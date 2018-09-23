package com.treebase.database.factory;

import com.treebase.database.conf.define;
import com.treebase.database.datasource.mysql.mysqlConnect;
/**
 * 树型列式数据库  工厂类 管理数据源连接
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class libConnectFactory implements myFactory{

	public Object getInstance(String key) {
		// TODO Auto-generated method stub
		if(key.equalsIgnoreCase(define.dataOpType_mysql)){
			return new mysqlConnect();
		}
		return null;
	}
	

}
