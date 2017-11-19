package com.cyy.util.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;

public class NetImageView extends ImageView {
	/** 成功返回的图片的URL */
	private final static String MSG_KEY_IMG_URL = "url";
	/** 成功下载 */
	private final static int MSG_SUCCESS = 0;
	/** 下载失败 */
	private final static int MSG_FAILED = 1;
	/** 图片队列 */
	private final static Map<String, Bitmap> bitmaps = new HashMap<String, Bitmap>();
	/** 消息句柄 */
	private Handler handler;

	/**
	 * 构造方法
	 * 
	 * @param context
	 */
	public NetImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * 构造方法
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public NetImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 构造方法
	 * 
	 * @param context
	 * @param attrs
	 */
	public NetImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 二级构造方法
	 */
	private void init() {
		// 初始化消息句柄
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case MSG_SUCCESS:
					// 设置赋入的位图对象
					setImageBitmap(bitmaps.get(msg.getData().getString(
							MSG_KEY_IMG_URL)));
					break;
				case MSG_FAILED:
					// 设置图片为空
					setImageBitmap(null);
					break;
				}
			}
		};
	}

	/**
	 * 通过URL设置网络图片
	 * 
	 * @param imgurl
	 */
	public void setImageFromNetUrl(final String imgurl) {
		if (bitmaps.containsKey(imgurl)) {
			setImageBitmap(bitmaps.get(imgurl));
		} else {
			new Thread(){
				
				@Override
				public void run(){
					try{
						//开启网络连接输入法
						HttpURLConnection conn = (HttpURLConnection) new URL(imgurl).openConnection();
						conn.setDoInput(true);
						conn.connect();
						InputStream is = conn.getInputStream();
						//将输入流转换为bitmap对象
						Bitmap bitmap = BitmapFactory.decodeStream(is);
						//关闭输入流
						is.close();
						if(null!=bitmap){
							//若bitmap对象不为空
							if(bitmaps.size()>10){
								//bitmap对象列表超过10张位图，清空
								bitmaps.clear();
							}
							//塞入新位图
							bitmaps.put(imgurl, bitmap);
							//发送成功消息
							Message msg = new Message();
							msg.what = MSG_SUCCESS;
							Bundle data = new Bundle();
							data.putString(MSG_KEY_IMG_URL, imgurl);
							msg.setData(data);
							handler.sendMessage(msg);
						}else{
							//发送失败消息
							Message msg = new Message();
							msg.what = MSG_FAILED;
							handler.sendMessage(msg);
						}
					}catch(OutOfMemoryError e){
						//清空Bitmap对象列表
						bitmaps.clear();
						//发送失败消息
						Message msg = new Message();
						msg.what = MSG_FAILED;
						handler.sendMessage(msg);
						
					}catch (IOException e) {
						// 发送失败消息
						Message msg = new Message();
						msg.what = MSG_FAILED;
						handler.sendMessage(msg);
					}
				}
			}.start();
		}
	}
}
