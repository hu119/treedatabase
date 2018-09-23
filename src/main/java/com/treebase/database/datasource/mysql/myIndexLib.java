package com.treebase.database.datasource.mysql;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.treebase.database.conf.treeConfig;
import com.treebase.database.datasource.mysql.mysql_result_rows.mysql_row;
import com.treebase.database.datasrouce.DataSource;
import com.treebase.database.datasrouce.connectPool;
import com.treebase.database.main.index;
import com.treebase.tools.date.FormatUtils;
import com.treebase.tools.string.mystring;
/**
 * 树型列式数据库（mysql)ID表操作
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */

public class myIndexLib extends mysqlDatabaseOp{
	private static final Logger LOGGER = LoggerFactory.getLogger(myIndexLib.class);
	
	@Override
	public boolean addRec(Object value) {
		// TODO Auto-generated method stub
		return updatedRec(value);
	}

	@Override
	public boolean updatedRec(Object value) {
		boolean bret=false;
		// TODO Auto-generated method stub
		index ind=(index)value;
		if(mystring.isEmpty(ind.mark))return false;
		
		DataSource conn=connectPool.getConnect();
		if(conn==null){
			LOGGER.warn("getConnect error!");
			return false;
		}
		mysql_result_rows rows=new mysql_result_rows();
		String sql="select * from "+treeConfig.index_tablename+" where mark='"+ind.mark+"'";
		System.out.println(FormatUtils.getCurrentDate()+" sql_query_Exec :"+sql);
		conn.sql_query_Exec(sql, rows);
		
		bret=true;
		if(rows.get_row_count()==0){ //
			sql="insert into "+treeConfig.index_tablename+" (mark,name) values ('"+ind.mark+"','"+ind.name+"')";
			bret=conn.sql_Exec(sql);
			LOGGER.info("index addRec:"+ind.mark+" "+ind.name);
		}else{
			///igone
		}		
		connectPool.releaseConnect(conn);
		
		return bret;
	}

	@Override
	public boolean removeRec(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getRec(Object value) {
		// TODO Auto-generated method stub
		String mark=(String)value;
		if(mystring.isEmpty(mark))return null;
		
		index item=null;
		DataSource conn=connectPool.getConnect();
		if(conn==null){
			LOGGER.warn("getConnect error!");
			return false;
		}
		
		mysql_result_rows rows=new mysql_result_rows();
		String sql="select * from "+treeConfig.index_tablename+" where mark='"+mark+"'";
//		System.out.println(FormatUtils.getCurrentDate()+" sql_query_Exec :"+sql);
		conn.sql_query_Exec(sql, rows);
		
		if(rows.get_row_count()==1){
			mysql_row row=rows.get_row(0);
			item=new index();
			item.name=row.get_field("name");
			item.mark=row.get_field("mark");
		
		}
		connectPool.releaseConnect(conn);
		return item;
	}

	@Override
	public List<Object> sqlExec(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

}
