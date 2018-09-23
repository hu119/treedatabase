package com.treebase.database.conf;

import com.treebase.database.config.myConfig;
import com.treebase.database.config.myConfigFactory;
import com.treebase.tools.string.mystring;

public class treeConfig {
	public static	String	node_tablename="HINODE";
	public static	String	index_tablename="TYPEINDEX";
	public static	String	attrib_tablename="CLASSATTRIB";
	
	public static String 	libtype="mysql";
	public static String 	cachetype="map";
	public static long		beart_checkconnect=1000*30;
	
	
	public static final String DEFINE_NODE_TABLENAME="treeConfig.node_tablename";
	public static final String DEFINE_INDEX_TABLENAME="treeConfig.index_tablename";
	public static final String DEFINE_ATTRIB_TABLENAME="treeConfig.attrib_tablename";
	
	public static final String DEFINE_LIBTYPE="treeConfig.libtype";
	public static final String DEFINE_CACHETYPE="treeConfig.cachetype";
	public static final String DEFINE_BEART_CHECKCONNECT="treeConfig.beart_checkconnect";
	
	
	
	public static void initDbConfig(String filename){
		myConfig fg=myConfigFactory.getConfig(filename,true);
		node_tablename=fg.getKey(DEFINE_NODE_TABLENAME, node_tablename);
		index_tablename=fg.getKey(DEFINE_INDEX_TABLENAME, index_tablename);
		attrib_tablename=fg.getKey(DEFINE_ATTRIB_TABLENAME, attrib_tablename);
		libtype=fg.getKey(DEFINE_LIBTYPE, libtype);
		cachetype=fg.getKey(DEFINE_CACHETYPE, cachetype);
		beart_checkconnect=mystring.myStringToLong(fg.getKey(DEFINE_CACHETYPE, Long.toString(beart_checkconnect)));
		
	}
}
