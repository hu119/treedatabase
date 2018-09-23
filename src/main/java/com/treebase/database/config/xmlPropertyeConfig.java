package com.treebase.database.config;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.treebase.tools.string.mystring;


/**
 * 配置文件操作 xml文件操作
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class xmlPropertyeConfig  extends myConfig{

	public xmlPropertyeConfig(String filepath) {
		super(filepath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getKey(String key,String defaultstr) {
		if(filepath==null||filepath.length()==0)
			throw new NullPointerException("filepath is null");
		String result="";
		
		try {
			SAXReader saxReader = new SAXReader();
			File fileConf = new File(filepath);
			Document docConf = saxReader.read(fileConf);
			Element xmlRoot = docConf.getRootElement();
			
	        Element xmlName = null, xmlVal = null;
	        List<Element> xmlPropertyList = xmlRoot.elements("property");
	        for (Element xmlProperty : xmlPropertyList) {
	        
	        	xmlName = xmlProperty.element("name");
	        	if (xmlName == null) continue;
	        	
	        	if (xmlName.getText().equalsIgnoreCase(key) == true) {
	        		xmlVal=xmlProperty.element("value");
	        		result=xmlVal.getText();
	        		
	        		break;
	        	}
	        	xmlName = null;
	        }
		}
		catch (Exception e) {
			 e.printStackTrace();
	
		}
		if(mystring.isEmpty(result))result=defaultstr;
		return result;
	}

	@Override
	public List<Object> getList(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setKey(String key, String value) {
		if(filepath==null||filepath.length()==0)
			throw new NullPointerException("filepath is null");
		try {
			SAXReader saxReader = new SAXReader();
			File fileConf = new File(filepath);
			Document docConf = saxReader.read(fileConf);
			Element xmlRoot = docConf.getRootElement();
			
	        Element xmlName = null, xmlVal = null;
	        List<Element> xmlPropertyList = xmlRoot.elements("property");
	        for (Element xmlProperty : xmlPropertyList) {
	        
	        	xmlName = xmlProperty.element("name");
	        	if (xmlName == null) continue;
	        	
	        	if (xmlName.getText().equalsIgnoreCase(key) == true) {
	        		
	        		xmlVal = xmlProperty.element("value");
	        		if (xmlVal == null) {
	        			xmlVal = xmlProperty.addElement("value");
	        		}
	        		xmlVal.setText(value);
	        		break;
	        	}
	        	xmlName = null;
	        }
	        
	        if (xmlName == null) {
	        	Element xmlProperty = xmlRoot.addElement("property");
	        	xmlName = xmlProperty.addElement("name");
	        	xmlName.setText(key);
	        	
	        	xmlVal = xmlProperty.addElement("value");
	        	xmlVal.setText(value);
	        	System.out.println("run.xmlProperty=" + xmlProperty);
	        }
	        
//	        System.out.println("run.docConf=" + docConf);
	        
	        XMLWriter xmlWriter=new XMLWriter(new java.io.FileWriter(filepath)); 
	        xmlWriter.write(docConf); 
	        xmlWriter.close();
	        return true;
		}
		catch (Exception e) {
			// e.printStackTrace();
	
		}
		return false;
	}

	@Override
	public boolean addKey(String key, String value) {
		return setKey(key,value);
	}
}
