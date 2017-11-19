package com.cyy.assistant.login;

import com.cyy.assistant.R;
import com.cyy.assistant.app.Global;
import com.cyy.assistant.initiliaze.InitilizeActivity;
import com.cyy.assistant.main.MainActivity;
import com.cyy.sdk.user.ActionDoLogin;
import com.cyy.sdk.util.OnActionListener;
import com.cyy.sdk.util.ResponseParam;
import com.cyy.util.dialog.DialogFactory;
import com.cyy.util.md5.MD5Util;
import com.cyy.util.net.NetUtil;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class LoginActivity extends Activity implements OnClickListener,
		OnActionListener {

	private TextView titleText;
	private ImageButton titleLeft;
	private ImageButton titleRight;
	private EditText userEdit;
	private EditText pwdEdit;
	private ImageButton loginBtn;

	private ActionDoLogin action;

	private Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		this.setContentView(R.layout.activity_login);
		Log.d("cyy", "login" );
		action = new ActionDoLogin(Global.ACTION_ID_DO_LOGIN);
		action.setOnActionListener(this);

		initTitle();
		initView();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (null != dialog && dialog.isShowing()) {
			dialog.dismiss();
		}
	}

	/**
	 * 初始化标题栏
	 */
	private void initTitle() {
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
		titleText = (TextView) findViewById(R.id.title_text);
		titleLeft = (ImageButton) findViewById(R.id.title_left);
		titleRight = (ImageButton) findViewById(R.id.title_right);
		// 隐藏左右按钮
		titleLeft.setVisibility(ImageButton.INVISIBLE);
		titleRight.setVisibility(ImageButton.INVISIBLE);
		// 设置标题文字
		titleText.setText(getString(R.string.title_login));
	}

	/**
	 * 初始化界面对�?
	 */

	private void initView() {
		userEdit = (EditText) findViewById(R.id.userEdit);
		pwdEdit = (EditText) findViewById(R.id.pwdEdit);
		loginBtn = (ImageButton) findViewById(R.id.loginBtn);
		loginBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// 启动登入�?�?
		if (loginBtn == v) {
			String pass = pwdEdit.getText().toString();
			String user = userEdit.getText().toString();
			if (null != user && user.length() > 0 && null != pass) {
				// 如果用户名�?�密码输入框内容不为空，发起请求
				if (null != dialog && dialog.isShowing()) {
					dialog.dismiss();
				}
				dialog = DialogFactory.showProgress(this);

				action.setUser(user);
				action.setPass(MD5Util.MD5(pass));
				action.startAction();
			}

			/*
			 * Intent intent = new Intent(); intent.setClass(this,
			 * MainActivity.class); startActivity(intent);
			 */
		}
	}

	@Override
	public void onActionSuccess(int actionId, ResponseParam ret) {
		// TODO Auto-generated method stub
		if (actionId == Global.ACTION_ID_DO_LOGIN) {
			if (null != dialog && dialog.isShowing()) {
				dialog.dismiss();
			}
			String uid = ActionDoLogin.getUserId(ret);
			if (uid.equals("0")) {
				// 登入失败的情�?
				if (null != dialog && dialog.isShowing()) {
					dialog.dismiss();
				}
				dialog = DialogFactory.showLoginFailed(this);
			} else {
				// 登入正确的情�?
				Intent intent = new Intent();
				intent.setClass(this, MainActivity.class);
				startActivity(intent);
			}
		}
	}

	@Override
	public void onActionFailed(int actionId, int httpStatus) {
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
			// 当前请求错误未知的情�?
			if (null != dialog && dialog.isShowing()) {
				dialog.dismiss();
			}
			dialog = DialogFactory.showConnectException(this);
		} else {
			// 当前网络断掉的情�?
			if (null != dialog && dialog.isShowing()) {
				dialog.dismiss();
			}
			dialog = DialogFactory.showNetworkSettingFailed(this);
		}
	}

}
