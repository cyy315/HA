package com.cyy.assistant.app;

public class Global {

	/** 客户端版本号 **/
	public final static int VERSION = 4;
	/** 获取客户端版本号操作ID **/
	public final static int ACTION_ID_GET_VERSION = 0x10;

	/** 登入操作ID **/
	public final static int ACTION_ID_DO_LOGIN = 0x20;

	/** 获取图片URL操作 **/
	public final static int ACTION_ID_GET_PICS = 0x30;
	
	/** 获取图片URL操作 **/
	public final static int ACTION_ID_GET_QUESTS = 0x40;
	
	/** 当前登入用户的id **/
	public static String userId;
}
