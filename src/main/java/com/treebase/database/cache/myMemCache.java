package com.treebase.database.cache;
/**
 * 树型列式数据库内存缓存接口
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public abstract class myMemCache {
	
	public abstract boolean addKey(String key,String value);
	
	public abstract String getUpdateKey(String key,String value);
	
	public abstract boolean removeKey(String key);
}
