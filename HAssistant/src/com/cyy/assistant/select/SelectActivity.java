package com.cyy.assistant.select;
/*package com.weijing.assistant.select;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

import com.weijing.assistant.R;
import com.weijing.assistant.app.Global;
import com.weijing.assistant.quest.model.DataQuest;
import com.weijing.sdk.init.ActionGetPics;
import com.weijing.sdk.util.ResponseParam;
import com.weijing.util.dialog.DialogFactory;
import com.weijing.util.net.BaseUrl;
import com.weijing.util.net.NetImageView;
import com.weijing.util.net.NetUtil;

public class SelectActivity extends Activity {
	private TextView titleText;
	private ImageButton titleLeft;
	private ImageButton titleRight;

	private Spinner spinner;

	private ArrayAdapter<String> adapter;

	private EditText edit;

	private Button enter;

	private ActiongGetQuests action;

	private Dialog dialog;

	private DataQuest[] quests;

	private String[] title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select);
		initTitle();
		initView();
		action = new ActionGetQuests(Global.ACTION_ID_GET_QUESTS);
		action.setOnActionListener(this);
		action.startAction();
	}

	*//**
	 * 初始化标题栏
	 *//*
	private void initTitle() {
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title);
		titleText = (TextView) findViewById(R.id.title_text);
		titleLeft = (ImageButton) findViewById(R.id.title_left);
		titleRight = (ImageButton) findViewById(R.id.title_right);
		// 隐藏左右按钮
		titleLeft.setVisibility(ImageButton.VISIBLE);
		titleLeft.setImageResource(R.drawable.left);
		titleLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();

			}

		});

		titleRight.setVisibility(ImageButton.INVISIBLE);

		// 设置标题文字
		titleText.setText(getString(R.string.title_select));
	}

	public void initView() {
		spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				edit.setText(quests[arg2].questDescription);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				edit.setText("");
			}
		});
		edit = (EditText) findViewById(R.id.description);
		// 使EditText不可被编辑
		edit.setCursorVisible(false);
		edit.setFocusable(false);
		edit.setFocusableInTouchMode(false);

		enter = (Button) findViewById(R.id.enter);
		enter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});

	}

	@Override
	public void onActionSuccess(int actionId, ResponseParam ret) {
		// TODO Auto-generated method stub
		if (Global.ACTION_ID_GET_QUESTS == actionId) {
			quests = AcionGetQuests.getQuests(ret);
			if (null != quests) {
				if(title==null && quests.length>0){
					title = new String[quests.length];
				}
				for (int i = 0; i < quests.length; i++) {
					title[i] = quest[i].questName;
				}
			}
			// 重设
			adapter = new ArrayAdapter<String>(this, R.layout.item_quest, title);
			adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
			spinner.setAdapter(adapter);
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
*/