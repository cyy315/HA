package com.cyy.util.xml.sax;

import java.io.IOException;
import java.io.InputStream;
import java.util.Stack;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.cyy.util.xml.XMLObject;

import android.util.Log;

public class SAXUtil extends DefaultHandler {

	private SAXParser parser;
	
	private Stack<XMLObject> stack = null;
	
	public XMLObject getXMLObject(){
		if(stack.size()==1){
			return stack.lastElement();
		}
		return null;
	}
	
	public SAXUtil(InputStream is) throws ParserConfigurationException, SAXException, IOException{
		stack = new Stack<XMLObject>();
		parser = SAXParserFactory.newInstance().newSAXParser();
		parser.parse(is, this);
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		XMLObject object = new XMLObject();
		object.setName(qName);
		for(int i = 0; i<attributes.getLength();i++){
			object.addAttributes(attributes.getQName(i), attributes.getValue(i));
		}
		stack.push(object);
		/*Log.d("weijing", "+++++++++START+++++++uri="+uri+" localName="+localName+" qName="+qName);
		for(int i = 0;i<attributes.getLength();i++){
			Log.d("weijing", "attr: qName="+attributes.getQName(i)+" value="+attributes.getValue(i));
		}*/
		super.startElement(uri, localName, qName, attributes);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		if(stack.size()>1){
			XMLObject temp = stack.pop();
			stack.lastElement().addSubObject(temp);
		}
/*		Log.d("weijing", "========END=======uri="+uri+" localName="+localName+" qName="+qName);
*/		super.endElement(uri, localName, qName);
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		stack.lastElement().appendValue(new String(ch,start,length));
/*		Log.d("weijing", "XXXXXXXXcharactersXXXXXXXXXXX"+new String(ch, start, length));
*/		super.characters(ch, start, length);
	}
	
	
}
