package com.estsoft.jblog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.jblog.service.CategoryService;
import com.estsoft.jblog.service.PostService;
import com.estsoft.jblog.vo.BlogVO;
import com.estsoft.jblog.vo.CategoryVO;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	
	@RequestMapping("/getList")
	@ResponseBody
	public Map<String, Object> getList(@RequestParam Long blog_id, Model model) {

		List<CategoryVO> list = categoryService.getList(blog_id); // 카테고리 목록 
		model.addAttribute("list", list);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("data", list);
		
		return map;
	}

	@RequestMapping("/add")
	@ResponseBody
	public Map<String, Object> addCategory(@RequestParam String name, @RequestParam String desc, @RequestParam Long blog_id) {

		CategoryVO vo = new CategoryVO(blog_id, name, desc, 0L);
		Long category_id = categoryService.insertCategory(vo);
		vo.setCategory_id(category_id);
		
		Map<String, Object> map = new HashMap<String, Object>();

		if (category_id != 0) { // 성공
			map.put("result", "success");
		} else {
			map.put("result", "fail");
		}
		
		map.put("data", vo);
		
		return map;
	}
	
	@RequestMapping("/del")
	@ResponseBody
	public Map<String, Object> delCategory(@RequestParam(value = "category_id", required = true, defaultValue = "") Long category_id){		
	
		postService.deletePostAll(category_id);
		int chk = categoryService.deleteCategory(category_id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (chk > 0) { // 성공
			map.put("result", "success");
		} else {
			map.put("result", "fail");
		}
		
		return map;
	}


	
	
	
}
