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
		
		System.out.println(count);
		
		if(count==0){
			return 0L;
		}
		System.out.println(count+"_ blog_id:"+vo.getBlog_id());
		
		return vo.getBlog_id();
	}
}
