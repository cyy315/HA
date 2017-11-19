package com.cyy.util.xml;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLObject implements Serializable {

	/**
	 * 序列化ID
	 */
	private static final long serialVersionUID = 6225771120463561887L;

	public String name = null;

	public String value = null;

	public Map<String, String> attributes = null;

	public List<XMLObject> subObjects = null;

	public void setName(String name) {
		this.name = name;
	}

	public void appendValue(String value){
		this.value += value;
	}
	
	public void addAttributes(String name, String value){
		/**懒人构造*/
		if(null == attributes){
			attributes = new HashMap<String, String>();			
		}
		attributes.put(name, value);
	}
	
	public void addSubObject(XMLObject obj){
		if(null == subObjects){
			subObjects = new ArrayList<XMLObject>();
		}
		subObjects.add(obj);
	}
}
