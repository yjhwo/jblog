package com.estsoft.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.BlogDAO;
import com.estsoft.jblog.vo.BlogVO;


@Service
public class BlogService {
	@Autowired
	private BlogDAO blogDao;

	public Long createBlog(String user_id) {	
		return blogDao.create(user_id);
	}
	
	public void registLogo(String user_id, String title, String url){		// Logo & Title 등록
		blogDao.registLogo(user_id,title, url);
	}
	
	public void registTitle(String user_id, String title){					// Title 등록
		blogDao.registTitle(user_id, title);
	}
	
	public BlogVO getInfo(String user_id){
		return blogDao.getInfo(user_id);
	}
	
}
