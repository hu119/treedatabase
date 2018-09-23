package com.treebase.database.config;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;

import com.treebase.tools.string.mystring;

/**
 * 配置文件操作 属性类型
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class ropertiesConfig extends myConfig{

	public ropertiesConfig(String filepath) {
		super(filepath);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getKey(String key,String defaultstr) {
		if(filepath==null)
			throw new NullPointerException("filepath is null");
		Properties props = new Properties();
        try {
        	InputStream in = new BufferedInputStream (new FileInputStream(filepath));
        	props.load(in);
        	String value = props.getProperty (key);
        	System.out.println(key+value);
        	if(mystring.isEmpty(value))value=defaultstr;
        	return value;
        } catch (Exception e) {
        	e.printStackTrace();
        	return null;
        }
	}

	@Override
	public List<Object> getList(String key) {
		if(filepath==null)
			throw new NullPointerException("filepath is null");
		
		return null;
	}

	@Override
	public boolean setKey(String key, String value) {
		if(filepath==null)
			throw new NullPointerException("filepath is null");
		Properties prop = new Properties();
		try {
			 InputStream fis = new FileInputStream(filepath);
		    //从输入流中读取属性列表（键和元素对）
		    prop.load(fis);
		    //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
		    //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
		    OutputStream fos = new FileOutputStream(filepath);
		    prop.setProperty(key, value);
		    //以适合使用 load 方法加载到 Properties 表中的格式，
		    //将此 Properties 表中的属性列表（键和元素对）写入输出流
		    prop.store(fos, "Update '" + key + "' value");
		} catch (IOException e) {
			System.err.println("Visit "+filepath+" for updating "+key+" value error");
			return false;
		}
		
		return true;
	}

	@Override
	public boolean addKey(String key, String value) {
		if(filepath==null)
			throw new NullPointerException("filepath is null");
		return setKey(key,value);
	}
}
