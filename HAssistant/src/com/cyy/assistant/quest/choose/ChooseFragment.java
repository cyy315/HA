package com.cyy.assistant.quest.choose;

import com.cyy.assistant.R;
import com.cyy.assistant.quest.model.DataChooseList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class ChooseFragment extends Fragment {

	private ExpandableListView listView;
	
	private ChooseAdapter adapter;
	
	private DataChooseList chooseList;
	
	public ChooseFragment(DataChooseList chooseList){
		this.chooseList = chooseList;
	}
	
	/*private DataChooseList chooseList;
	
	private ChooseFragment(DataChooseList chooseList){
		this.chooseList = chooseList;	
	}*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View root = inflater.inflate(R.layout.fragment_choose, container, false);
		listView = (ExpandableListView)root.findViewById(R.id.listview);
		adapter = new ChooseAdapter(this.getActivity(),chooseList);
		listView.setAdapter(adapter);
		return root;
	}

}
