package com.estsoft.jblog.controller;

import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.estsoft.jblog.service.BlogService;
import com.estsoft.jblog.service.CategoryService;
import com.estsoft.jblog.vo.BlogVO;
import com.estsoft.jblog.vo.CategoryVO;

@Controller	
public class BlogController {		// /blog/..
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	
	private static final Log LOG = LogFactory.getLog( BlogController.class );
	private static final String SAVE_PATH = "/temp";								// 미리 약속을 해둬야함  
	
	@RequestMapping("/{user_id}")
	public String blog(@PathVariable("user_id")String user_id, Model model) {
		
		model.addAttribute("user_id", user_id);
		
		// 카테고리 목록 가져와서 model에 저장
		BlogVO vo = blogService.getInfo(user_id);
		model.addAttribute("vo", vo);
		
		List<CategoryVO> list = categoryService.getList(vo.getBlog_id());
		model.addAttribute("list", list);		
		
		return "/blog/blog-main";
	}

	@RequestMapping("/{user_id}/blog_admin")
	public String blog_admin(@PathVariable("user_id") String user_id, Model model){		
		
		BlogVO vo = blogService.getInfo(user_id);							// get_title,get_logo
		model.addAttribute("vo", vo);
			
		return "/blog/blog-admin-basic";
	}
	
	@RequestMapping("/{user_id}/blog_category")
	public String blog_category(@PathVariable("user_id") String user_id, Model model){		
		
		BlogVO vo = blogService.getInfo(user_id);						
		model.addAttribute("vo", vo);
		
		List<CategoryVO> list = categoryService.getList(vo.getBlog_id());		//카테고리 목록 가져오기
		model.addAttribute("list", list);				
		
		return "/blog/blog-admin-category";
	}
	
	@RequestMapping("/{user_id}/blog_write")
	public String blog_write(@PathVariable("user_id")String user_id, Model model){		
		
		BlogVO vo = blogService.getInfo(user_id);						
		model.addAttribute("vo", vo);
		
		return "/blog/blog-admin-write";
	}
	
	
	
	// 블로그 관리 - 기본 설정 - 로고이미지
	@RequestMapping("/{user_id}/upload")
	public String upload( @RequestParam String title, @RequestParam( "file" ) MultipartFile file,@RequestParam String user_id ) {

	
		if( file.isEmpty() == false ) {		// Logo & Title 저장
	        String fileOriginalName = file.getOriginalFilename();		// 사용자가 업로드한 파일 이름.(ex. 지출.txt)
	        String extName = fileOriginalName.substring( fileOriginalName.lastIndexOf(".") + 1, fileOriginalName.length() );
	        String saveFileName = genSaveFileName( extName );			// 저장할 파일 이름(구분이 쉽게 보통 날짜로 많이 함) => 유일해야 함!
	        Long size = file.getSize();
	        
	        writeFile( file, SAVE_PATH, saveFileName );					// file1안에 InputStream이 들어가 있음

	        String url = "/product-images/" + saveFileName;				// ★★★ 저장한 경로를 주지않고 쌩뚱맞은 폴더 이름으로 해놓은 이유는
	        
	        blogService.registLogo(user_id,title,url);					// ★ db에 로고, 타이틀 저장!!! ★				
	        
		}else{								// Title만 변경
			blogService.registTitle(user_id, title);
		}
		
        return "redirect:/"+user_id;		// 아이디로...
	}
	
	private void writeFile( MultipartFile file, String path, String fileName ) {
		FileOutputStream fos = null;
		try {
			byte fileData[] = file.getBytes();					// getBytes():업로드한 파일 데이터를 구하는 가장 단순한 방법
			fos = new FileOutputStream( path + "/" + fileName );
			fos.write(fileData);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
	private String genSaveFileName( String extName ) {
		
        Calendar calendar = Calendar.getInstance();
		String fileName = "";
        
        fileName += calendar.get( Calendar.YEAR );
        fileName += calendar.get( Calendar.MONTH );
        fileName += calendar.get( Calendar.DATE );
        fileName += calendar.get( Calendar.HOUR );
        fileName += calendar.get( Calendar.MINUTE );
        fileName += calendar.get( Calendar.SECOND );
        fileName += calendar.get( Calendar.MILLISECOND );
        fileName += ( "." + extName );
        
        return fileName;
	}
	
}
