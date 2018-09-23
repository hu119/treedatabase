package com.treebase.database.datasource.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


import com.mysql.jdbc.ResultSetMetaData;
/**
 * 鏍戝瀷鍒楀紡鏁版嵁搴擄紙mysql)璁板綍琛岀鐞嗙被
 * @author 鑳″織姘�
 * Copyright(c) 2012-2013 鑳″織姘�
 * QQ:397874092
 * hu119_3@163.com
 */
public class mysql_result_rows {
	HashMap<String,Integer> colmap=new HashMap<String,Integer>();
	ArrayList<mysql_row> rows=new ArrayList<mysql_row>();
	public class mysql_row{
		public ArrayList<String>col_list=new ArrayList<String>();
		
		public String get_field(String fieldname){
			Integer pos=colmap.get(fieldname);
			if(pos==null)return "";
			String val=col_list.get(pos.intValue());
			if(val==null)val="";
			return val;
		}
	}
	public void clearObject(){
		colmap.clear();
		rows.clear();
	}
	public int get_col_count(){
		return colmap.size();
	}
	public int get_row_count(){
		return rows.size();
	}
	public mysql_row get_row(int no){
		if(no>=rows.size())return null;
		return rows.get(no);
	}
	
	public int analzyer_result(ResultSet rs){
		ResultSetMetaData md;
		try {
			if((rs==null)||(!rs.next()))return 0;
			
			md = (ResultSetMetaData) rs.getMetaData();
			int size = md.getColumnCount();
			System.out.println("ResultSet colnum:"+size);
			
			for(int i=1;i<=size;i++){
				colmap.put(md.getColumnName(i), i-1);
			}
			boolean bret=true;
	        while (bret) {
	        	mysql_row row=new mysql_row();
		        
		        for (int i = 1; i <=size; i++){
		        	row.col_list.add(rs.getObject(i).toString());
		        }
		        rows.add(row);
		        bret=rs.next();
	        }
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return rows.size();
	}
	
	
}
