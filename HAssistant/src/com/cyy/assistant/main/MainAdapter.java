package com.cyy.assistant.main;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class MainAdapter extends PagerAdapter {

	private ArrayList<PageItem> pageList;
	/**
	 * 构造方法
	 */
	
	public MainAdapter() {
		super();
		pageList = new ArrayList<PageItem>();
	}
	
	public void addItem(String title, View view){
		pageList.add(new PageItem(view,title));
	}
	
	public PageItem getItem(int position){
		return pageList.get(position);
	}
	
	public void removeItem(int position){
		pageList.remove(position);
	}
	
	public void removeAll(){
		pageList.clear();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return pageList.size();
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		((ViewPager)container).removeView(pageList.get(position).view);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		// TODO Auto-generated method stub
		return pageList.get(position).title;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		((ViewPager)container).addView(pageList.get(position).view);
		return pageList.get(position);
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == ((PageItem)arg1).view;
	}

	public class PageItem{
		public View view;
		public String title;
		/**
		 * 构造方法
		 * @param v 视图
		 * @param t 标题
		 */
		public PageItem(View v, String t){
			view = v;
			title = t;
		}
	}
}
