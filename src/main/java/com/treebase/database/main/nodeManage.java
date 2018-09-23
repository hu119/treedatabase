package com.treebase.database.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.treebase.database.cache.myCache;
import com.treebase.database.conf.define;
import com.treebase.database.datasrouce.SqlProcess;
import com.treebase.database.factory.myFactory;
import com.treebase.database.factory.nodeFactory;
import com.treebase.tools.string.mystring;
/**
 * 树型列式数据库  
 * 树节点对象管理
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */

public class nodeManage {
	private static final Logger LOGGER = LoggerFactory.getLogger(nodeManage.class);
	public static myFactory ndf=new nodeFactory();
	
	public static boolean addNode(node nd) throws Exception{
		//锟斤拷锟斤拷锟斤拷锟斤拷锟�
		if(nd.lmark.length()==0)return false;
		myCache nd_cache=(myCache)ndf.getInstance(define.dataOpType_cache);
		if(nd_cache==null)throw  new Exception("memCache no defined!");
		
		nd_cache.removeKey(nd.lmark);
		if(nd.pmark.length()!=0)
			nd_cache.removeKey(nd.pmark+define.children_mark);
		
		SqlProcess nd_lib=(SqlProcess)ndf.getInstance(define.dataOpType_lib);
		if(nd_lib==null)throw  new Exception("lib no defined!");
		
		nd_lib.addRec(nd);
		
		nd_cache.removeKey(nd.lmark);
		if(nd.pmark.length()!=0){
			nd_cache.removeKey(nd.pmark+define.children_mark);
		}
		
		return true;
	}
	
	public static node getNode(String lmark) throws Exception{
		myCache nd_cache=(myCache)ndf.getInstance(define.dataOpType_cache);
		if(nd_cache==null)throw  new Exception("memCache no defined!");
		
		node nd =(node)nd_cache.getKey(lmark);
		if(nd==null){
			SqlProcess nd_lib=(SqlProcess)ndf.getInstance(define.dataOpType_lib);
			if(nd_lib==null)throw  new Exception("lib no defined!");
			
			nd =(node)nd_lib.getRec(lmark);
			if(nd==null)return null;
			//锟斤拷锟铰碉拷锟斤拷锟斤拷
			nd_cache.UpdateKey(lmark,nd);
		}
		return nd;
	}
	public static String getChildren(String lmark) throws Exception{
		myCache nd_cache=(myCache)ndf.getInstance(define.dataOpType_cache);
		if(nd_cache==null)throw  new Exception("memCache no defined!");
		
		String mark=lmark+define.children_mark;
		String ndlist =(String)nd_cache.getKey(mark);
		if(mystring.isEmpty(ndlist)){
			SqlProcess nd_lib=(SqlProcess)ndf.getInstance(define.dataOpType_lib);
			if(nd_lib==null)throw  new Exception("lib no defined!");
			
			ndlist =(String)nd_lib.getRec(mark);
			
			if(mystring.isEmpty(ndlist)){
				nd_cache.UpdateKey(mark,"null");
			}else{
				nd_cache.UpdateKey(mark,ndlist);
			}
			
		}
		return ndlist;
	}
	
	public static boolean  updateNode(node nd) throws Exception{

		//锟斤拷锟斤拷锟斤拷锟斤拷锟�
		if(nd.lmark.length()==0)return false;
		myCache nd_cache=(myCache)ndf.getInstance(define.dataOpType_cache);
		if(nd_cache==null)throw  new Exception("memCache no defined!");
		nd_cache.removeKey(nd.lmark);
		
		SqlProcess nd_lib=(SqlProcess)ndf.getInstance(define.dataOpType_lib);
		if(nd_lib==null)throw  new Exception("lib no defined!");
		nd_lib.updatedRec(nd.lmark);
		
		nd_cache.removeKey(nd.lmark);
		
		return true;
	}
	
	public static boolean  removeNode(node nd) throws Exception{
		//
		if(nd.lmark.length()==0)return false;
		myCache nd_cache=(myCache)ndf.getInstance(define.dataOpType_cache);
		if(nd_cache==null)throw  new Exception("memCache no defined!");
		
		nd_cache.removeKey(nd.lmark);
		if(nd.pmark.length()!=0)
			nd_cache.removeKey(nd.pmark+define.children_mark);
		
		SqlProcess nd_lib=(SqlProcess)ndf.getInstance(define.dataOpType_lib);
		if(nd_lib==null)throw  new Exception("lib no defined!");
		nd_lib.removeRec(nd.lmark);
		
		nd_cache.removeKey(nd.lmark);
		if(nd.pmark.length()!=0){
			nd_cache.removeKey(nd.pmark+define.children_mark);
		}
		return true;
	}
}
