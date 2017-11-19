package com.cyy.assistant.album;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import com.cyy.assistant.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AlbumActivity extends Activity {

	private final static int CAMERA_ATION_REQUEST_ID = 0x12;
	private GridView gridView;

	private AlbumAdapter adapter;

	private TextView titleText;
	private ImageButton titleLeft;
	private ImageButton titleRight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_album);

		initTitle();
		gridView = (GridView) findViewById(R.id.gridview);
		adapter = new AlbumAdapter(this, this.getCacheDir());
		Log.d("weijing", getCacheDir().toString());
		gridView.setAdapter(adapter);
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
		titleLeft.setVisibility(ImageButton.VISIBLE);
		titleLeft.setImageResource(R.drawable.left);
		titleLeft.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();

			}

		});

		titleRight.setImageResource(R.drawable.photo);
		titleRight.setVisibility(ImageButton.VISIBLE);
		titleRight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivityForResult(intent, CAMERA_ATION_REQUEST_ID);
			}

		});

		// 设置标题文字
		titleText.setText(getString(R.string.title_album));
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode == CAMERA_ATION_REQUEST_ID) {
			if (resultCode == Activity.RESULT_OK) {
				String name = DateFormat.format("yyyyMMdd_hhmmss",
						Calendar.getInstance(Locale.CHINA))
						+ ".jpg";
				Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
				Bitmap bitmap = (Bitmap)data.getExtras().get("data");
				
				String fileName = getCacheDir()+"/"+name;
				try {
					FileOutputStream out = new FileOutputStream(fileName);
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
					out.flush();
					out.close();
					adapter.loadImageFile();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				return;
			} else {
				Toast.makeText(this, "返回失败", Toast.LENGTH_SHORT);
				return;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);

	}

}
