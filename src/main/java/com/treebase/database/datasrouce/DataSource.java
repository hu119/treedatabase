package com.treebase.database.datasrouce;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
/**
 * 树型列式数据库 数据源接口
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public abstract class DataSource {
	protected Connection conn;
	protected boolean buser=false;
	protected long	 actime=0;
	
	
	public void setConnect(Connection conn){
		this.conn=conn;
		buser=true;
		Date dt=new Date();
		actime=dt.getTime()/1000;
	}
	public void idle(){
		actime=0;
		buser=false;
	}
	
	public abstract boolean sql_Exec(String sql);
	
	public abstract long sql_query_Exec(String sql,Object result);
		
	public boolean close(){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		conn=null;
		return true;
	}
	
}
