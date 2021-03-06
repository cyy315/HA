package com.cyy.server.dao;

import java.util.List;

import com.cyy.server.entity.User;

public interface UserDao {

	/**
	 * 获取所有的用户
	 * @return
	 */
	List<User> getAllUser();
	
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	User getUserByName(String username);
	
	//curd
}
