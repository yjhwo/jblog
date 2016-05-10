package com.estsoft.jblog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.estsoft.jblog.service.BlogService;
import com.estsoft.jblog.service.CategoryService;
import com.estsoft.jblog.service.PostService;
import com.estsoft.jblog.vo.BlogVO;
import com.estsoft.jblog.vo.CategoryVO;
import com.estsoft.jblog.vo.PostVO;

@Controller
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/{user_id}/write", method = RequestMethod.POST)
	public String write(@PathVariable("user_id") String user_id, @ModelAttribute @Valid PostVO vo, BindingResult result,
			Model model) { // 글 작성

		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel()); // Map return

			BlogVO blogvo = blogService.getInfo(user_id); // 해당 블로그 정보
			model.addAttribute("vo", blogvo);

			List<CategoryVO> list = categoryService.getList(blogvo.getBlog_id());						
			model.addAttribute("list", list);

			return "/blog/blog-admin-write";
		}

		vo = new PostVO(vo.getCategory_id(), vo.getTitle(), vo.getContent());
		categoryService.addCount(vo.getCategory_id());				// CATEGORY DB에 POST COUNT++
		postService.write(vo);										// POST DB에 글 등록

		return "redirect:/" + user_id;
	}


}
