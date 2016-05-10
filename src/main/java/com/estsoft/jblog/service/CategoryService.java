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
	
	public Long insertCategory(CategoryVO vo) {	
		return categoryDao.insertCategory(vo);
	}
	
	public int deleteCategory(Long category_id){
		return categoryDao.deleteCategory(category_id);
	}
	
	public void addCount(Long category_id){
		categoryDao.addCount(category_id);
	}
	
	public List<CategoryVO> getList(Long blog_id){
		return categoryDao.getList(blog_id);
	}
	
	public Long getDefaultCategory(Long blog_id){
		return categoryDao.getDefaultCategory(blog_id);
	}

}
