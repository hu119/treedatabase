package com.treebase.database.define;
/**
 * 树型列式数据库 初始字段类型参数定义
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class field_attrib_def {
	public static final String 		field_attrib_def_root="ACB766C6067F046105F8B1263B9E5172";
	public static final String 		myint="C35C95D98812A955F190CD4067BB58B8";
	public static final String 		myfloat="A3DECAA1996B1C6DD1697D5F2556F7BE";
	public static final String 		mychar="2B721CD0CB0B2551FE4EE5B4F2AAB647";
	public static final String 		myenchar="696ECEC5A86E19E4D4DF56006ECCDA0B";
	
//	public static Map<String,Node> get_field_attrib(){
//		ArrayList<Node> ret=new ArrayList<Node>();
//		Node item=Node_set.MyShareSet.getNode(field_attrib_def_root);
//		if(item!=null){
//			return item.children;
//		}
//		return null;
//	}
//	public static String get_field_attrib_josn(String value){
//		String retval="";
//		Node item=Node_set.MyShareSet.getNode(field_attrib_def_root);
//		if(item!=null){
//			
//			String tmp;
//			for(String key:item.children.keySet()){
//				Node nd=item.children.get(key);
//				if(value!=null){
//					if(nd.lmark.equals(value)){
//						tmp="{\"id\":\""+nd.lmark+"\",\"text\":\""+nd.descs+"\"}";
//						
//						return tmp;
//					}
//				}else{
//					tmp="{\"id\":\""+nd.lmark+"\",\"text\":\""+nd.descs+"\"}";
//					if(retval==null||retval.length()==0){
//						retval+="["+tmp;
//					}else{
//						retval+=","+tmp;
//					}
//				}
//			}
//			if(retval!=null)
//				retval+="]";
//		}
//		return retval;
//	}
	
}
