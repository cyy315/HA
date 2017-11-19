package com.cyy.assistant.main;

import com.cyy.assistant.R;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainWidget extends LinearLayout {

	public ImageView imageView;

	public TextView textView;

	public MainWidget(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		imageView = new ImageView(context);
		initView(context);
	}

	private void initView(Context context) {
		
		setOrientation(VERTICAL);
		imageView = new ImageView(context);
		imageView.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 1.0f));
		imageView.setBackgroundColor(Color.RED);
		imageView.setScaleType(ScaleType.FIT_CENTER);
		
		textView = new TextView(context);
		textView.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, 4.0f));
		textView.setTextAppearance(context, android.R.attr.textAppearanceMedium);
		textView.setTextColor(getResources().getColor(R.color.color_text_1));
		textView.setBackgroundColor(Color.GREEN);
		addView(imageView);
		addView(textView);
	}

}
