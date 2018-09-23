package com.treebase.database.main;

import com.treebase.tools.date.FormatUtils;
/**
 * 树型列式数据库  
 * 属性对象
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class attrib {
	public String 		mark="";
	public String 		fieldmark="";
	public String 		value="";
	public String		user="";
	public long		timeval=0;
	
	
	public void initRec(String mark,String fieldmark,String value,String user){
		this.mark=mark;
		this.fieldmark=fieldmark;
		this.value=value;
		this.user=user;
		timeval=FormatUtils.getCurrentTime();
	}
	
}
