package com.treebase.database;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.treebase.database.conf.dbconfig;
import com.treebase.database.conf.treeConfig;
import com.treebase.database.system.tableManage;
/**
 * 树型列式数据库初始化
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */

public class treeDatabase {

	private ScheduledExecutorService heartbeatScheduler;
	
	public static void startup(String xmlname){
		
		dbconfig.initDbConfig(xmlname);
		treeConfig.initDbConfig(xmlname);
		
		tableManage.relaodFieldTypeList();
		tableManage.relaodNodeTypeList();
		tableManage.relaodTableList();
	}
	public treeDatabase(){
		//
		heartbeatScheduler = Executors.newSingleThreadScheduledExecutor();
		heartbeatScheduler.scheduleAtFixedRate(dataNodeHeartbeat(), 0L, treeConfig.beart_checkconnect,TimeUnit.MILLISECONDS);
	}
	// ���ݽڵ㶨ʱ��������
	private Runnable dataNodeHeartbeat() {
		return new Runnable() {
			public void run() {
				
			}
		};
	}
}
