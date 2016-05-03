package com.estsoft.jblog.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAO {
	@Autowired
	private SqlSession sqlSession;

	public void create(Long blog_id) {
		sqlSession.insert("category.create", blog_id);
	}
}
