package com.estsoft.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.BlogDAO;


@Service
public class BlogService {
	@Autowired
	private BlogDAO blogDao;

	public Long createBlog(String user_id) {	
		return blogDao.create(user_id);
	}
}
