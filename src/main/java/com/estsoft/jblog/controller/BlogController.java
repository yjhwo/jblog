package com.estsoft.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.estsoft.jblog.service.BlogService;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/create")
	public String create(@RequestParam(value = "user_id", required = true, defaultValue = "") String user_id, Model model) {	// blog 생성
		
		Long blog_id = blogService.createBlog(user_id);
		model.addAttribute("blog_id", blog_id);
		
//		return "/user/joinsuccess";
		return "redirect:/category/create";
	}
	
}
