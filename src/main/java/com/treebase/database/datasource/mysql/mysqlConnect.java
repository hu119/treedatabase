package com.treebase.database.datasource.mysql;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.treebase.database.conf.dbconfig;
import com.treebase.database.datasrouce.dbConnect;
import com.treebase.database.datasrouce.DataSource;
/**
 * 树型列式数据库（mysql)连接管理
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class mysqlConnect implements dbConnect{

	public DataSource getConnect() {
		// TODO Auto-generated method stub
	   try {
			Class.forName(dbconfig.driver);
			String url="jdbc:mysql://"+dbconfig.hostname+":"+dbconfig.port+"/"+
					dbconfig.basename+"?useUnicode=true&characterEncoding=utf-8";	
			
			System.out.println("url:"+url);
			Connection conn =(Connection)DriverManager.getConnection(url, 
	        		dbconfig.username,dbconfig.password);
			DataSource co=new mysqlDataSource();
			co.setConnect(conn);
			return co;
       } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



}
