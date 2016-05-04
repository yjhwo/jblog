package com.estsoft.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.jblog.dao.UserDAO;
import com.estsoft.jblog.vo.UserVO;

//달아줘야 root-application-context에 생성됨
@Service
public class UserService {
	@Autowired
	private UserDAO userDao;
	
	public void join(UserVO vo){
		userDao.insert(vo);
	}
	
	public UserVO getUserId(String user_id){
		UserVO vo = userDao.get(user_id);
		return vo;
	}
	
	public UserVO login(UserVO vo){
		UserVO authUser = userDao.getUser(vo);
		return authUser;
	}
}
