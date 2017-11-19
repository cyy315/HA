package com.cyy.assistant.phone;

import com.cyy.assistant.R;

import android.app.Dialog;
import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class PhoneDialog extends Dialog {

	//	对话框边距系数
	public final static int DIALOG_WIDTH_WEIGHT = 8;
	
	private PhoneListAdapter adapter;
	
	private ListView phoneList;
	
	public PhoneDialog(Context context, PhoneInfo[] infos) {
		super(context, R.style.DialogTheme);
		adapter = new PhoneListAdapter(context, infos);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_phone);
		//对话框大小的调整
		DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
		int padding = dm.widthPixels/DIALOG_WIDTH_WEIGHT;
		getWindow().setLayout(dm.widthPixels-padding, LayoutParams.WRAP_CONTENT);
		//listview的初始化
		phoneList = (ListView)findViewById(R.id.phoneList);
		phoneList.setAdapter(adapter);
		phoneList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				adapter.onItemClick(position);
			}
		});

	}	

}
