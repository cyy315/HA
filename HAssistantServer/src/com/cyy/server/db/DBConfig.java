package com.cyy.server.db;

import java.util.Properties;

public class DBConfig {

	private static Properties prop = new Properties();
	
	static{
		try{
			prop.load(DBConfig.class.getResourceAsStream("dbconfig.properties"));
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public static final String CLASS_NAME = prop.getProperty("CLASS_NAME");
	public static final String DATABASE_URL = prop.getProperty("DATABASE_URL");
	public static final String USERNAME = prop.getProperty("USERNAME");
	public static final String PASSWORD = prop.getProperty("PASSWORD");
}
