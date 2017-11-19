package com.cyy.util.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetUtil {

	public static boolean isConnected(Context context) {
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			
			if (null != connectivity) {
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (null != info && info.isConnected()) {
					
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						
						return true;
						
					}
				}
			}
		} catch (Exception e) {

		}
		
		return false;

	}
}
