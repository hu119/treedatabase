package com.treebase.database.main.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import com.treebase.database.conf.dbconfig;
import com.treebase.database.conf.define;
import com.treebase.database.conf.treeConfig;
import com.treebase.database.datasrouce.SqlProcess;
import com.treebase.database.main.attrib;
import com.treebase.database.main.attribManage;
import com.treebase.database.main.node;
import com.treebase.database.main.nodeManage;
import com.treebase.database.system.table;
import com.treebase.database.system.tableManage;
import com.treebase.tools.string.mystring;

/**
 * 树型列式数据库  
 * 数据库表操作
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class tableOp extends myTable{
		
//	public static Map<String,String> treemap = Collections.synchronizedMap(new TreeMap<String,String>());

	table tb=null;
	
	
	@Override
	public List<Object> getResult(String parentMark,
								  String beginMark, 
								  int 	 pagesize,	
								  String selectField,
								  String sortField,	
								  String sorttype) {
		tb=tableManage.getTable(parentMark);
		if(tb==null)return null;
		ArrayList<Object> resultlist=new ArrayList<Object>();
		try {
			String ndlist=nodeManage.getChildren(parentMark);
			int nums=0;
			if((!ndlist.isEmpty())&&(!ndlist.equals("null"))){
				String[] arr=ndlist.split(",");
				if(arr.length!=dbconfig.cacheChildrenNums)nums=arr.length;
			}
			if(nums>0){
				//
				ArrayList<tablerec> reclist=new ArrayList<tablerec>();
				TreeMap<String,tablerec> retlist=getRecList(tb,ndlist,selectField,sortField);
				for(String key:retlist.keySet()){
					tablerec rec=retlist.get(key);
					reclist.add(rec);
				}
				resultlist=getLimitList(reclist,beginMark,pagesize,sortField,sorttype);
			}else{
				String sql="select ";
				//缁撴灉浠庢暟鎹簱涓彁鍙�
				sql+=" * from "+treeConfig.attrib_tablename;
				String fieldmark = null;
				ArrayList<node> fieldlist=tb.getFieldlist();
				for(node nd:fieldlist){
					if(mystring.isEmpty(sortField)){
						fieldmark=nd.lmark;
						break;
					}else{
						if(sortField.equalsIgnoreCase(nd.name)){
							fieldmark=nd.lmark;
							break;
						}
					}
				}
				if(mystring.isEmpty(fieldmark)){
					return null;
				}
				if(!mystring.isEmpty(beginMark)){
					sql+=(" where mark>'"+beginMark+"' ");
					sql+=(" and fieldmark='"+fieldmark+"' ");
				}else{
					sql+=(" where fieldmark='"+fieldmark+"' ");
				}
				if(!mystring.isEmpty(sorttype)){
					sql+=(" order by ='"+fieldmark+"' "+sorttype);
				}
				sql+=(" limit "+pagesize);
				SqlProcess ab_lib=(SqlProcess)attribManage.attf.getInstance(define.dataOpType_lib);
				if(ab_lib==null)throw  new Exception("lib no defined!");
				List<Object> ret=ab_lib.sqlExec(sql);
				int count=ret.size();
				for(int i=0;i<count;i++){
					attrib ab=(attrib)ret.get(i);
					tablerec tr=new tablerec(tb,ab.mark,selectField);
					if(tr.loadrec()){
						resultlist.add(tr);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultlist;
	}
	/**
	 * 表记录修改
	 * @param tablename		表名
	 * @param recmark		记录ID
	 * @param updatemap		修改键值对
	 * @return
	 */
	public boolean updateTablerec(String tablename,String recmark,HashMap<String,String>updatemap){
		tb=tableManage.getTableFromName(tablename);
		for(String key:updatemap.keySet()){
			String fieldmark=tb.getFieldMark(key);
			attrib ab=new attrib();
			ab.initRec(recmark,fieldmark,updatemap.get(key),"");
			try {
				attribManage.updateAttrib(ab);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	/**
	 * 表记录删除
	 * @param tablename		表名
	 * @param recmark		记录ID
	 * @return
	 */
	public boolean deleteTablerec(String tablename,String recmark){
		tb=tableManage.getTableFromName(tablename);
		ArrayList<node>flist=tb.getFieldlist();
		node nd=new node();
		nd.lmark=recmark;
		try {
			nodeManage.removeNode(nd);
			for(node subnd:flist){
				attrib ab=new attrib();
				ab.initRec(recmark,subnd.lmark, "", "");
				attribManage.removeAttrib(ab);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
}
