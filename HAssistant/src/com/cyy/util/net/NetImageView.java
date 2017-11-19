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
	/** �ɹ����ص�ͼƬ��URL */
	private final static String MSG_KEY_IMG_URL = "url";
	/** �ɹ����� */
	private final static int MSG_SUCCESS = 0;
	/** ����ʧ�� */
	private final static int MSG_FAILED = 1;
	/** ͼƬ���� */
	private final static Map<String, Bitmap> bitmaps = new HashMap<String, Bitmap>();
	/** ��Ϣ��� */
	private Handler handler;

	/**
	 * ���췽��
	 * 
	 * @param context
	 */
	public NetImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * ���췽��
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
	 * ���췽��
	 * 
	 * @param context
	 * @param attrs
	 */
	public NetImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * �������췽��
	 */
	private void init() {
		// ��ʼ����Ϣ���
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case MSG_SUCCESS:
					// ���ø����λͼ����
					setImageBitmap(bitmaps.get(msg.getData().getString(
							MSG_KEY_IMG_URL)));
					break;
				case MSG_FAILED:
					// ����ͼƬΪ��
					setImageBitmap(null);
					break;
				}
			}
		};
	}

	/**
	 * ͨ��URL��������ͼƬ
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
						//���������������뷨
						HttpURLConnection conn = (HttpURLConnection) new URL(imgurl).openConnection();
						conn.setDoInput(true);
						conn.connect();
						InputStream is = conn.getInputStream();
						//��������ת��Ϊbitmap����
						Bitmap bitmap = BitmapFactory.decodeStream(is);
						//�ر�������
						is.close();
						if(null!=bitmap){
							//��bitmap����Ϊ��
							if(bitmaps.size()>10){
								//bitmap�����б���10��λͼ�����
								bitmaps.clear();
							}
							//������λͼ
							bitmaps.put(imgurl, bitmap);
							//���ͳɹ���Ϣ
							Message msg = new Message();
							msg.what = MSG_SUCCESS;
							Bundle data = new Bundle();
							data.putString(MSG_KEY_IMG_URL, imgurl);
							msg.setData(data);
							handler.sendMessage(msg);
						}else{
							//����ʧ����Ϣ
							Message msg = new Message();
							msg.what = MSG_FAILED;
							handler.sendMessage(msg);
						}
					}catch(OutOfMemoryError e){
						//���Bitmap�����б�
						bitmaps.clear();
						//����ʧ����Ϣ
						Message msg = new Message();
						msg.what = MSG_FAILED;
						handler.sendMessage(msg);
						
					}catch (IOException e) {
						// ����ʧ����Ϣ
						Message msg = new Message();
						msg.what = MSG_FAILED;
						handler.sendMessage(msg);
					}
				}
			}.start();
		}
	}
}
