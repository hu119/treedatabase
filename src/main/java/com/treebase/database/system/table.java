package com.treebase.database.system;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.treebase.database.main.node;
import com.treebase.database.main.nodeManage;
import com.treebase.tools.string.mystring;
/**
 * 树型列式数据库  
 * 表管理
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */

public class table{
	ConcurrentHashMap<String,node> fieldlist=new ConcurrentHashMap<String,node>();
	public node nd;
	public table(node nd){
		this.nd=nd;
	}
	public boolean relaodFieldList(){
		fieldlist.clear();
		
		String tabstr;
		try {
			tabstr = nodeManage.getChildren(nd.lmark);
			if(!mystring.isEmpty(tabstr)){
				String[] tabarr=tabstr.split(",");
				for(String key:tabarr){
					node item=nodeManage.getNode(key);
					fieldlist.put(item.name, item);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}	
	public String getFieldMark(String name){
		node nd=fieldlist.get(name);
		if(nd!=null)return nd.lmark;
		return null;
	}
	public ArrayList<node> getFieldlist(){
		ArrayList<node> result=new ArrayList<node>();
		for(String key:fieldlist.keySet()){
			result.add(fieldlist.get(key));
		}
		return result;
	}
}