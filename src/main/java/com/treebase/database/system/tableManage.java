package com.treebase.database.system;

import java.util.concurrent.ConcurrentHashMap;

import com.treebase.database.define.cluster_module_def;
import com.treebase.database.define.tables_define;
import com.treebase.database.main.node;
import com.treebase.database.main.nodeManage;
import com.treebase.tools.string.mystring;

/**
 * 树型列式数据库  
 * 节点类型管理
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class tableManage {
	public static ConcurrentHashMap<String,table> tablist=new ConcurrentHashMap<String,table>();
	public static ConcurrentHashMap<String,node> fieldtypelist=new ConcurrentHashMap<String,node>();
	public static ConcurrentHashMap<String,node> nodetypelist=new ConcurrentHashMap<String,node>();
	
	
	public static boolean relaodTableList(){
		tablist.clear();
		node nd;
		try {
			nd=nodeManage.getNode(tables_define.rec_root);
			
			String tabstr=nodeManage.getChildren(nd.lmark);
			if(!mystring.isEmpty(tabstr)){
				String[] tabarr=tabstr.split(",");
				for(String key:tabarr){
					nd=nodeManage.getNode(key);
					table tb=new table(nd);
					tb.relaodFieldList();
					tablist.put(nd.name, tb);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public static boolean relaodFieldTypeList(){
		fieldtypelist.clear();
		String tabstr;
		try {
			tabstr = nodeManage.getChildren(cluster_module_def.field_attrib);
			if(!mystring.isEmpty(tabstr)){
				String[] tabarr=tabstr.split(",");
				for(String key:tabarr){
					node nd=nodeManage.getNode(key);
					fieldtypelist.put(nd.lmark, nd);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	public static boolean relaodNodeTypeList(){
		nodetypelist.clear();
		String tabstr;
		try {
			tabstr = nodeManage.getChildren(cluster_module_def.node_attrib);
			if(!mystring.isEmpty(tabstr)){
				String[] tabarr=tabstr.split(",");
				for(String key:tabarr){
					node nd=nodeManage.getNode(key);
					nodetypelist.put(nd.lmark, nd);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	public static table getTable(String lmark){
		//先判断该节点是否为表属性
		try {
			node nd=nodeManage.getNode(lmark);
			if(nd!=null){
				node tmpnd=nodeManage.getNode(nd.node_type);
				return tablist.get(tmpnd.name);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static table getTableFromName(String name){
		return tablist.get(name);
	}
	
	
	
}
