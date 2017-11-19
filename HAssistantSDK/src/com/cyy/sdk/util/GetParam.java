package com.cyy.sdk.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GetParam {
	private Map<String, String> params;
	public void addParam(String key, String value){
		if(null == params){
			params = new HashMap<String,String>();
		}
		params.put(key, value);
	}
	@Override
	public String toString() {
		
		if(null != params){
			StringBuffer sb = new StringBuffer();
			sb.append('?');
			Iterator<String> it = params.keySet().iterator();
			while(it.hasNext()){
				String key = it.next();
				sb.append(key);
				sb.append('=');
				sb.append(params.get(key));
				if(it.hasNext()){
					sb.append('&');
				}
			}
			return sb.toString();
		}
		return "";
		
	}
	
}
