package com.cyy.sdk.init;

import android.util.Log;

import com.cyy.sdk.MainActivity;
import com.cyy.sdk.base.ActionBase;
import com.cyy.sdk.util.GetAction;
import com.cyy.sdk.util.GetParam;
import com.cyy.sdk.util.OnActionListener;
import com.cyy.sdk.util.ResponseParam;

public class ActionGetVersion extends ActionBase {
	public final static int DEVICE_TYPE_ANDROID = 0;
	public final static int DEVICE_TYPE_IOS = 1;
	
	private final static String ACTION_NAME = "GetVersions";	

	private final static String PARAM_DEVICE = "device";	
	private final static String PARAM_SERVER_VERSION = "serverVersion";	
	private final static String PARAM_CLIENT_VERSION = "clientVersion";
	
	private GetAction action;
	
	private GetParam outParam;
	
	public ActionGetVersion(int actionId){
		action = new GetAction(actionId,BASE_URL+ACTION_NAME);
		Log.d("cyy", BASE_URL+ACTION_NAME);
		outParam = new GetParam();
	}
	
	public void setOnActionListener(OnActionListener listener){
		action.setOnActionListener(listener);
	}
	
	
	public void startAction(){
		action.setParam(outParam);
		action.startAction();
	}
	
	public void setDevice(int deviceType){
		switch(deviceType){
		case DEVICE_TYPE_ANDROID:
			outParam.addParam(PARAM_DEVICE, ""+DEVICE_TYPE_ANDROID);
			break;
		case DEVICE_TYPE_IOS:
			outParam.addParam(PARAM_DEVICE, ""+DEVICE_TYPE_IOS);
			break;
		}
	}
	
	public static String getServerVersion(ResponseParam ret){
		return ret.getString(PARAM_SERVER_VERSION);
	}
	
	public static String getClientVersion(ResponseParam ret){
		return ret.getString(PARAM_CLIENT_VERSION);
	}
}
