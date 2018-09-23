package com.treebase.database.cache;
/**
 * 缓存操作接口
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public interface myCache {
	
	public abstract boolean addKey(String key,Object value);
	
	public abstract Object getKey(String key);
	
	public abstract boolean UpdateKey(String key,Object value);
	
	public abstract boolean removeKey(String key);
}
