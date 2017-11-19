package com.cyy.assistant.quest.model;

import com.cyy.util.xml.XMLObject;

public class DataQuest {
	public final static String KEY_QUEST = "quest";
	public final static String KEY_ID = "id";
	public final static String KEY_TITLE = "title";
	public final static String KEY_DESCRIPTION = "description";
	
	public String id;
	public String title;
	public String description;
	public DataChooseList chooseList;
	
	public DataQuest(XMLObject object){
		if(object.name.equals(KEY_QUEST)){
			id = object.attributes.get(KEY_ID);
			title = object.attributes.get(KEY_TITLE);
			description = object.attributes.get(KEY_DESCRIPTION);
			chooseList = new DataChooseList(object.subObjects.get(0));
		}
		
	}
}
