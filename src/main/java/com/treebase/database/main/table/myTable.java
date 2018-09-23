package com.treebase.database.main.table;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.treebase.database.conf.define;
import com.treebase.database.system.table;
import com.treebase.tools.string.mystring;
/**
 * 树型列式数据库  
 * 数据库表操作基类
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public abstract class  myTable {
	
	TreeMap<String,tablerec> getRecList(table tb,
			String marklist,
			String selectField,
			String sortfield){
		TreeMap<String,tablerec> retmap=new TreeMap<String,tablerec>();
		String[] arr=marklist.split(",");
		for(String mark:arr){
			try {
				tablerec tr=new tablerec(tb,mark,selectField);
				if(tr.loadrec()){
					retmap.put(tr.getKey(sortfield), tr);
				}
			} catch (Exception e) {
				//TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return retmap;
	}
	ArrayList<Object> getLimitList(ArrayList<tablerec> reclist, 
									String beginMark, 
									int pagesize,
									String sortField,
									String sorttype){
		
		ArrayList<tablerec> retobj=new ArrayList<tablerec>();
		boolean badd=false;
		int     count=0;
		if(mystring.isEmpty(sorttype)||sorttype.equalsIgnoreCase(define.sort_asc)){
			for(tablerec rec:reclist){
				if(badd){
					if(count>pagesize)break;
					count++;
					retobj.add(rec);
				}else{
					if(mystring.isEmpty(beginMark)){
						badd=true;
						count++;
						retobj.add(rec);
					}else{
						if(rec.recmark.compareToIgnoreCase(beginMark)>0){
							badd=true;
							count++;
							retobj.add(rec);
						}
					}
				}
			}
		}else if(mystring.isEmpty(sorttype)||sorttype.equalsIgnoreCase(define.sort_desc)){
			int size=reclist.size();
			for(int i=size-1;i>0;i--){
				tablerec rec=reclist.get(i);
				if(badd){
					if(count>pagesize)break;
					count++;
					retobj.add(rec);
				}else{
					if(mystring.isEmpty(beginMark)){
						badd=true;
						count++;
						retobj.add(rec);
					}else{
						if(rec.recmark.compareToIgnoreCase(beginMark)<0){
							badd=true;
							count++;
							retobj.add(rec);
						}
					}
				}
			}					
		}
		return null;
	}
	
	public abstract List<Object> getResult(String parentMark, 
										  String beginMark, 
										  int pagesize,
										  String selectField,
										  String sortField,
										  String sorttype);
}
