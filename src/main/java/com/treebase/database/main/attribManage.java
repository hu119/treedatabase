package com.treebase.database.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.treebase.database.cache.myCache;
import com.treebase.database.conf.define;
import com.treebase.database.datasrouce.SqlProcess;
import com.treebase.database.factory.attribFactory;
import com.treebase.database.factory.myFactory;
import com.treebase.tools.string.mystring;

/**
 * 树型列式数据库  
 * 属性对象管理
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */


public class attribManage {
	private static final Logger LOGGER = LoggerFactory.getLogger(attribManage.class);
	public static myFactory attf=new attribFactory();
		
	public static boolean addAttrib(attrib ab) throws Exception{
		//锟斤拷锟斤拷锟斤拷锟斤拷锟�
		if(mystring.isEmpty(ab.mark))return false;
		if(mystring.isEmpty(ab.fieldmark))return false;
		
		myCache att_cache=(myCache)attf.getInstance(define.dataOpType_cache);
		if(att_cache==null)throw  new Exception("memCache no defined!");
		att_cache.removeKey(ab.fieldmark+ab.fieldmark);
		
		SqlProcess att_lib=(SqlProcess)attf.getInstance(define.dataOpType_lib);
		if(att_lib==null)throw  new Exception("lib no defined!");
		att_lib.addRec(ab);
		
		att_cache.removeKey(ab.fieldmark+ab.fieldmark);
		return true;
	}
	public static attrib getAttrib(String mark,String fieldmark) throws Exception{
		if(mystring.isEmpty(mark))return null;
		if(mystring.isEmpty(fieldmark))return null;
		myCache att_cache=(myCache)attf.getInstance(define.dataOpType_cache);
		if(att_cache==null)throw  new Exception("memCache no defined!");
		
		attrib ab=(attrib)att_cache.getKey(mark+fieldmark);
		if(ab!=null)return ab;
		
		SqlProcess att_lib=(SqlProcess)attf.getInstance(define.dataOpType_lib);
		if(att_lib==null)throw  new Exception("lib no defined!");
		ab=(attrib)att_lib.getRec(mark+"_"+fieldmark);
		
		if(ab!=null)att_cache.addKey(mark+fieldmark,ab);
		
		return ab;
	}
	public static boolean  updateAttrib(attrib ab) throws Exception{
		
		//锟斤拷锟斤拷锟斤拷锟斤拷锟�
		if(mystring.isEmpty(ab.mark))return false;
		if(mystring.isEmpty(ab.fieldmark))return false;
		
		myCache att_cache=(myCache)attf.getInstance(define.dataOpType_cache);
		if(att_cache==null)throw  new Exception("memCache no defined!");
		att_cache.removeKey(ab.fieldmark+ab.fieldmark);
		
		SqlProcess att_lib=(SqlProcess)attf.getInstance(define.dataOpType_lib);
		if(att_lib==null)throw  new Exception("lib no defined!");
		
		att_lib.updatedRec(ab);
		
		att_cache.removeKey(ab.fieldmark+ab.fieldmark);
		
		return true;
	}
	
	public static boolean  removeAttrib(attrib ab) throws Exception{
		
		//锟斤拷锟斤拷锟斤拷锟斤拷锟�
		if(mystring.isEmpty(ab.mark))return false;
		if(mystring.isEmpty(ab.fieldmark))return false;
		
		myCache att_cache=(myCache)attf.getInstance(define.dataOpType_cache);
		if(att_cache==null)throw  new Exception("memCache no defined!");
		att_cache.removeKey(ab.fieldmark+ab.fieldmark);
		
		SqlProcess att_lib=(SqlProcess)attf.getInstance(define.dataOpType_lib);
		if(att_lib==null)throw  new Exception("lib no defined!");
		
		att_lib.removeRec(ab);
		
		att_cache.removeKey(ab.fieldmark+ab.fieldmark);
		
		return true;
	}
	
}
