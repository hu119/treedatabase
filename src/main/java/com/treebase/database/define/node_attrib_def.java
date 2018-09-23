package com.treebase.database.define;

/**
 * 树型列式数据库 初始节点参数定义
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */

public class node_attrib_def {
	
	public static final String node_attrib_def_root="BF701DAE9AFF5BB22B8F000DC9BF6199";
	public static final String field="5D98805CDCAF447B11F65F5B84E0EBC0";
	public static final String field_def="65C7C329317C243D8549A8F4E6E53ED3";
	public static final String tpclass="AF56487774D1E3B7CE13083119D763EB";
	public static final String link="8007F78DBF757466F1F33A05292C3AA1";
	public static final String table="1FE1F2D76ECB592D91F100C044AC5997";
	public static final String table_def="A44A368807279C1747CAB11577C06CA1";
	public static final String value="C0EC8A5C0088D4AB8985433027E951BB";
	public static final String function="352F2C5F9D36B15140BD4F136EA0CC31";
	public static final String alluser="C27029522F76C04F5185EE42C8BD6E3A";
	
	
	public static final String field_name="field";
	public static final String field_def_name="field_def";
	public static final String tpclass_name="type";
	public static final String link_name="link";
	public static final String table_name="table";
	public static final String table_def_name="table_def";
	public static final String value_name="value";
	public static final String function_name="function";
	public static final String alluser_name="alluser";
	
	
//	public static Map<String,Node> get_node_attrib(){
//		ArrayList<Node> ret=new ArrayList<Node>();
//		Node item=Node_set.MyShareSet.getNode(node_attrib_def_root);
//		if(item!=null){
//			return item.children;
//		}
//		return null;
//	}
//	public static String get_node_attrib_josn(){
//		String retval="";
//		Node item=Node_set.MyShareSet.getNode(node_attrib_def_root);
//		if(item!=null){
//			
//			String tmp;
//			for(String key:item.children.keySet()){
//				Node nd=item.children.get(key);
//				tmp="{\"id\":\""+nd.lmark+"\",\"text\":\""+nd.descs+"\"}";
//				if(retval==null||retval.length()==0){
//					retval+="["+tmp;
//				}else{
//					retval+=","+tmp;
//				}
//			}
//			if(retval!=null)
//				retval+="]";
//		}
//		return retval;
//	}
}
