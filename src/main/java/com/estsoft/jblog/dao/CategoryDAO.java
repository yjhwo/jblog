package com.estsoft.jblog.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.BlogVO;
import com.estsoft.jblog.vo.CategoryVO;


@Repository
public class CategoryDAO {
	@Autowired
	private SqlSession sqlSession;

	public void create(Long blog_id) {
		CategoryVO vo = new CategoryVO(blog_id, "미분류", "카테고리를 지정하지 않은 경우", 0L);
		sqlSession.insert("category.create", vo);
	}

	public int insertCategory(CategoryVO vo){
		// return : int The number of rows affected by the insert.
		return sqlSession.insert("category.create",vo);
	}
	
	public int deleteCategory(Long category_id){
		// return : int The number of rows affected by the delete.
		return sqlSession.delete("category.delete", category_id);
	}
	
	public List<CategoryVO> getList(Long blog_id) {
		List<CategoryVO> list = sqlSession.selectList("category.selectList", blog_id);
		return list;
	}
	
}
