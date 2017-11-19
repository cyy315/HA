package com.cyy.assistant.initiliaze;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.cyy.assistant.R;
import com.cyy.assistant.app.Global;
import com.cyy.assistant.login.LoginActivity;
import com.cyy.sdk.init.ActionGetVersion;
import com.cyy.sdk.util.OnActionListener;
import com.cyy.sdk.util.ResponseParam;
import com.cyy.util.dialog.DialogFactory;
import com.cyy.util.net.NetUtil;


public class InitilizeActivity extends Activity implements OnActionListener {

	/*
	 * private TimerTask task;
	 * 
	 * private Timer timer;
	 */

	private ActionGetVersion action;

	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_initilize);
		Log.d("cyy", "initialize" );
		//实例化Action
		action = new ActionGetVersion(Global.ACTION_ID_GET_VERSION);
		action.setDevice(ActionGetVersion.DEVICE_TYPE_ANDROID);
		action.setOnActionListener(this);

		/*
		 * task = new TimerTask() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * Intent intent = new Intent(); intent.setClass(InitilizeActivity.this,
		 * LoginActivity.class); startActivity(intent); finish(); } }; timer =
		 * new Timer(); timer.schedule(task, 3000);
		 */

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (NetUtil.isConnected(this)) {
			// 启动版本检查
			Log.d("cyy", "版本检查" );
			action.startAction();
		} else {
			if (null != dialog && dialog.isShowing()) {
				dialog.dismiss();
			}
			dialog = DialogFactory.showNetworkSettingFailed(this);
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (null != dialog && dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	@Override
	public void onActionSuccess(int actionId, ResponseParam ret) {
		// TODO Auto-generated method stub
		Log.d("cyy", "ActionSuccess" );
		if (actionId == Global.ACTION_ID_GET_VERSION) {
			int server = Integer.parseInt(ActionGetVersion
					.getServerVersion(ret));
			int client = Integer.parseInt(ActionGetVersion
					.getClientVersion(ret));
			if (Global.VERSION < server) {
				// 版本过低
				if (null != dialog && dialog.isShowing()) {
					dialog.dismiss();
				}
				dialog = DialogFactory.showMustUpdate(this);
			} else if (Global.VERSION < client) {

				// 有新版本
				if (null != dialog && dialog.isShowing()) {
					dialog.dismiss();
				}
				dialog = DialogFactory.showNewVersion(this,
						new OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								// 启动Login页面
								Intent intent = new Intent();
								intent.setClass(InitilizeActivity.this,
										LoginActivity.class);
								startActivity(intent);
								finish();
							}

						});

			} else {
				Intent intent = new Intent();
				intent.setClass(InitilizeActivity.this, LoginActivity.class);
				startActivity(intent);
				finish();
			}

		}

	}

	@Override
	public void onActionFailed(int actionId, int httpStatus) {
		Log.d("cyy", "ActionFailed" );
		// TODO Auto-generated method stub
		if (null != dialog && dialog.isShowing()) {
			dialog.dismiss();
		}
		dialog = DialogFactory.showConnectError(this);
	}

	@Override
	public void onActionException(int actionId, String exception) {
		// TODO Auto-generated method stub
		if (NetUtil.isConnected(this)) {
			// 当前请求错误未知的情况
			if (null != dialog && dialog.isShowing()) {
				dialog.dismiss();
			}
			dialog = DialogFactory.showConnectException(this);
		} else {
			// 当前网络断掉的情况
			if (null != dialog && dialog.isShowing()) {
				dialog.dismiss();
			}
			dialog = DialogFactory.showNetworkSettingFailed(this);
		}
	}

}
