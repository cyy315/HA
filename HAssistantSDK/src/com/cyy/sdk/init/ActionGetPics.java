package com.cyy.sdk.init;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

import com.cyy.sdk.base.ActionBase;
import com.cyy.sdk.util.GetAction;
import com.cyy.sdk.util.GetParam;
import com.cyy.sdk.util.OnActionListener;
import com.cyy.sdk.util.ResponseParam;

public class ActionGetPics extends ActionBase {
	
	private final static String ACTION_NAME = "GetPics";	

	private final static String PARAM_PIC_LIST = "picList";	
	private final static String PARAM_PIC_URL = "picUrl";	
	
	private GetAction action;
	
	private GetParam outParam;
	
	public ActionGetPics(int actionId){
		action = new GetAction(actionId,BASE_URL+ACTION_NAME);
		outParam = new GetParam();
	}
	
	public void setOnActionListener(OnActionListener listener){
		action.setOnActionListener(listener);
	}
	
	
	public void startAction(){
		action.setParam(outParam);
		action.startAction();
	}
	
	public static String[] getPicList(ResponseParam param){
		Log.d("cyy", "bbbbbbbs");
		JSONArray array = param.getJSONArray(PARAM_PIC_LIST);
		Log.d("cyy", "aaa");
		String[] picList =new String[array.length()];
		for (int i = 0;i<array.length();i++){
			try {
				picList[i] = array.getJSONObject(i).getString(PARAM_PIC_URL);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				picList[i]="";
			}
		}
		return picList;
	}
}
