package com.treebase.database.config;

/**
 * 配置文件操作工厂类
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */

public class myConfigFactory{

	public static myConfig getConfig(String filepath,boolean blayer){
		if(filepath==null||filepath.length()==0)
			throw new NullPointerException("filepath is null");
		
		int pos=filepath.lastIndexOf(".");
		if(pos==-1)return null;
		String ext=filepath.substring(pos+1);
		if(ext.equalsIgnoreCase("properties")){
			return new ropertiesConfig(filepath);
		}else if(ext.equalsIgnoreCase("xml")){
			if(blayer){
				return new xmlLayerConfig(filepath);
			}else{
				return new xmlPropertyeConfig(filepath);
			}
		}
		return null;
	}
}
