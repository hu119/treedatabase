package com.treebase.database.datasrouce;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.treebase.database.conf.dbconfig;
import com.treebase.database.conf.treeConfig;
import com.treebase.database.factory.dataSourceFactory;
import com.treebase.database.factory.libConnectFactory;
/**
 * 树型列式数据库 数据源连接池管理
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class connectPool {
	private static ConcurrentLinkedQueue<DataSource> connect_use=new ConcurrentLinkedQueue<DataSource>();
	private static ConcurrentLinkedQueue<DataSource> connect_idle=new ConcurrentLinkedQueue<DataSource>();
	
	private static dbConnect dbcon=null;
	public static void initPool(){
		dbcon=(dbConnect)(new libConnectFactory()).getInstance(treeConfig.libtype);
	}
	public static DataSource getConnect( ){
		if(dbcon==null)initPool();
		
		DataSource conn=connect_idle.poll();
		if(conn==null){
			if((connect_use.size()+connect_idle.size())>dbconfig.maxcon)
				return null;
			
			conn=(DataSource)(new dataSourceFactory()).getInstance(treeConfig.libtype);
			conn=dbcon.getConnect();
			if(conn==null)return null;
					
		}
		connect_use.add(conn);
		return conn;
	}
	
	public static  boolean releaseConnect(DataSource conn){
		conn.idle();
		connect_use.remove(conn);
		long count=(dbconfig.maxcon+dbconfig.mincon)/2;
		if(connect_idle.size()>count){
			conn.close();
		}else{
			connect_idle.add(conn);
		}
		return true;
	}
	public static void doBreat(){
		if(dbcon==null)initPool();
		
		
	}
	
	
}
