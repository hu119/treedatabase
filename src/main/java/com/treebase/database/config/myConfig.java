package com.treebase.database.config;

import java.util.List;
/**
 * 配置文件操作基类
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public abstract class myConfig {
	
	String filepath=null;
	public myConfig(String filepath){
		this.filepath=filepath;
	}
	
	public abstract String getKey(String key,String defaultstr);
	
	public abstract List<Object> getList(String key);
	
	public abstract boolean setKey(String key,String value);
	
	public abstract boolean addKey(String key,String value);
	
	
}
