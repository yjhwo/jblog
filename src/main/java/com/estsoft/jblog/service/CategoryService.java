package com.estsoft.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.CategoryDAO;


@Service
public class CategoryService {
	@Autowired
	private CategoryDAO categoryDao;
	
	public void createCategory(Long blog_id) {	
		categoryDao.create(blog_id);
	}
}
