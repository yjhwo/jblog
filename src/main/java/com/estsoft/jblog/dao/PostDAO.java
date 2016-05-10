package com.estsoft.jblog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.PostVO;

@Repository
public class PostDAO {
	@Autowired
	private SqlSession sqlSession;
	
	public void write(PostVO vo){
		sqlSession.insert("post.write",vo);
	}
	
	public List<PostVO> getCategory(Long category_id){
		return sqlSession.selectList("post.getList", category_id); 
	}
	
	public PostVO getPost(PostVO vo){
		return sqlSession.selectOne("post.getPost", vo);
	}
}
