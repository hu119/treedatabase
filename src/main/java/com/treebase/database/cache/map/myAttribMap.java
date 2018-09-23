package com.treebase.database.cache.map;

import java.util.concurrent.ConcurrentHashMap;

import com.treebase.database.cache.myCache;
import com.treebase.database.main.attrib;
/**
 * 树型列式数据库单进程服务属性对象缓存
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class myAttribMap implements myCache{

	static ConcurrentHashMap<String,attrib>attmap=new ConcurrentHashMap<String,attrib>();
	
	public boolean addKey(String key, Object value) {
		// TODO Auto-generated method stub
		if(value instanceof attrib){
			attmap.put(key, (attrib)value);
			return true;
		}
		return false;
	}

	public Object getKey(String key) {
		return attmap.get(key);
	}

	public boolean UpdateKey(String key, Object value) {
		// TODO Auto-generated method stub
		return addKey(key,value);
	}

	public boolean removeKey(String key) {
		// TODO Auto-generated method stub
		attmap.remove(key);
		return true;
			
	}

	

}
