package com.cyy.server.dao;

import java.util.List;

import com.cyy.server.entity.User;

public interface UserDao {

	/**
	 * ��ȡ���е��û�
	 * @return
	 */
	List<User> getAllUser();
	
	/**
	 * �����û�����ѯ�û�
	 * @param username
	 * @return
	 */
	User getUserByName(String username);
	
	//curd
}
