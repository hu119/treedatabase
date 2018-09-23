package com.treebase.database.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.treebase.database.cache.myCache;
import com.treebase.database.conf.define;
import com.treebase.database.datasrouce.SqlProcess;
import com.treebase.database.factory.indexFactory;
import com.treebase.database.factory.myFactory;
import com.treebase.tools.string.mystring;
/**
 * 树型列式数据库  
 * ID对象管理
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */


public class indexManage {
	private static final Logger LOGGER = LoggerFactory.getLogger(indexManage.class);
	static myFactory indf=new indexFactory();

	public static boolean addAttrib(index ind) throws Exception{
		//锟斤拷锟斤拷锟斤拷锟斤拷锟�
		if(mystring.isEmpty(ind.mark))return false;
		
		myCache ind_cache=(myCache)indf.getInstance(define.dataOpType_cache);
		if(ind_cache==null)throw  new Exception("memCache no defined!");
		ind_cache.removeKey(ind.mark);
		
		SqlProcess ind_lib=(SqlProcess)indf.getInstance(define.dataOpType_lib);
		if(ind_lib==null)throw  new Exception("lib no defined!");
		ind_lib.addRec(ind);
		
		ind_cache.removeKey(ind.mark);
		return true;
	}
	public static index getIndex(String mark) throws Exception{
		if(mystring.isEmpty(mark))return null;
		myCache ind_cache=(myCache)indf.getInstance(define.dataOpType_cache);
		if(ind_cache==null)throw  new Exception("memCache no defined!");
		
		index ind=(index)ind_cache.getKey(mark);
		if(ind!=null)return ind;
		
		SqlProcess ind_lib=(SqlProcess)indf.getInstance(define.dataOpType_lib);
		if(ind_lib==null)throw  new Exception("lib no defined!");
		ind=(index)ind_lib.getRec(mark);
		
		if(ind!=null)ind_cache.addKey(mark,ind);
		
		return ind;
	}
	public static boolean  updateIndex(index ind) throws Exception{
		
		//锟斤拷锟斤拷锟斤拷锟斤拷锟�
		if(mystring.isEmpty(ind.mark))return false;
				
		myCache ind_cache=(myCache)indf.getInstance(define.dataOpType_cache);
		if(ind_cache==null)throw  new Exception("memCache no defined!");
		ind_cache.removeKey(ind.mark);
		
		SqlProcess ind_lib=(SqlProcess)indf.getInstance(define.dataOpType_lib);
		if(ind_lib==null)throw  new Exception("lib no defined!");
		
		ind_lib.updatedRec(ind);
		
		ind_cache.removeKey(ind.mark);
		
		return true;
	}
	
	public static boolean  removeIndex(String mark) throws Exception{
		
		//锟斤拷锟斤拷锟斤拷锟斤拷锟�
		if(mystring.isEmpty(mark))return false;
		
		myCache ind_cache=(myCache)indf.getInstance(define.dataOpType_cache);
		if(ind_cache==null)throw  new Exception("memCache no defined!");
		ind_cache.removeKey(mark);
		
		SqlProcess ind_lib=(SqlProcess)indf.getInstance(define.dataOpType_lib);
		if(ind_lib==null)throw  new Exception("lib no defined!");
		
		ind_lib.removeRec(mark);
		
		ind_cache.removeKey(mark);
		
		return true;
	}
	
	
	
}
