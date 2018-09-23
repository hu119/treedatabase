package com.treebase.database.datasource.mysql;


import com.treebase.database.conf.treeConfig;
import com.treebase.database.datasource.mysql.mysql_result_rows.mysql_row;
import com.treebase.database.datasrouce.DataSource;
import com.treebase.database.datasrouce.connectPool;
import com.treebase.database.main.attrib;
import com.treebase.tools.date.FormatUtils;
import com.treebase.tools.string.mystring;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

/**
 * 树型列式数据库（mysql)属性表操作
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class myAttribLib extends mysqlDatabaseOp{
	private static final Logger LOGGER = LoggerFactory.getLogger(myAttribLib.class);
	@Override
	public boolean addRec(Object value) {
		
		return updatedRec(value);
	}

	@Override
	public boolean updatedRec(Object value) {
		// TODO Auto-generated method stub
		boolean bret=false;
		// TODO Auto-generated method stub
		attrib ab=(attrib)value;
		if(mystring.isEmpty(ab.mark))return false;
		if(mystring.isEmpty(ab.fieldmark))return false;
		
		DataSource conn=connectPool.getConnect();
		if(conn==null){
			LOGGER.warn("getConnect error!");
			return false;
		}
		
		mysql_result_rows rows=new mysql_result_rows();
		String sql="select * from "+treeConfig.attrib_tablename+" where mark='"+ab.mark+"' and fieldmark='"+ab.fieldmark+"'";
		System.out.println(FormatUtils.getCurrentDate()+" sql_query_Exec :"+sql);
		conn.sql_query_Exec(sql, rows);
		String ftvalue="";
		if(rows.get_row_count()==1){
			sql="update  "+treeConfig.attrib_tablename+" set value='"+ftvalue+"',user='"+ab.user+"',delmark=0 where mark='"+ab.mark+"' and fieldmark='"+ab.fieldmark+"'";
			LOGGER.info("attrib updateRec:"+ab.mark+" "+ab.fieldmark+" value:"+ab.value);
		}else{
			sql="insert into "+treeConfig.attrib_tablename+" (mark,fieldmark,value,user,timeval,delmark) values ('"+ab.mark+"','"+ab.fieldmark+"','"+
					ftvalue+"','"+ab.user+"',"+ab.timeval+", 0)";
			LOGGER.info("attrib addRec:"+ab.mark+" "+ab.fieldmark+" value:"+ab.value);
		}
		bret=conn.sql_Exec(sql);
		
		connectPool.releaseConnect(conn);
		
		return bret;
	}

	@Override
	public boolean removeRec(Object value) {
		boolean bret=false;
		attrib ab=(attrib)value;
		if(mystring.isEmpty(ab.mark))return false;
		if(mystring.isEmpty(ab.fieldmark))return false;

		
		DataSource conn=connectPool.getConnect();
		if(conn==null){
			LOGGER.warn("getConnect error!");
			return false;
		}
		
		String sql="update "+treeConfig.attrib_tablename+" set delmark=1 where mark='"+ab.mark+"' and fieldmark='"+ab.fieldmark+"'";
		conn.sql_Exec(sql);
		
		connectPool.releaseConnect(conn);
		LOGGER.info("attrib removeRec:"+ab.mark+" "+ab.fieldmark+" value:"+ab.value);
		return bret;
	}

	@Override
	public Object getRec(Object value) {
		// TODO Auto-generated method stub
		String markname=(String)value;
		String[] markarr=markname.split("_");
		if(markarr.length!=2)return null;
		attrib ab=null;
		
		DataSource conn=connectPool.getConnect();
		if(conn==null){
			LOGGER.warn("getConnect error!");
			return false;
		}
		mysql_result_rows rows=new mysql_result_rows();
		String sql="select * from "+treeConfig.attrib_tablename+" where mark='"+markarr[0]+"' and fieldmark='"+markarr[1]+"' and delmark=0 ";
		System.out.println(FormatUtils.getCurrentDate()+" sql_query_Exec :"+sql);
		conn.sql_query_Exec(sql, rows);
		String ftvalue="";
		if(rows.get_row_count()==1){
			mysql_row row=rows.get_row(0);
			ab=new attrib();
			ab.mark=row.get_field("mark");
			ab.fieldmark=row.get_field("fieldmark");
			ab.value=row.get_field("value");
			ab.user=row.get_field("user");
			String tv=row.get_field("timeval");
			
			ab.timeval=mystring.myStringToLong(tv);
			
		}
		connectPool.releaseConnect(conn);
		return ab;
	}

	@Override
	public List<Object> sqlExec(String sql) {
		DataSource conn=connectPool.getConnect();
		if(conn==null){
			LOGGER.warn("getConnect error!");
			return null;
		}
		List<Object> retlist=new ArrayList<Object>();
		mysql_result_rows rows=new mysql_result_rows();
		System.out.println(FormatUtils.getCurrentDate()+" sql_query_Exec :"+sql);
		conn.sql_query_Exec(sql, rows);
		int count=rows.get_row_count();
		for(int i=0;i<count;i++){
			mysql_row row=rows.get_row(i);
			attrib ab=new attrib();
			ab.mark=row.get_field("mark");
			ab.fieldmark=row.get_field("fieldmark");
			ab.value=row.get_field("value");
			ab.user=row.get_field("user");
			String tv=row.get_field("timeval");
			
			ab.timeval=mystring.myStringToLong(tv);
			
			retlist.add(ab);
		}
		connectPool.releaseConnect(conn);
		return retlist;
	}
	
}