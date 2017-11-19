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
			//1.����������
			Class.forName(DBConfig.CLASS_NAME);
			//2.�������ݿ�����
			conn = DriverManager.getConnection(DBConfig.DATABASE_URL,DBConfig.USERNAME,DBConfig.PASSWORD);
			//3.
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int execOther(final String sql, final Object[] params){
		//��ȡ���ݿ�����
		this.getConnection();
		try{
			//����Statement�ӿڶ���
			pstmt = conn.prepareStatement(sql);
			//��̬Ϊpstmt����ֵ����
			if(params!=null){
				for(int i = 0; i<params.length;i++){
					pstmt.setObject(i+1, params[i]);
				}
			}
			
			//ʹ��Statement������sql���
			int affectedRows = pstmt.executeUpdate();
			//���ؽ��
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
