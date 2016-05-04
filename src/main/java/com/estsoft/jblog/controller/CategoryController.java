package com.estsoft.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.estsoft.jblog.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
/*	@RequestMapping("/create")
	public String create(@RequestParam(value = "blog_id", required = true, defaultValue = "-1") Long blog_id) {		// 회원 가입 시 기본 카테고리 생성
		
		categoryService.createCategory(blog_id);
		return "/user/joinsuccess";
	}*/
}
