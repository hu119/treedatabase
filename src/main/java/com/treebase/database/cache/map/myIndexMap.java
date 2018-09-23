package com.treebase.database.cache.map;

import java.util.concurrent.ConcurrentHashMap;

import com.treebase.database.cache.myCache;
import com.treebase.database.main.index;
/**
 * 树型列式数据库单进程服务ID对象缓存
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class myIndexMap implements myCache{
	static ConcurrentHashMap<String,index>indmap=new ConcurrentHashMap<String,index>();
	
	public boolean addKey(String key, Object value) {
		// TODO Auto-generated method stub
		if(value instanceof index){
			indmap.put(key, (index)value);
			return true;
		}
		return false;
	}

	public Object getKey(String key) {
		// TODO Auto-generated method stub
		return indmap.get(key);
	}

	public boolean UpdateKey(String key, Object value) {
		// TODO Auto-generated method stub
		return addKey(key,value);
	}

	public boolean removeKey(String key) {
		// TODO Auto-generated method stub
		indmap.remove(key);
		return false;
	}


	

}