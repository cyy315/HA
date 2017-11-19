package com.cyy.server.data;

import java.io.IOException;
import java.util.Properties;

public class VersionInfo {

	private static Properties properties = new Properties();
	
	static{
		try {
			properties.load(VersionInfo.class.getResourceAsStream("version.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static final String SERVER_VERSION = properties.getProperty("SERVER_VERSION");
	public static final String CLIENT_VERSION = properties.getProperty("CLIENT_VERSION");
	
}
