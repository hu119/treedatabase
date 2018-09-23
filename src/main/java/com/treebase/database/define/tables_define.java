package com.treebase.database.define;
/**
 * 树型列式数据库 初始表参数定义
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 * 该模块已忽略，所有信息动态处理！！！
 */
public class tables_define {
	public final static String rec_root="F65670D6E503B653AFC3C8E75BE110B7";
	
	public final static String defaultCompany="default_company";
	
	public enum table_def{
			system(0),				//系统信息
			company(1),				//公司信息
			macgroup(2),			//机器分组
			usergroup(3),			//用户分组
			macinfo(4),				//机器信息
			user(5),				//用户
			session(6),				//会话
			gui(7),					//界面项
			program(8),				//程序
			version(9),				//版本信息
			disk(10),				//磁盘分区
			NODEMEM(11),			//内存信息
			NODECPU(12),			//CPU信息
			runprocess(13),		//运行进程
			command(14),			//运行进程
			clusterparam(15);		//集群参数
		
			
			private int value;
		    private table_def(int val){
		    	value=val;
		    }
	}
	public enum system_def{
		ver(0),path(1);
		private int value;
	    private system_def(int val){
	    	value=val;
	    }
	}
	public enum commpany_def{
		companyid(0),companyname(1),ploatver(2);
		private int value;
	    private commpany_def(int val){
	    	value=val;
	    }
	}
	
	public enum user_def{
		username(0),passwd(1);
		private int value;
	    private user_def(int val){
	    	value=val;
	    }
	}
	public enum maclib_def{
		uuid(0),name(1),ips(2),companyid(3),ploatver(4),
		systemname(5),systemver(6),mark(7),creatime(8),
		actime(9),status(10),outips(11),ftpport(12),ftpuser(13),
		ftppassword(14);
		private int value;
	    private maclib_def(int val){
	    	value=val;
	    }
	}
	public enum disk_def{
		avail(0),userp(1),userd(2),filesystem(3),
		size(4),mount(5);
		private int value;
	    private disk_def(int val){
	    	value=val;
	    }
	}
	public enum NODEMEM_def{
		precentsys(0),swapmem(1),remainmem(2),totalmem(3),
		timeval(4);
		private int value;
	    private NODEMEM_def(int val){
	    	value=val;
	    }
	}
	
	public enum NODECPU_def{
		precentsys(0),cores(1),timeval(2);
		private int value;
	    private NODECPU_def(int val){
	    	value=val;
	    }
	}
	public enum runprocess_def {
		name(0),pgclass(1),pid(2),uuid(3),ips(4),jobid(5),starttime(6),activetime(7);
	    private int value;
	    private runprocess_def(int val){
	    	value=val;
	    }
	}
	//命令说明
	public enum command_desc_def{
		cmdid(0),timeval(1),content(2);
		private int value;
	    private command_desc_def(int val){
	    	value=val;
	    }
	}
	//命令
	public enum command_def{
		cmdid(0),clusterid(1),content(2),name(3),starttime(4),endtime(5),type(6),
		status(7),status_desc(8),progress(9),user(10),companyid(11),uuid(12),
		hostname(13),ip(14);
		private int value;
	    private command_def(int val){
	    	value=val;
	    }
	}
	public enum session_def{
		companyid(0),user(1),passwd(2),sessionid(3),
		remmber(4),timeval(5);
		private int value;
	    private session_def(int val){
	    	value=val;
	    }
	}
	public enum gui_def{
		id(0),name(1),url(2),img(3),posx(4),
		posy(5),width(6),height(7),mode(8),typemark(9),no(10),
		icon16(11);
		private int value;
	    private gui_def(int val){
	    	value=val;
	    }
	}
	public enum program_def{
		raw(0),descs(1),status(2),setuptime(3),version(4),setuppath(5),
		icon16(6),classname(7),serverpath(8),icon48(9),icon32(10),user(11),
		name(12),showname(13),self_mark(14),type(15),checkmark(16),floatver(17);
		private int value;
	    private program_def(int val){
	    	value=val;
	    }
	}
	public enum function_def{
		background_image(0),backup_color(1);
		private int value;
	    private function_def(int val){
	    	value=val;
	    }
	}
	public enum clusterparam_def{
		maxnums(0);
		private int value;
	    private clusterparam_def(int val){
	    	value=val;
	    }
	}
	
	
	
	
	//////////////////////状态和类型定义
	public enum define_process_status{
		run(0),stop(1),start(2);
		private int value;
	    private define_process_status(int val){
	    	value=val;
	    }
	}
	//机器状态
	public enum define_macinfo_status{
		stop(0),initdown(1),run(2);
		private int value;
	    private define_macinfo_status(int val){
	    	value=val;
	    }
	}
	//命令状态定义
	public enum define_command_status{
		wait(0),down(1),start(2),process(3),successful(4),fail(5);
		private int value;
	    private define_command_status(int val){
	    	value=val;
	    }
	    public static String getname(int no){
	    	switch(no){
	    	case 0:
	    		return "等待";	    	
	    	case 1:
	    		return "下发";
	    	case 2:
	    		return "开始";
	    	case 3:
	    		return "处理";
	    	case 4:
	    		return "成功";
	    	case 5:
	    		return "失败";
	    	}
	    	return "";
	    }
	}
	//程序状态
	public enum define_program_status{
		wait(0),unsetuped(1),canmodify(2),setuping(3),unsetuping(4),setuped(5);
		private int value;
	    private define_program_status(int val){
	    	value=val;
	    }
	}
	//节点操作属性
	public enum define_node_opattrib{
		skipExist(1);
		private int value;
	    private define_node_opattrib(int val){
	    	value=val;
	    }
	}
	
}
