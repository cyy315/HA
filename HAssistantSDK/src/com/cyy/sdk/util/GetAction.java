package com.cyy.sdk.util;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public final class GetAction implements Runnable {

	private final static int MSG_TYPE_SUCCESS = 0x10;

	private final static int MSG_TYPE_FAILED = 0x20;

	private final static int MSG_TYPE_EXCEPTION = 0x30;

	private final static String MSG_KEY_PARAM = "param";

	private final static String MSG_KEY_EXCEPTION = "exception";

	private int id;

	private String url;

	private OnActionListener listener;

	private Handler handler;

	private GetParam param;

	public GetAction(int actionId, String baseUrl) {
		this.url = baseUrl;
		this.id = actionId;
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == id) {
					switch (msg.arg1) {
					case MSG_TYPE_SUCCESS:
						// ��óɹ���Ϣ�����
						if (null != listener) {
							listener.onActionSuccess(id, (ResponseParam) msg
									.getData().getSerializable(MSG_KEY_PARAM));
						}
						return;
					case MSG_TYPE_FAILED:
						if (null != listener) {
							listener.onActionFailed(id, msg.arg2);
						}
						return;
					case MSG_TYPE_EXCEPTION:
						if (null != listener) {
							listener.onActionException(id, msg.getData()
									.getString(MSG_KEY_EXCEPTION));
						}
						return;
					}
				}
				super.handleMessage(msg);
			}

		};
	}

	public void setParam(GetParam param) {
		this.param = param;
	}

	public void setOnActionListener(OnActionListener l) {
		listener = l;
	}

	public void startAction() {
		// ����һ���̣߳�ִ�б���
		new Thread(this).start();
	}

	/* run��������������һ���߳��� */
	@Override
	public void run() {
		try {
			Log.d("cyy", "get param=" + param.toString());
			HttpGet get = new HttpGet(url + param.toString());
			Log.d("cyy", url + param.toString());
			HttpResponse httpResponse = new DefaultHttpClient().execute(get);
			int httpCode = httpResponse.getStatusLine().getStatusCode();
			Log.d("cyy", ""+httpCode);
			// �ж�HTTP�����Ƿ�ɹ�����
			if (httpCode == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(httpResponse.getEntity());
				Log.d("cyy", "ret=" + result);
				ResponseParam param = new ResponseParam(result);
				// ���ͷ��ز��������߳�
				Message msg = new Message();
				msg.what = id;
				msg.arg1 = MSG_TYPE_SUCCESS;
				Bundle data = new Bundle();
				data.putSerializable(MSG_KEY_PARAM, param);
				msg.setData(data);
				handler.sendMessage(msg);
			} else {
				// HTTP״̬��Ϊʧ�ܵ����
				Message msg = new Message();
				msg.what = id;
				msg.arg1 = MSG_TYPE_FAILED;
				msg.arg2 = httpCode;
				handler.sendMessage(msg);
			}
		} catch (Exception e) {
			// ��������з����쳣�����
			Message msg = new Message();
			msg.what = id;
			msg.arg1 = MSG_TYPE_EXCEPTION;
			Bundle data = new Bundle();
			data.putString(MSG_KEY_EXCEPTION, e.getMessage());
			msg.setData(data);
			handler.sendMessage(msg);
		}

	}
}
