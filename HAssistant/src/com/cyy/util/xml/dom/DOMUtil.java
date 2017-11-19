package com.cyy.util.xml.dom;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.cyy.util.xml.XMLObject;

import android.util.Log;

public class DOMUtil {
	
	private XMLObject object;

	public DOMUtil(InputStream is) throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		Document document = builder.parse(is);
		object = this.parser(document.getDocumentElement());
	}
	
	public XMLObject getXMLObject(){
		return object;
	}
	
	public XMLObject parser(Element element){
		XMLObject object = new XMLObject();
		
		object.name = element.getNodeName();
		
		object.appendValue(element.getTextContent());
		
		NamedNodeMap map = element.getAttributes();
		if(null != map){
			for(int i = 0;i<map.getLength();i++){
				Attr attr = (Attr)map.item(i);
				object.addAttributes(attr.getName(), attr.getValue());
			}
		}
		
		if(element.hasChildNodes()){
			NodeList list = element.getChildNodes();
			for(int i = 0;i<list.getLength();i++){
				if(Node.ELEMENT_NODE == list.item(i).getNodeType()){
					object.addSubObject(parser((Element)list.item(i)));
				}
			}
		}
		return object;
	}
}
