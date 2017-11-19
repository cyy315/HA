package com.cyy.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn {
		
	public Connection conn = null;
	public PreparedStatement pstmt = null;
	public ResultSet rs = null;
	
	public void getConnection(){
		
		try {
			//1.加载驱动类
			Class.forName(DBConfig.CLASS_NAME);
			//2.创建数据库连接
			conn = DriverManager.getConnection(DBConfig.DATABASE_URL,DBConfig.USERNAME,DBConfig.PASSWORD);
			//3.
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int execOther(final String sql, final Object[] params){
		//获取数据库连接
		this.getConnection();
		try{
			//创建Statement接口对象
			pstmt = conn.prepareStatement(sql);
			//动态为pstmt对象赋值参数
			if(params!=null){
				for(int i = 0; i<params.length;i++){
					pstmt.setObject(i+1, params[i]);
				}
			}
			
			//使用Statement对象发送sql语句
			int affectedRows = pstmt.executeUpdate();
			//返回结果
			return affectedRows;
		}catch(SQLException e){
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public ResultSet execQuery(final String sql, final Object[] params){
		
		this.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			if(params!=null){
				for(int i = 0; i<params.length;i++){
					pstmt.setObject(i+1, params[i]);
				}
			}
			rs = pstmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void closeConn(){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//curd
}
