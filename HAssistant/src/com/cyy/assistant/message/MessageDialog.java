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
 * ��Ϣ���ʶԻ���
 * 
 * @author WeiJing
 * 
 */
public class MessageDialog extends Dialog {
	//	�Ի���߾�ϵ��
	public final static int DIALOG_WIDTH_WEIGHT = 8;
	/* �绰������� */
	private TextView phoneNum;
	/* ��Ϣ������� */
	private EditText contentEdit;
	/* ���Ͱ�ť */
	private Button sendBtn;
	/* ȡ����ť */
	private Button cancelBtn;
	/* �绰���� */
	private String phone;

	/**
	 * 
	 * @param context
	 *            ҳ������
	 * @param phone
	 *            �绰����
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
 * ����SMS��Ϣ
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
