package com.treebase.database.datasrouce;

/**
 * 树型列式数据库数据源连接
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public interface dbConnect {

		
	public abstract DataSource getConnect();
	
}
