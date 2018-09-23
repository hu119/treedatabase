package com.treebase.database.datasrouce;

import com.treebase.database.factory.libConnectFactory;
import com.treebase.database.factory.myFactory;
/**
 * 树型列式数据库 数据源接口
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public abstract class databaseOp implements  SqlProcess{
	public myFactory connectFactory=new libConnectFactory();
	
	public abstract boolean addRec(Object rec);
	
	public abstract boolean updatedRec(Object rec);
	
	public abstract boolean removeRec(Object rec);
	
	public abstract Object getRec(Object rec);
}
