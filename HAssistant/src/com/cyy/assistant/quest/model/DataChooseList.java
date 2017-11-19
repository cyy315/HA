package com.cyy.assistant.quest.model;

import java.util.ArrayList;
import java.util.List;

import com.cyy.util.xml.XMLObject;

import android.util.Log;

public class DataChooseList {

	public final static String CHOOSE_LIST = "choose_list";
	public List<DataChoose> chooses;
	
	public DataChooseList(XMLObject object){
		if(object.name.equals(CHOOSE_LIST)){			
			chooses = new ArrayList<DataChoose>();
			for(XMLObject obj:object.subObjects){
				chooses.add(new DataChoose(obj));
			}
		}
	}
}
