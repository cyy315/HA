package com.cyy.assistant.message;

import android.app.ActionBar.LayoutParams;

import com.cyy.assistant.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 消息提问对话框
 * 
 * @author WeiJing
 * 
 */
public class MessageDialog extends Dialog {
	//	对话框边距系数
	public final static int DIALOG_WIDTH_WEIGHT = 8;
	/* 电话号码组件 */
	private TextView phoneNum;
	/* 消息内容组件 */
	private EditText contentEdit;
	/* 发送按钮 */
	private Button sendBtn;
	/* 取消按钮 */
	private Button cancelBtn;
	/* 电话号码 */
	private String phone;

	/**
	 * 
	 * @param context
	 *            页面正文
	 * @param phone
	 *            电话号码
	 */
	public MessageDialog(Context context, String phone) {
		super(context, R.style.DialogTheme);
		this.phone = phone;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_message);
		
		DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
		int padding = dm.widthPixels/DIALOG_WIDTH_WEIGHT;
		getWindow().setLayout(dm.widthPixels-padding, LayoutParams.WRAP_CONTENT);
		
		phoneNum = (TextView) findViewById(R.id.phoneNum);
		contentEdit = (EditText) findViewById(R.id.contentEdit);
		sendBtn = (Button) findViewById(R.id.sendBtn);
		cancelBtn = (Button) findViewById(R.id.cancelBtn);

		phoneNum.setText(phone);
		cancelBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});

		sendBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(sendSMS(phone,contentEdit.getText().toString())){
					dismiss();
				}
			}
		});
	}
/**
 * 发送SMS消息
 * @param phone
 * @param content
 * @return
 */
	private boolean sendSMS(String phone, String content) {
		if (phone != null && content != null && !content.trim().equals("")) {
			SmsManager smsmanager = SmsManager.getDefault();
			smsmanager.sendTextMessage(phone, null, contentEdit.getText()
					.toString(), null, null);
			Toast.makeText(
					getContext(),
					getContext()
							.getString(R.string.dialog_message_send_success)
							+ phone, Toast.LENGTH_SHORT).show();
			return true;
		}
		Toast.makeText(getContext(),
				getContext().getString(R.string.dialog_message_send_no_input),
				Toast.LENGTH_SHORT).show();
		return false;
	}

}
