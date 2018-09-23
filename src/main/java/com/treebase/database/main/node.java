package com.treebase.database.main;

import java.util.ArrayList;
/**
 * 树型列式数据库  
 * 树节点对象
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */

public class node {
	public String pmark="";
	public String lmark="";
	public String name="";
	public String descs="";
	public String node_attrib="";
	public String node_type="";
	public int	  id=0;
	
	public ArrayList<String>children=new ArrayList<String>();
	
	public boolean getChildList(){
		try {
			children.clear();
			String retstr=nodeManage.getChildren(lmark);
			String[] strlist=retstr.split(",");
			for(String key:strlist){
				children.add(key);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	
	
}
