package com.cyy.server.biz;

import java.util.List;

import com.cyy.server.entity.User;

public interface UserService {

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
