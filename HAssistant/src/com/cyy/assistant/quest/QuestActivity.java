package com.cyy.assistant.quest;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.cyy.assistant.R;
import com.cyy.assistant.quest.answer.AnswerFragment;
import com.cyy.assistant.quest.choose.ChooseFragment;
import com.cyy.assistant.quest.coding.CodingFragment;
import com.cyy.assistant.quest.model.DataQuest;
import com.cyy.util.xml.XMLObject;
import com.cyy.util.xml.dom.DOMUtil;
import com.cyy.util.xml.sax.SAXUtil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class QuestActivity extends FragmentActivity {

	/** 试题ID *//*
	public final static String PARAM_NAME_QUESTID = "questId";
	*//** 试题文件URL *//*
	public final static String PARAM_NAME_URL = "questFileUrl";
	*//** 下载操作ID *//*
	public final static int ACTION_ID_DOWNLOAD = 0x50;*/

	private String questId;

	private String questFileUrl;

	private TextView titleText;
	private ImageButton titleLeft;
	private ImageButton titleRight;
	/** 滑动页面容器的适配器 */
	private QuestPagerAdapter adapter;

	/** 滑动页面容器 */
	private ViewPager viewPager;

	private ArrayList<PageItem> pageItems;

	private RadioGroup radioGroup;

	private DataQuest quest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_quest);

		/*Intent intent = getIntent();
		questId = intent.getExtras().getString(PARAM_NAME_QUESTID);
		questFileUrl = intent.getExtras().getString(PARAM_NAME_URL);*/

		/*DownloadAction action = new DownloadAciton(ACTION_ID_DOWNLOAD,
				getCacheDir().toString(), questFileUrl);
		action.setOnDownloadListener(new OnDownloadListener(){
			
		});*/

		initTitle();
		initDocument();
		initPageItems();
		initPageList();
		initRadioGroup();

	}

	private void initDocument() {

		try {
			/* SAXUtil util = new SAXUtil(getAssets().open("test_1.xml")); */
			DOMUtil util = new DOMUtil(getAssets().open("test_1.xml"));
			XMLObject obj = util.getXMLObject();
			quest = new DataQuest(obj);
			/*
			 * String test =
			 * obj.subObjects.get(0).subObjects.get(0).attributes.get
			 * ("description"); Log.d("weijing", test);
			 */

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		titleLeft.setVisibility(ImageButton.INVISIBLE);
		titleRight.setVisibility(ImageButton.INVISIBLE);
		// 设置标题文字
		titleText.setText(getString(R.string.title_quest));
	}

	private void initPageItems() {
		pageItems = new ArrayList<PageItem>();

		pageItems.add(new PageItem((RadioButton) findViewById(R.id.chooseItem),
				new ChooseFragment(quest.chooseList)));

		pageItems.add(new PageItem((RadioButton) findViewById(R.id.answerItem),
				new AnswerFragment()));
		pageItems.add(new PageItem((RadioButton) findViewById(R.id.codingItem),
				new CodingFragment()));

	}

	private void initPageList() {
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		adapter = new QuestPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(adapter);

		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				/* Log.d("weijing","onPageSelected"); */
				int i = 0;
				for (PageItem item : pageItems) {
					if (i == arg0) {
						pageItems.get(i).button.setChecked(true);
					} else {
						pageItems.get(i).button.setChecked(false);
					}
					i++;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				/* Log.d("weijing","onPageScrolled"); */
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				/* Log.d("weijing","onPageScrollStateChanged"); */
			}
		});

	}

	private void initRadioGroup() {
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				for (int i = 0; i < pageItems.size(); i++) {
					if (pageItems.get(i).button.getId() == checkedId) {
						viewPager.setCurrentItem(i);
						break;
					}
				}

			}
		});
	}

	public class QuestPagerAdapter extends FragmentPagerAdapter {

		public QuestPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public android.support.v4.app.Fragment getItem(int position) {
			// TODO Auto-generated method stub
			return pageItems.get(position).fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return pageItems.size();
		}

	}

	public class PageItem {
		public RadioButton button;
		public Fragment fragment;

		public PageItem(RadioButton button, Fragment fragment) {
			this.button = button;
			this.fragment = fragment;
		}
	}
}
