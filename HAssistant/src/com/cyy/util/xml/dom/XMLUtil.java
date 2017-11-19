package com.cyy.util.xml.dom;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.util.Log;

public class XMLUtil {
	public XMLUtil(InputStream is) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(is);
		document.getDocumentElement();
		Element quest = document.getDocumentElement();
		Log.d("weijing", quest.getAttribute("description"));
		NodeList nodes = quest.getChildNodes();
		for(int i=0; i<nodes.getLength();i++){
			if(nodes.item(i).getNodeType() == Node.ELEMENT_NODE){
				Log.d("weijing", nodes.item(i).getFirstChild().getNodeValue());
			}
		}
		
		/*Log.d("weijing", ""+nodes.getLength());*/
	}
}
