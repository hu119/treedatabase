package com.treebase.database.datasource.mysql;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.treebase.database.conf.dbconfig;
import com.treebase.database.conf.define;
import com.treebase.database.conf.treeConfig;
import com.treebase.database.datasource.mysql.mysql_result_rows.mysql_row;
import com.treebase.database.datasrouce.connectPool;
import com.treebase.database.main.index;
import com.treebase.database.main.node;
import com.treebase.tools.date.FormatUtils;
import com.treebase.tools.encode.DES;
import com.treebase.tools.string.mystring;
import com.treebase.database.datasrouce.DataSource;
/**
 * 树型列式数据库（mysql)树节点表操作
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class myNodeLib extends mysqlDatabaseOp{
	private static final Logger LOGGER = LoggerFactory.getLogger(myNodeLib.class);
	@Override
	public boolean addRec(Object value) {
		node nd=(node)value;
		if(mystring.isEmpty(nd.pmark))return false;
		get_new_sub_mark(nd);
				
		return updatedRec(nd);
	}
	public static boolean  get_new_sub_mark(node nd){
		String mark=null;
		DataSource conn=connectPool.getConnect();
		if(conn==null){
			LOGGER.warn("getConnect error!");
			return false;
		}
		
		mysql_result_rows rows=new mysql_result_rows();
		String sql="select * from "+treeConfig.node_tablename+" where pmark='"+nd.pmark+"' and delmark=1 order by id asc limit 1";
		System.out.println(FormatUtils.getCurrentDate()+" sql_query_Exec :"+sql);
		conn.sql_query_Exec(sql, rows);
		int id=1;
		if(rows.get_row_count()==1){
			mysql_row row=rows.get_row(0);
			id=mystring.myStringToInt(row.get_field("id"));
		}else{
			sql="select * from "+treeConfig.node_tablename+" where pmark='"+nd.pmark+"' order by id desc limit 1";
			conn.sql_query_Exec(sql, rows);
			if(rows.get_row_count()==1){
				mysql_row row=rows.get_row(0);
				id=mystring.myStringToInt(row.get_field("id"));
				id++;
			}
		}
		nd.id=id;
		
		myIndexLib mib=new myIndexLib();
		index ind=(index)mib.getRec(nd.pmark);
		
		if(ind!=null){
			index item=new index();
			String idstr=DES.des_decode(ind.name);
			idstr+=("."+id);
			item.name=DES.des_encode(idstr);
			item.mark=DES.des_getMd5(idstr);
			mib.updatedRec(item);
			
			nd.lmark=item.mark;
			return true;
		}
		return false;
	}
	@Override
	public boolean updatedRec(Object value) {
		// TODO Auto-generated method stub
		boolean bret=false;
		node nd=(node)value;
		if(mystring.isEmpty(nd.lmark))return false;

		DataSource conn=connectPool.getConnect();
		if(conn==null){
			LOGGER.warn("getConnect error!");
			return false;
		}
		
		mysql_result_rows rows=new mysql_result_rows();
		String sql="select * from "+treeConfig.node_tablename+" where lmark='"+nd.lmark+"'";
//		System.out.println(FormatUtils.getCurrentDate()+" sql_query_Exec :"+sql);
		conn.sql_query_Exec(sql, rows);
		
		if(rows.get_row_count()==1){
			mysql_row row=rows.get_row(0);
			String delmark=row.get_field("delmark");
			if(delmark.equals("0")){
				sql="update  "+treeConfig.node_tablename+" set name='"+nd.name+"' ,descs='"+nd.descs+"' ,"
						+ "node_attrib='"+nd.node_attrib+"' ,node_type='"+nd.node_type+"'" +
					",pmark='"+nd.pmark+"' ,id="+nd.id+"  where lmark='"+nd.lmark+"'";
			}else{
				sql="update  "+treeConfig.node_tablename+" set name='"+nd.name+"' ,descs='"+nd.descs+"' ,"
						+ "node_attrib='"+nd.node_attrib+"' ,node_type='"+nd.node_type+"'" +
						",pmark='"+nd.pmark+"' ,id="+nd.id+" ,delmark=0 where lmark='"+nd.lmark+"'";
			}
			LOGGER.info("node updatedRec:"+nd.lmark+" "+nd.pmark+" name:"+nd.name);
		}else{
			sql="insert into "+treeConfig.node_tablename+" (pmark,lmark,id,name,descs,node_attrib,node_type,delmark) values ('"+nd.pmark+"','"+nd.lmark+"',"+nd.id+",'"+nd.name+"','"+nd.descs+"','"+nd.node_attrib+"','"+nd.node_type+"',0)";
			LOGGER.info("node addRec:"+nd.lmark+" "+nd.pmark+" name:"+nd.name);

		}
		bret=conn.sql_Exec(sql);
		
		connectPool.releaseConnect(conn);
		
		return bret;
	}

	@Override
	public boolean removeRec(Object value) {
		// TODO Auto-generated method stub
		boolean bret=false;
		node nd=(node)value;
		if(mystring.isEmpty(nd.lmark))return false;
		
		DataSource conn=connectPool.getConnect();
		if(conn==null){
			LOGGER.warn("getConnect error!");
			return false;
		}
		
		String sql="update "+treeConfig.node_tablename+" set delmark=1 where lmark='"+nd.lmark+"'";
		conn.sql_Exec(sql);
		
		connectPool.releaseConnect(conn);
		LOGGER.info("node removeRec:"+nd.lmark+" "+nd.pmark+" name:"+nd.name);
		return bret;
	}

	@Override
	public Object getRec(Object rec) {
		String mark=(String)rec;
		if(mystring.isEmpty(mark))return null;
		
		node item=null;
		DataSource conn=connectPool.getConnect();
		if(conn==null){
			LOGGER.warn("getConnect error!");
			return item;
		}
		int pos=mark.indexOf(define.children_mark);
		if(pos!=-1){
			String pmark=mark.substring(0, pos);
			mysql_result_rows rows=new mysql_result_rows();
			String sql="select lmark from "+treeConfig.node_tablename+" where pmark='"+pmark+"' and delmark=0  limit "+
							dbconfig.cacheChildrenNums;
			conn.sql_query_Exec(sql, rows);
			String result=null;
			int count=rows.get_row_count();
			for(int i=0;i<count;i++){
				mysql_row row=rows.get_row(i);
				if(result==null)
					result=row.get_field("lmark");
				else
					result+=(","+row.get_field("lmark"));
			}
			connectPool.releaseConnect(conn);
			return result;
		}else{
			pos=mark.indexOf(define.children_sql);
			if(pos!=-1){
				List<node> retlist=new ArrayList<node>();
				mysql_result_rows rows=new mysql_result_rows();
				String sql=mark.substring(0, pos);
		//		System.out.println(FormatUtils.getCurrentDate()+" sql_query_Exec :"+sql);
				conn.sql_query_Exec(sql, rows);
				
				if(rows.get_row_count()==1){
					mysql_row row=rows.get_row(0);
					item=new node();
					item.pmark=row.get_field("pmark");
					item.lmark=row.get_field("lmark");
					item.name=row.get_field("name");
					item.descs=row.get_field("descs");
					item.node_attrib=row.get_field("node_attrib");
					item.node_type=row.get_field("node_type");
					item.id=mystring.myStringToInt(row.get_field("id"));
					retlist.add(item);
				}
				return retlist;
			}else{
				mysql_result_rows rows=new mysql_result_rows();
				String sql="select * from "+treeConfig.node_tablename+" where lmark='"+mark+"' and delmark=0 ";
		//		System.out.println(FormatUtils.getCurrentDate()+" sql_query_Exec :"+sql);
				conn.sql_query_Exec(sql, rows);
				
				if(rows.get_row_count()==1){
					mysql_row row=rows.get_row(0);
					item=new node();
					item.pmark=row.get_field("pmark");
					item.lmark=row.get_field("lmark");
					item.name=row.get_field("name");
					item.descs=row.get_field("descs");
					item.node_attrib=row.get_field("node_attrib");
					item.node_type=row.get_field("node_type");
					item.id=mystring.myStringToInt(row.get_field("id"));
				}
			}
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
