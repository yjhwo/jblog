package com.estsoft.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.jblog.service.BlogService;
import com.estsoft.jblog.service.CategoryService;
import com.estsoft.jblog.vo.CategoryVO;

@Controller	
public class BlogController {		// /blog/..
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/{user_id}")
	public String blog(@PathVariable("user_id")String user_id, Model model) {
		
		model.addAttribute("user_id", user_id);
		
		// 카테고리 목록 가져와서 model에 저장
		List<CategoryVO> list = categoryService.getList(user_id);
		model.addAttribute("list", list);
		
		return "/blog/blog-main";
	}

	@RequestMapping("/blog_admin")
	public String blog_admin(){		
		return "/blog/blog-admin-basic";
	}
	
	@RequestMapping("/blog_category")
	public String blog_category(){		
		return "/blog/blog-admin-category";
	}
	
	@RequestMapping("/blog_write")
	public String blog_write(){		
		return "/blog/blog-admin-write";
	}
	
}
