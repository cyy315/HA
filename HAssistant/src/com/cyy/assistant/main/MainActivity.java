package com.cyy.assistant.main;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cyy.assistant.R;
import com.cyy.assistant.album.AlbumActivity;
import com.cyy.assistant.app.Global;
import com.cyy.assistant.message.MessageDialog;
import com.cyy.assistant.phone.PhoneDialog;
import com.cyy.assistant.phone.PhoneInfo;
import com.cyy.assistant.quest.QuestActivity;
import com.cyy.sdk.init.ActionGetPics;
import com.cyy.sdk.util.OnActionListener;
import com.cyy.sdk.util.ResponseParam;
import com.cyy.util.dialog.DialogFactory;
import com.cyy.util.net.BaseUrl;
import com.cyy.util.net.NetImageView;
import com.cyy.util.net.NetUtil;


public class MainActivity extends Activity implements OnClickListener,
		OnActionListener {

	private TextView titleText;
	private ImageButton titleLeft;
	private ImageButton titleRight;
	/** 头部滑动页面 */
	private ViewPager viewPager;
	/** 头部滑动页面适配器 */
	private MainAdapter adapter;

	private LinearLayout quest;
	private LinearLayout album;
	private LinearLayout message;
	private LinearLayout telephone;
	private LinearLayout share;
	private LinearLayout setting;

	private Dialog dialog;

	private ActionGetPics action;

	private Button shareButton, shakebutton, commentButton;
	private String[] mPermissionList = new String[] {
			Manifest.permission.ACCESS_FINE_LOCATION,
			Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS,
			Manifest.permission.READ_PHONE_STATE,
			Manifest.permission.WRITE_EXTERNAL_STORAGE,
			Manifest.permission.SET_DEBUG_APP,
			Manifest.permission.SYSTEM_ALERT_WINDOW,
			Manifest.permission.GET_ACCOUNTS };
	private static final int REQUEST_PERM = 150;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		this.setContentView(R.layout.activity_main);

		initTitle();
		initView();
		// 初始化头部分页容器
		initPager();
		// 开始获取头部页面
		startGetPics();
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
	 * 获取头部图片的URL列表
	 */
	public void startGetPics() {
		action = new ActionGetPics(Global.ACTION_ID_GET_PICS);
		action.setOnActionListener(this);
		action.startAction();
	}

	public void initUmeng() {
		//
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
		titleText.setText(getString(R.string.title_main));
	}

	private void initView() {
		quest = (LinearLayout) findViewById(R.id.item_quest);
		album = (LinearLayout) findViewById(R.id.item_album);
		message = (LinearLayout) findViewById(R.id.item_message);
		telephone = (LinearLayout) findViewById(R.id.item_telephone);
		share = (LinearLayout) findViewById(R.id.item_share);
		setting = (LinearLayout) findViewById(R.id.item_setting);

		quest.setOnClickListener(this);
		album.setOnClickListener(this);
		message.setOnClickListener(this);
		telephone.setOnClickListener(this);
		share.setOnClickListener(this);
		setting.setOnClickListener(this);
	}

	private void initPager() {
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		adapter = new MainAdapter();

		/*
		 * View v = new View(this); v.setLayoutParams(new LayoutParams(-1,-1));
		 * v.setBackgroundColor(Color.WHITE);
		 * 
		 * View v2 = new View(this); v2.setLayoutParams(new
		 * LayoutParams(-1,-1)); v2.setBackgroundColor(Color.RED);
		 * 
		 * View v3 = new View(this); v3.setLayoutParams(new
		 * LayoutParams(-1,-1)); v3.setBackgroundColor(Color.GREEN);
		 * 
		 * adapter.addItem("第一页", v); adapter.addItem("第二页", v2);
		 * adapter.addItem("第三页", v3);
		 */

		viewPager.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == quest) {
			// 阶段测试
			Intent intent = new Intent();
			intent.setClass(this, QuestActivity.class);
			startActivity(intent);
		} else if (v == album) {
			// 学习相册
			Intent intent = new Intent();
			intent.setClass(this, AlbumActivity.class);
			startActivity(intent);
		} else if (v == message) {
			// 消息提问
			if (null != dialog) {
				if (dialog.isShowing()) {
					if (dialog.getClass() == MessageDialog.class) {
						return;
					}
					dialog.dismiss();
				}
			}
			dialog = new MessageDialog(this, "13739093390");
			dialog.show();
		} else if (v == telephone) {

			PhoneInfo[] infos = new PhoneInfo[3];
			for (int i = 0; i < infos.length; i++) {
				infos[i] = new PhoneInfo();
				infos[i].name = "电话支持" + i;
				infos[i].number = "13739093390";
			}
			// 电话支持
			if (null != dialog) {
				if (dialog.isShowing()) {
					if (dialog.getClass() == PhoneDialog.class) {
						return;
					}
					dialog.dismiss();
				}
			}
			dialog = new PhoneDialog(this, infos);
			dialog.show();
		} else if (v == share) {
			Intent intent = new Intent(MainActivity.this,
					ShareandAuthActivity.class);
			startActivity(intent);

		} else if (v == setting) {

		}
	}

	@Override
	public void onActionSuccess(int actionId, ResponseParam ret) {
		// TODO Auto-generated method stub
		if (Global.ACTION_ID_GET_PICS == actionId) {
			String[] urlArray = ActionGetPics.getPicList(ret);
			if (null != urlArray) {
				for (int i = 0; i < urlArray.length; i++) {
					NetImageView imageView = new NetImageView(this);
					imageView.setScaleType(ScaleType.FIT_XY);
					imageView.setLayoutParams(new LayoutParams(-1, -1));
					Log.d("weijing", BaseUrl.getBaseUrl() + urlArray[i]);
					imageView.setImageFromNetUrl(BaseUrl.getBaseUrl()
							+ urlArray[i]);
					adapter.addItem("第" + (i + 1) + "页", imageView);

				}
				adapter.notifyDataSetChanged();

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
