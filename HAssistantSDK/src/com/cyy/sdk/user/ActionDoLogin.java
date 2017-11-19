package com.cyy.sdk.user;

import com.cyy.sdk.base.ActionBase;
import com.cyy.sdk.util.OnActionListener;
import com.cyy.sdk.util.PostAction;
import com.cyy.sdk.util.PostParam;
import com.cyy.sdk.util.ResponseParam;

public class ActionDoLogin extends ActionBase {
	
	private final static String ACTION_NAME = "DoLogin";	

	private final static String PARAM_USER = "user";	
	private final static String PARAM_PASS = "pass";	
	private final static String PARAM_USER_ID = "userId";
	
	private PostAction action;
	
	private PostParam outParam;
	
	public ActionDoLogin(int actionId){
		action = new PostAction(actionId,BASE_URL+ACTION_NAME);
		outParam = new PostParam();
	}
	
	public void setOnActionListener(OnActionListener listener){
		action.setOnActionListener(listener);
	}
	
	
	public void startAction(){
		action.setParam(outParam);
		action.startAction();
	}
	
	public void setUser(String user){
		outParam.putString(PARAM_USER, user);
	}
	
	public void setPass(String pass){
		outParam.putString(PARAM_PASS, pass);
	}
	
	public static String getUserId(ResponseParam ret){
		return ret.getString(PARAM_USER_ID);
	}
}
