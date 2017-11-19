package com.cyy.assistant.quest.choose;

import com.cyy.assistant.R;
import com.cyy.assistant.quest.model.DataChoice;
import com.cyy.assistant.quest.model.DataChoose;
import com.cyy.assistant.quest.model.DataChooseList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ChooseAdapter extends BaseExpandableListAdapter {

	/** 布局反射器实例 */
	private LayoutInflater inflater;

	private DataChooseList chooseList;

	public ChooseAdapter(Context context, DataChooseList chooseList) {
		this.chooseList = chooseList;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return chooseList.chooses.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return chooseList.chooses.get(groupPosition).choices.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return chooseList.chooses.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return chooseList.chooses.get(groupPosition).choices.get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		GroupViewHolder holder;
		if (null == convertView) {
			convertView = inflater.inflate(R.layout.listitem_choose, null);
			holder = new GroupViewHolder();
			holder.index = (TextView) convertView.findViewById(R.id.index);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			holder.description = (TextView) convertView
					.findViewById(R.id.description);
			holder.answer = (TextView) convertView.findViewById(R.id.answer);
			convertView.setTag(holder);
		} else {
			holder = (GroupViewHolder) convertView.getTag();
		}
		DataChoose choose = (DataChoose) getGroup(groupPosition);
		holder.index.setText("" + (groupPosition + 1));
		holder.title.setText(choose.title);
		holder.description
				.setText(choose.description);
		holder.answer.setText("未回答");
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ChildViewHoler holder;
		if (null == convertView) {
			convertView = inflater
					.inflate(R.layout.listitem_choose_child, null);
			holder = new ChildViewHoler();
			holder.tag = (TextView) convertView.findViewById(R.id.child_tag);
			holder.description = (TextView) convertView
					.findViewById(R.id.child_description);
			convertView.setTag(holder);
		} else {
			holder = (ChildViewHoler) convertView.getTag();
		}
		DataChoice choice = (DataChoice)getChild(groupPosition,childPosition);
		holder.tag.setText(choice.tag);
		holder.description.setText(choice.description);
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

	public class GroupViewHolder {
		public TextView index;
		public TextView title;
		public TextView description;
		public TextView answer;
	}

	public class ChildViewHoler {
		public TextView tag;
		public TextView description;
	}
}
