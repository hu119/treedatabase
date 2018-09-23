package com.treebase.database.datasource.mysql;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.treebase.database.main.attrib;
import com.treebase.database.main.attribManage;
import com.treebase.database.main.node;
import com.treebase.database.system.table;
import com.treebase.database.system.tableManage;
import com.treebase.tools.string.mystring;

public class myTableRec{
	
	public boolean updateRec(String tablename,String rec_mark,HashMap<String,String>fieldmap){
		table tb=tableManage.tablist.get(tablename);
		if(tb==null)return false;
		
		Date dt=new Date();
		try {
			for(String key:fieldmap.keySet()){
				String fieldmark=tb.getFieldMark(key);
				if(!mystring.isEmpty(fieldmark)){
					attrib ab=new attrib();
					ab.mark=rec_mark;
					ab.fieldmark=fieldmark;
					ab.value=fieldmap.get(key);
					ab.timeval=dt.getTime()/1000;
					
					attribManage.updateAttrib(ab);
			
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public HashMap<String,String> getRec(String tablename,String rec_mark){
		HashMap<String,String> result=new HashMap<String,String>();
		table tb=tableManage.tablist.get(tablename);
		if(tb==null)return null;
		ArrayList<node> fieldlist=tb.getFieldlist();
		for(node nd:fieldlist){
			try {
				attrib ab=attribManage.getAttrib(rec_mark, nd.lmark);
				if(ab!=null){
					result.put(nd.name,ab.value);
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return result;
	}
	

}