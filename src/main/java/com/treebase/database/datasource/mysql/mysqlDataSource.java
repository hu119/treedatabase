package com.treebase.database.datasource.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.treebase.database.datasrouce.DataSource;
/**
 * 树型列式数据库（mysql)数据源操作
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */

public class mysqlDataSource extends DataSource{

	public boolean sql_Exec(String sql) {
		// TODO Auto-generated method stub
		Statement statement;
		try {
			statement = conn.createStatement();
			return statement.execute(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public long sql_query_Exec(String sql, Object result) {
		// TODO Auto-generated method stub
		int retcount=0;
		mysql_result_rows  rows=(mysql_result_rows)result;
		try {
			Statement statement;
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			rows.analzyer_result(rs);
			retcount=rows.rows.size();
			statement.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retcount;
		
	}

	
	

}
