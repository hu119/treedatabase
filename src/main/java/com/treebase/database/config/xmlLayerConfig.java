package com.treebase.database.config;

import java.util.List;

import org.apache.commons.configuration.AbstractHierarchicalFileConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

import com.treebase.tools.string.mystring;

/**
 * 配置文件操作 xml文件操作
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class xmlLayerConfig extends myConfig{

	public xmlLayerConfig(String filepath) {
		super(filepath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getKey(String key,String defaultstr) {
		if(filepath==null)
			throw new NullPointerException("filepath is null");
				
		Configuration config;
		try {
			config = new XMLConfiguration(filepath);
			String name = config.getString(key);
			if(mystring.isEmpty(name))name=defaultstr;
		    return name;
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Object> getList(String key) {
		if(filepath==null)
			throw new NullPointerException("filepath is null");
		Configuration config;
		try {
			config = new XMLConfiguration(filepath);
			return config.getList(key);
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean setKey(String key, String value) {
		if(filepath==null)
			throw new NullPointerException("filepath is null");
		
		
		Configuration config;
		try {
			config = new XMLConfiguration(filepath);
			config.setProperty(key,value);
		    ((AbstractHierarchicalFileConfiguration) config).save();
		    return true;
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    	    
		return false;
	}

	@Override
	public boolean addKey(String key, String value) {
		if(filepath==null)
			throw new NullPointerException("filepath is null");
		
		
		Configuration config;
		try {
			config = new XMLConfiguration(filepath);
			config.addProperty(key,value);
		    ((AbstractHierarchicalFileConfiguration) config).save();
		    return true;
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		return false;
	}
}
