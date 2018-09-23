package com.treebase.database.conf;

import com.treebase.database.config.myConfig;
import com.treebase.database.config.myConfigFactory;
import com.treebase.tools.string.mystring;

public class dbconfig {
	public static String driver="com.mysql.jdbc.Driver";
	public static String hostname="192.168.1.203";
	public static String port="3306";
	public static String basename="hibig";
	public static String username="root";
	public static String password="123456";
	
	public static long	  maxcon=100;
	public static long	  mincon=3;
	
	public static long	  cacheChildrenNums=128; //
	
	public final static String DEFINE_DRIVER="database.mysql.driver";
	public final static String DEFINE_HOSTNAME="database.mysql.hostname";
	public final static String DEFINE_PORT="database.mysql.port";
	public final static String DEFINE_BASENAME="database.mysql.basename";
	public final static String DEFINE_USERNAME="database.mysql.username";
	public final static String DEFINE_PASSWORD="database.mysql.password";
	public final static String DEFINE_MAXCON="database.mysql.conpool.maxcon";
	public final static String DEFINE_MINCON="database.mysql.conpool.mincon";
	public final static String DEFINE_CACHECHILDRENNUMS="database.mysql.cacheChildrenNums";

	
	public static void initDbConfig(String filename){
		
		myConfig fg=myConfigFactory.getConfig(filename,true);
		driver=fg.getKey(DEFINE_DRIVER, driver);
		hostname=fg.getKey(DEFINE_HOSTNAME, hostname);
		port=fg.getKey(DEFINE_PORT, port);
		basename=fg.getKey(DEFINE_BASENAME, basename);
		username=fg.getKey(DEFINE_USERNAME, username);
		password=fg.getKey(DEFINE_PASSWORD, password);
		maxcon=mystring.myStringToLong(fg.getKey(DEFINE_MAXCON, Long.toString(maxcon)));
		mincon=mystring.myStringToLong(fg.getKey(DEFINE_MINCON, Long.toString(mincon)));
		cacheChildrenNums=mystring.myStringToLong(fg.getKey(DEFINE_CACHECHILDRENNUMS, Long.toString(cacheChildrenNums)));
		
		
	}
	
}
