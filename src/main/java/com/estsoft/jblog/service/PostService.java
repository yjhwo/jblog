package com.estsoft.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.PostDAO;
import com.estsoft.jblog.vo.PostVO;


@Service
public class PostService {
	@Autowired
	private PostDAO postDao;
	
	public void write(PostVO vo){
		postDao.write(vo);
	}
	
	public List<PostVO> getCategory(Long category_id){
		return postDao.getCategory(category_id);
	}
	
	public PostVO getPost(PostVO vo){
		return postDao.getPost(vo);
	}
}
