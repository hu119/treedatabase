package com.treebase.database.cache.map;

import java.util.concurrent.ConcurrentHashMap;

import com.treebase.database.cache.myCache;
import com.treebase.database.conf.define;
import com.treebase.database.main.node;
import com.treebase.tools.string.mystring;
/**
 * 树型列式数据库单进程服务树节点对象缓存
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class myNodeMap implements myCache{
	static ConcurrentHashMap<String,node>ndmap=new ConcurrentHashMap<String,node>();
	static ConcurrentHashMap<String,String>childrenmap=new ConcurrentHashMap<String,String>();
	
	public boolean addKey(String key, Object value) {
		// TODO Auto-generated method stub
		if(mystring.isEmpty(key))return false;
		if(value instanceof String){
			childrenmap.put(key, (String)value);
		}else if(value instanceof node){
			ndmap.put(key, (node)value);
		}else{
			return false;
		}
		return true;
	}

	public boolean UpdateKey(String key, Object value) {
		// TODO Auto-generated method stub
		if(mystring.isEmpty(key))return false;
		
		return addKey(key,value);
	}

	public boolean removeKey(String key) {
		if(mystring.isEmpty(key))return false;
		// TODO Auto-generated method stub
		if(key.indexOf(define.children_mark)!=-1)childrenmap.remove(key);
		else ndmap.remove(key);
		return true;
	}

	public Object getKey(String key) {
		if(mystring.isEmpty(key))return null;
		// TODO Auto-generated method stub
		if(key.indexOf(define.children_mark)!=-1){
			return childrenmap.get(key);
		}else{
			return ndmap.get(key);
		}
	}
	

}
