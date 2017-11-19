package com.cyy.sdk;

import com.cyy.sdk.R;
import com.cyy.sdk.init.ActionGetPics;
import com.cyy.sdk.init.ActionGetVersion;
import com.cyy.sdk.user.ActionDoLogin;
import com.cyy.sdk.util.GetAction;
import com.cyy.sdk.util.GetParam;
import com.cyy.sdk.util.OnActionListener;
import com.cyy.sdk.util.ResponseParam;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity<ActionGetVerison> extends Activity implements OnActionListener {
	
	public final static int ACTION_ID_GET_VERSION = 0x11;
	public final static int ACTION_ID_GET_PICS = 0x12;
	public final static int ACTION_ID_DO_LOGIN = 0x13;

	public TextView tv;
	
	public Button btn;
	
	public ActionGetVersion action;
	/*public GetAction aaction;*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tv = (TextView) findViewById(R.id.text);
		btn = (Button) findViewById(R.id.btn);

		/*aaction = new GetAction(0,"http://192.168.1.107:8080/HAssistantServer/GetVersions");
		GetParam param = new GetParam();
		param.addParam("device", "0");
		aaction.setParam(param);
		aaction.setOnActionListener(new OnActionListener(){

			@Override
			public void onActionSuccess(int actionId, ResponseParam ret) {
				// TODO Auto-generated method stub
				String serverVersion = ret.getString("serverVersion");
				tv.setText("Server:"+serverVersion);
			}

			@Override
			public void onActionFailed(int actionId, int httpStatus) {
				// TODO Auto-generated method stub
				tv.setText("HttpStatus:"+httpStatus);
			}

			@Override
			public void onActionException(int actionId, String exception) {
				// TODO Auto-generated method stub
				tv.setText("Exception:"+exception);
			}
			
		});*/
		action = new ActionGetVersion(ACTION_ID_GET_VERSION);
		action.setDevice(ActionGetVersion.DEVICE_TYPE_ANDROID);
		action.setOnActionListener(this);
		
		ActionGetPics getPics = new ActionGetPics(ACTION_ID_GET_PICS);
		getPics.setOnActionListener(this);
		getPics.startAction();
		
		/*ActionDoLogin doLogin = new ActionDoLogin(ACTION_ID_DO_LOGIN);
		doLogin.setUser("test");
		doLogin.setPass("098f6bcd4621d373cade4e832627b4f6");
		doLogin.setOnActionListener(this);
		doLogin.startAction();*/
		

		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				action.startAction();
			}
		});

	}
	
	@Override
	public void onActionSuccess(int actionId, ResponseParam ret) {
		// TODO Auto-generated method stub
		switch(actionId){
		case ACTION_ID_GET_VERSION:
			String client = ActionGetVersion.getClientVersion(ret);
			String server = ActionGetVersion.getServerVersion(ret);
			tv.setText("Client:"+client+" Server:"+server);
			break;
		case ACTION_ID_GET_PICS:
			
			for(String url:ActionGetPics.getPicList(ret)){
				Log.d("cyy","picUrl"+url);
			}
			break;
		case ACTION_ID_DO_LOGIN:
			String uid = ActionDoLogin.getUserId(ret);
			Log.d("cyy", "uid:"+uid);
		}
	}

	@Override
	public void onActionFailed(int actionId, int httpStatus) {
		// TODO Auto-generated method stub
		tv.setText("Action:"+actionId+" httpFailed:"+httpStatus);
	}

	@Override
	public void onActionException(int actionId, String exception) {
		// TODO Auto-generated method stub
		tv.setText("Action:"+actionId+" exception:"+exception);
	}


}
