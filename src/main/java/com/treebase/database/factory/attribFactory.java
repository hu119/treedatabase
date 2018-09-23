package com.treebase.database.factory;

import com.treebase.database.cache.map.myAttribMap;
import com.treebase.database.cache.memcache.myAttribMem;
import com.treebase.database.cache.redis.myAttribRedis;
import com.treebase.database.conf.define;
import com.treebase.database.conf.treeConfig;
import com.treebase.database.datasource.mysql.myAttribLib;

/**
 * 树型列式数据库  工厂类 管理属性对象
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class attribFactory implements myFactory{

	public Object getInstance(String key) {
		// TODO Auto-generated method stub
		if(key.equalsIgnoreCase(define.dataOpType_lib)){
			if(treeConfig.libtype.equalsIgnoreCase(define.dataOpType_mysql)){
				return new myAttribLib();
			}
		}else if(key.equalsIgnoreCase(define.dataOpType_cache)){
			if(treeConfig.libtype.equalsIgnoreCase(define.dataOpType_redis)){
				return new myAttribRedis();
			}else if(treeConfig.libtype.equalsIgnoreCase(define.dataOpType_memcache)){
				return new myAttribMem();
			}else if(treeConfig.libtype.equalsIgnoreCase(define.dataOpType_map)){
				return new myAttribMap();
			}
		}
		return null;
	}

}
