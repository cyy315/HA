package com.cyy.server.entity;
/**
 * 用户实体类
 * @author WeiJing
 *
 */
public class User {
	/**fields*/
	private String userId;
	private String username;
	private String pass;
	
	/**getters and setters*/
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
}
