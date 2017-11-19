package com.cyy.server.dao.impl;

import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.cyy.server.dao.UserDao;
import com.cyy.server.db.DBConn;
import com.cyy.server.entity.User;

public class UserDaoImpl implements UserDao {

	DBConn conn = new DBConn();
	
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		List<User> userList = new ArrayList<User>();
		
		User user = null;
		try{
			String sql = "select * from tb_user";
			ResultSet rs = conn.execQuery(sql,null);
			
			while(rs.next()){
				user = new User();
				user.setUserId(""+rs.getInt("user_id"));
				user.setUsername(rs.getString("user"));
				user.setPass(rs.getString("pass"));
				userList.add(user);
			}
			
		}catch(Exception e){
			
		}
		
		return userList;
	}

	@Override
	public User getUserByName(String username) {
		
		User user = null;
		try{
			String sql = MessageFormat.format("select * from tb_user where user=\"{0}\"", username);
			ResultSet rs = conn.execQuery(sql,null);
			
			while(rs.next()){
				user = new User();
				user.setUserId(""+rs.getInt("user_id"));
				user.setUsername(rs.getString("user"));
				user.setPass(rs.getString("pass"));
			}
			
		}catch(Exception e){
			
		}
		
		return user;
	}

}
