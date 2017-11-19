package com.cyy.assistant.album;

import java.io.File;
import java.util.ArrayList;

import com.cyy.assistant.R;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AlbumAdapter extends BaseAdapter {

	private ArrayList<File> imageList;
	/**布局反射器实例*/
	private LayoutInflater inflater;
	
	private File dirFile;
	
	public AlbumAdapter(Context context,File dirFile){
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.dirFile = dirFile;
		loadImageFile();
	}
	
	public void loadImageFile(){
		if(null == imageList){
			imageList = new ArrayList<File>();
		} else {
			imageList.clear();
		}
		for(File f: dirFile.listFiles()){
			String extention = getExtention(f.getName());
			if(null != extention && extention.equals("png")){
				imageList.add(f);
			}
			
		}
		notifyDataSetChanged();
	}
	
	public String getExtention(String filename) {
		if (null != filename && filename.length() > 0) {
			int i = filename.lastIndexOf(".");
			if (i > -1 && i < filename.length()) {
				return filename.substring(i+1);
			}
		}
		return null;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return imageList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return imageList.get(position);
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
		if(null == convertView){
			convertView = inflater.inflate(R.layout.griditem_album, null);
			holder = new ViewHolder();
			holder.image = (ImageView)convertView.findViewById(R.id.image);
			holder.title = (TextView)convertView.findViewById(R.id.title);
			convertView.setTag(holder);
		}else{
			holder =(ViewHolder)convertView.getTag();
		}
		holder.image.setImageURI(Uri.fromFile(imageList.get(position)));
		holder.title.setText(imageList.get(position).getName());
		return convertView;
	}

	public class ViewHolder{
		public ImageView image;
		public TextView title;
	}
}
