package com.treebase.database.factory;
/**
 * 树型列式数据库  工厂基类
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public interface myFactory {

	public abstract Object getInstance(String key);
}
