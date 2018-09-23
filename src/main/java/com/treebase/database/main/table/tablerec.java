package com.treebase.database.main.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.treebase.database.main.attrib;
import com.treebase.database.main.attribManage;
import com.treebase.database.main.node;
import com.treebase.database.system.table;
import com.treebase.tools.string.mystring;
/**
 * 树型列式数据库  
 * 数据库表记录操作
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class tablerec {
	table 	tb;
	String 	recmark;
	String 	selectField;
	Map<String,String>fieldmap=new HashMap<String,String>();
	
	public tablerec(table tb,String recmark,String selectField){
		this.tb=tb;
		this.recmark=recmark;
		this.selectField=selectField;
	}
	public boolean loadrec(){
		fieldmap.clear();
		ArrayList<node> retarr=tb.getFieldlist();
		for(node nd:retarr){
			try {
				if(selectField.isEmpty()||selectField.contains(nd.name)){
					attrib ab=attribManage.getAttrib(recmark, nd.lmark);
					if(ab!=null){
						fieldmap.put(nd.name,ab.value);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(fieldmap.size()>0)
			return true;
		return false;
	}
	public String getKey(String fieldname){
		if(fieldmap.size()==0)return "";
		
		String str=fieldmap.get(fieldname);
		if(mystring.isEmpty(str))
			str=fieldmap.get(0);
		return str;
	}
}
