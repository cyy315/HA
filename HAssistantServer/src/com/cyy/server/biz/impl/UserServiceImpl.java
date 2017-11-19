package com.cyy.server.biz.impl;

import java.util.List;

import com.cyy.server.biz.UserService;
import com.cyy.server.dao.UserDao;
import com.cyy.server.dao.impl.UserDaoImpl;
import com.cyy.server.entity.User;

public class UserServiceImpl implements UserService {

	UserDao userDao = new UserDaoImpl();
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userDao.getAllUser();
	}

	@Override
	public User getUserByName(String username) {
		// TODO Auto-generated method stub
		return userDao.getUserByName(username);
	}

}
