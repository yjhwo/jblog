package com.estsoft.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.jblog.service.BlogService;
import com.estsoft.jblog.service.CategoryService;
import com.estsoft.jblog.service.UserService;
import com.estsoft.jblog.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/joinform")
	public String joinform() {
		return "/user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVO vo, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel()); // Map return
			
		    List<ObjectError> list = result.getAllErrors();
		    for (ObjectError e : list) {
		         System.out.println(" ObjectError : " + e );
		    }
			return "/user/join"; 	
		}

		userService.join(vo);		

		Long blog_id = blogService.createBlog(vo.getUser_id()); 		// 블로그 생성
		categoryService.createCategory(blog_id);						// default 카테고리 생성(0:미분류)
		
		return "/user/joinsuccess";
	}

	@RequestMapping("/checkid")
	@ResponseBody
	public Map<String, Object> checkid(@RequestParam(value = "user_id", required = true, defaultValue = "") String user_id) {

		UserVO vo = userService.getUserId(user_id);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", vo == null);

		return map;
	}
	
	// -------------------------- loginform 오버로딩
//	@RequestMapping("/loginform")
//	public String loginform() {
//		return "/user/login";
//	}
	
	@RequestMapping("/loginform")
	public String loginform(@RequestParam(value="ret", required = true, defaultValue="-1") String user_id,Model model) {
		
		// 블로그 주소로 접근해서 로그인 한 경우  main으로 리턴하지 않고, 해당 블로그로 리턴하도록 
		model.addAttribute("ret", user_id);
		
		return "/user/login";
	}
	// --------------------------

}
