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
import com.estsoft.jblog.service.PostService;
import com.estsoft.jblog.vo.BlogVO;
import com.estsoft.jblog.vo.CategoryVO;
import com.estsoft.jblog.vo.PostVO;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;

	private static final String SAVE_PATH = "/temp"; // 미리 약속을 해둬야함

	@RequestMapping("/{user_id}")
	public String blog(@PathVariable("user_id") String user_id,
			@RequestParam(value = "category_id", required = true, defaultValue = "-1") Long category_id,
			@RequestParam(value = "post_id", required = true, defaultValue = "-1") Long post_id, Model model) {

		model.addAttribute("user_id", user_id);

		// --------- 블로그 정보, 카테고리 목록 가져와서 model에 저장
		BlogVO vo = blogService.getInfo(user_id);
		model.addAttribute("vo", vo);

		List<CategoryVO> list = categoryService.getList(vo.getBlog_id());
		model.addAttribute("list", list);
		// ------

		if (category_id == -1) { // 카테고리 클릭 안 했을 경우: default(미분류)출력, 첫 번째 post출력
			Long defaultCategoryNo = categoryService.getDefaultCategory(vo.getBlog_id());

			List<PostVO> postList = postService.getCategory(defaultCategoryNo); // 해당 카테고리의 전체 글 목록
			model.addAttribute("postList", postList);

			if (!postList.isEmpty()) { // 글이 있는 경우
				model.addAttribute("title", postList.get(0).getTitle());
				model.addAttribute("content", postList.get(0).getContent());
				model.addAttribute("post_id", postList.get(0).getPost_id());
			}else {
				//model.addAttribute("title", "아직 등록된 글이 없습니다.^^");
				model.addAttribute("content", "아직 등록된 내용이 없습니다.^^");
			}

		} else { // 카테고리 클릭한 경우
			List<PostVO> postList = postService.getCategory(category_id);
			model.addAttribute("postList", postList);

			// 글 클릭 안 한 경우 default
			if (post_id == -1) {
				if (!postList.isEmpty()) { // 글이 있는 경우
					model.addAttribute("title", postList.get(0).getTitle());
					model.addAttribute("content", postList.get(0).getContent());
					model.addAttribute("post_id", postList.get(0).getPost_id());
				} else {
					//model.addAttribute("title", "아직 등록된 글이 없습니다.^^");
					model.addAttribute("content", "아직 등록된 내용이 없습니다.^^");
				}
			} else {
				// 글 클릭 했을 때 (category_id, post_id)
				PostVO postvo = postService.getPost(new PostVO(category_id, post_id));
				model.addAttribute("title", postvo.getTitle());
				model.addAttribute("content", postvo.getContent());
				model.addAttribute("post_id", postvo.getPost_id());
			}

		}

		return "/blog/blog-main";
	}

	@RequestMapping("/{user_id}/deletepost/{post_id}")
	public String deletePost(@PathVariable("user_id") String user_id,
							@PathVariable("post_id") Long post_id, Model model) {
		
		postService.deletePost(post_id);
		
		return "redirect:/"+user_id;
	}
	
	
	@RequestMapping("/{user_id}/blog_admin")
	public String blog_admin(@PathVariable("user_id") String user_id, Model model) {

		BlogVO vo = blogService.getInfo(user_id); // get_title,get_logo
		model.addAttribute("vo", vo);

		return "/blog/blog-admin-basic";
	}

	@RequestMapping("/{user_id}/blog_category")
	public String blog_category(@PathVariable("user_id") String user_id, Model model) {

		BlogVO vo = blogService.getInfo(user_id);
		model.addAttribute("vo", vo);

		List<CategoryVO> list = categoryService.getList(vo.getBlog_id()); // 카테고리
																			// 목록
																			// 가져오기
		model.addAttribute("list", list);

		return "/blog/blog-admin-category";
	}

	@RequestMapping("/{user_id}/blog_write")
	public String blog_write(@PathVariable("user_id") String user_id, Model model) {

		BlogVO vo = blogService.getInfo(user_id); // 해당 블로그 정보
		model.addAttribute("vo", vo);

		List<CategoryVO> list = categoryService.getList(vo.getBlog_id()); // 카테고리
																			// 목록
																			// 가져오기
		model.addAttribute("list", list);

		return "/blog/blog-admin-write";
	}

	// 블로그 관리 - 기본 설정 - 로고이미지
	@RequestMapping("/{user_id}/upload")
	public String upload(@RequestParam String title, @RequestParam("file") MultipartFile file,
			@RequestParam String user_id) {

		if (file.isEmpty() == false) { // Logo & Title 저장
			String fileOriginalName = file.getOriginalFilename(); // 사용자가 업로드한
																	// 파일
																	// 이름.(ex.
																	// 지출.txt)
			String extName = fileOriginalName.substring(fileOriginalName.lastIndexOf(".") + 1,
					fileOriginalName.length());
			String saveFileName = genSaveFileName(extName); // 저장할 파일 이름(구분이 쉽게
															// 보통 날짜로 많이 함) =>
															// 유일해야 함!

			writeFile(file, SAVE_PATH, saveFileName); // file1안에 InputStream이
														// 들어가 있음
			String url = "/product-images/" + saveFileName; // ★★★ 저장한 경로를 주지않고
															// 쌩뚱맞은 폴더 이름으로 해놓은
															// 이유는

			blogService.registLogo(user_id, title, url); // ★ db에 로고, 타이틀 저장!!!
															// ★
		} else { // Title만 변경
			blogService.registTitle(user_id, title);
		}

		return "redirect:/" + user_id; // 아이디로...
	}

	private void writeFile(MultipartFile file, String path, String fileName) {
		FileOutputStream fos = null;
		try {
			byte fileData[] = file.getBytes(); // getBytes():업로드한 파일 데이터를 구하는 가장
												// 단순한 방법
			fos = new FileOutputStream(path + "/" + fileName);
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

	private String genSaveFileName(String extName) {

		Calendar calendar = Calendar.getInstance();
		String fileName = "";

		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);

		return fileName;
	}

}
