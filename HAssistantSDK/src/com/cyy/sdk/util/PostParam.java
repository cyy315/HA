package com.cyy.sdk.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostParam {

	public JSONObject params;

	public void putString(String key,String value){
		if(null == params){
			params = new JSONObject();
		}
		try {
			params.put(key, value);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void putJSONObject(String key, JSONObject object){
		try {
			params.put(key, object);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void putJSONArray(String key, JSONArray array){
		try {
			params.put(key, array);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return params.toString();
	}
	
}
