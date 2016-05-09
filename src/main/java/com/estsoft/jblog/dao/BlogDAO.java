package com.estsoft.jblog.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.BlogVO;

@Repository
public class BlogDAO {
	@Autowired
	private SqlSession sqlSession;

	public Long create(String user_id) {
		
		BlogVO vo = new BlogVO();
		vo.setUser_id(user_id);
		
		int count = sqlSession.insert("blog.create", vo);
		
		if(count==0){
			return 0L;
		}
		
		return vo.getBlog_id();
	}
	
	public void registLogo(String user_id,String title, String url){
		BlogVO vo = new BlogVO(user_id, title, url);
		sqlSession.insert("blog.registLogo", vo);
	}
	
	public void registTitle(String user_id, String title){
		BlogVO vo = new BlogVO(user_id, title);
		sqlSession.insert("blog.registTitle", vo);
	}

	
	public BlogVO getInfo(String user_id){
		return sqlSession.selectOne("blog.getInfo", user_id);
	}
}
