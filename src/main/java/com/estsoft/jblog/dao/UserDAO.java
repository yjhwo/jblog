package com.estsoft.jblog.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.jblog.vo.UserVO;


@Repository
public class UserDAO {

	@Autowired
	private SqlSession sqlSession;
	
	public void insert(UserVO vo) {
		sqlSession.insert("user.insert", vo);
	}
		
	public UserVO get(String user_id) { 			// 아이디 있는지 확인
		return sqlSession.selectOne("user.getUserId", user_id);
	}
	
	public UserVO getUser(UserVO vo){
		return sqlSession.selectOne("user.getUser",vo);
	}
}
