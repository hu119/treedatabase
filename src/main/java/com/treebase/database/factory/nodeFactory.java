package com.treebase.database.factory;

import com.treebase.database.cache.map.myNodeMap;
import com.treebase.database.cache.memcache.myNodeMem;
import com.treebase.database.cache.redis.myNodeRedis;
import com.treebase.database.conf.define;
import com.treebase.database.conf.treeConfig;
import com.treebase.database.datasource.mysql.myNodeLib;
/**
 * 树型列式数据库  工厂类 管理树节点对象
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class nodeFactory implements myFactory{

	public Object getInstance(String key) {
		// TODO Auto-generated method stub
		if(key.equalsIgnoreCase(define.dataOpType_lib)){
				if(treeConfig.libtype.equalsIgnoreCase(define.dataOpType_mysql)){
					return new myNodeLib();
				}
		}else if(key.equalsIgnoreCase(define.dataOpType_cache)){
			if(treeConfig.cachetype.equalsIgnoreCase(define.dataOpType_redis)){
				return new myNodeRedis();
			}else if(treeConfig.cachetype.equalsIgnoreCase(define.dataOpType_memcache)){
				return new myNodeMem();
			}else if(treeConfig.cachetype.equalsIgnoreCase(define.dataOpType_map)){
				return new myNodeMap();
			}
		}
		return null;
	}
}
