package com.estsoft.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.CategoryDAO;
import com.estsoft.jblog.vo.CategoryVO;


@Service
public class CategoryService {
	@Autowired
	private CategoryDAO categoryDao;
	
	public void createCategory(Long blog_id) {	
		categoryDao.create(blog_id);
	}
	
	public List<CategoryVO> getList(String user_id){
		return categoryDao.getList(user_id);
	}
}
