package com.estsoft.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
	
	@RequestMapping("/main")
	public String index(){
		return "/main/index";
	}
	
	@RequestMapping("/*")
	public String blog(@RequestParam(value = "user_id", required = true, defaultValue = "") String user_id){		// jblog/kickscar/20....
		System.out.println("user_id:"+user_id);
		return "/blog/blog-main";
	}
}
