package com.treebase.database.datasource.mysql;

import com.treebase.database.datasrouce.databaseOp;
/**
 * 树型列式数据库（mysql)接口
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public abstract class mysqlDatabaseOp extends databaseOp{
	
	
	@Override
	public abstract boolean addRec(Object rec);

	@Override
	public abstract boolean updatedRec(Object rec);

	@Override
	public abstract boolean removeRec(Object rec);

	@Override
	public abstract Object getRec(Object rec);

	
}
