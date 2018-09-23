package com.treebase.database.factory;

import com.treebase.database.cache.map.myIndexMap;
import com.treebase.database.cache.memcache.myIndexMem;
import com.treebase.database.cache.redis.myIndexRedis;
import com.treebase.database.conf.define;
import com.treebase.database.conf.treeConfig;
import com.treebase.database.datasource.mysql.myIndexLib;
/**
 * 树型列式数据库  工厂类 管理ID对象
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class indexFactory implements myFactory{

	public Object getInstance(String key) {
		// TODO Auto-generated method stub
		if(key.equalsIgnoreCase(define.dataOpType_lib)){
			if(treeConfig.libtype.equalsIgnoreCase(define.dataOpType_mysql)){
				return new myIndexLib();
			}
		}else if(key.equalsIgnoreCase(define.dataOpType_cache)){
			if(treeConfig.libtype.equalsIgnoreCase(define.dataOpType_redis)){
				return new myIndexRedis();
			}else if(treeConfig.libtype.equalsIgnoreCase(define.dataOpType_memcache)){
				return new myIndexMem();
			}else if(treeConfig.libtype.equalsIgnoreCase(define.dataOpType_map)){
				return new myIndexMap();
			}
		}
		return null;
	}
	

}
