package com.cyy.assistant.phone;

import com.cyy.assistant.R;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PhoneListAdapter extends BaseAdapter {

	private PhoneInfo[] infos;
	
	private Context context;
	
	private LayoutInflater inflater;//反射器获取视图
	
	public PhoneListAdapter(Context context,PhoneInfo[] infos){
		this.context = context;
		this.infos = infos;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		
		return infos.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return infos[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item_phone,null);
			holder = new ViewHolder();
			holder.phoneName = (TextView) convertView.findViewById(R.id.phoneName);
			holder.phoneNumber = (TextView) convertView.findViewById(R.id.phoneNumber);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.phoneName.setText(infos[position].name);
		holder.phoneNumber.setText(infos[position].number);
	
		return convertView;
	}
	
	public class ViewHolder{
		public TextView phoneName;
		public TextView phoneNumber;
	}
	
	public void onItemClick(int position){
		Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+infos[position].number));
		context.startActivity(intent);
		
	}

}
