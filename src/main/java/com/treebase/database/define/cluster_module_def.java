package com.treebase.database.define;
/**
 * 树型列式数据库 初始参数定义
 * @author 胡志水
 * Copyright(c) 2012-2013 胡志水
 * QQ:397874092
 * hu119_3@163.com
 */
public class cluster_module_def {
	
	public final static String cluster_root="C4CA4238A0B923820DCC509A6F75849B";
	
	public final static String node_company_root="9E605CE3BB6AD134BB55C54D861CEB6A";
	public final static String node_type_root="777D45BBBCDF50D49C42C70AD7ACF5FE";
	public final static String node_attrib_root="56765472680401499C79732468BA4340";
	public final static String node_system_root="6008647277C4454CECD97D33C069F0CA";		//系统根位置
	
	public final static String node_attrib="BF701DAE9AFF5BB22B8F000DC9BF6199";
	public final static String field_attrib="ACB766C6067F046105F8B1263B9E5172";
	
	
	public final static String node_session_root="0B0EC9F1CC28B3C19DC6C36DCD5AF7CC";
	
	
	public static class node_type_def{
		public final static String program_type_root="98660805CDEE362A748327AD6032805B";			//程序类型
		public final static String node_res_type_root="7158B3695F2009939C367485DA9C0D12";			//节点资源类型定义
		public final static String action_type_root="E178BDA012CB82BD209DC5B0D739BA5F";			//动作操作
		public final static String paramset_type_root="78CE6525D96DA9015FF548F9EB606A02";			//节点资源类型定义
		public final static String command_type_root="E178BDA012CB82BD209DC5B0D739BA5F";
		
		//节点资源
		public final static String node_res_type_name="node_info";						//节点资源类型定义
		public final static String node_res_disk_name="disk";
		
		
	}	
	public static class node_system_def{
		public final static String programdata_root="F724C0DFD18B481D8B3D8E35D15FBCDC"; //程序数据
		public final static String macdata_root="3C1DE4A43DAA0FE17B5080559CFF640E";		//机器数据
		public final static String shared_root="0CEA24AE3B134C67DFF89B49C55EA750";		//多用户共享信息
		public final static String userdata_root="0F0334DB42BDFBE01FD0C587B5B10CCE";	//用户数据
		public final static String commanddata_root="51A3950C50505D7F5D8217ED8913F870";	//命令数据
		
		
		//以下等待删除
		public final static String function_root="3C1DE4A43DAA0FE17B5080559CFF640E";	//功能模块
		public final static String sysop_root="51A3950C50505D7F5D8217ED8913F870";		//系统操作命令
		
		
		//程序数据
		public final static String programdata_name="programdata";
		public final static String programdata_dirname="dirname";		//程序目录
		public final static String programdata_shobj="shobj";			//程序脚本
	
		//机器数据
		public final static String macdata_name="macdata";
		public final static String macdata_program="program";			//程序信息
		public final static String macdata_resouce="resouce";			//机器资源
		public final static String macdata_command="command";			//命令
		public final static String macdata_runinfo="runinfo";			//运行进程
		
		//多用户共享信息项		
		public final static String shared_name="usershare";
		public final static String shared_user="user";				//用户信息
		public final static String shared_usergroup="usergroup";	//用户分组
		public final static String shared_session="session";		//会话
		public final static String shared_macinfo="macinfo";		//机器信息
		public final static String shared_commandcluster="commandcluster"; //集群命令
		
		public final static String userdata_name="userdata";		//用户数据
		public final static String userdata_macgroup="macgroup";	//机器分组
		public final static String userdata_function="function";	//功能模块

		//功能模块项
		public final static String function_name="function";			
		public final static String function_main_screen="main_screen";	//main_screen
		public final static String function_summary="summary";			//综合信息
		public final static String function_config="config";			//系统设置
		public final static String function_login="login";				//登录系统
		
		
		//命令数据
		public final static String commanddata_name="commanddata";		
		public final static String commanddata_setup="setup";			//安装
		public final static String commanddata_unsetup="unsetup";		//卸载
		public final static String commanddata_query="query";			//查询
		public final static String commanddata_init="init";				//初始化
		public final static String commanddata_broadcast="broadcast";	//广播
		public final static String commanddata_update="update";			//升级
		
		
	}
	
	//以下等待删除
	public static class node_company_def{
		public final static String macinfo_root_name="macinfo";
		public final static String user_root_name="user";
		public final static String program_root_name="program";
		public final static String usergroup_root_name="usergroup";
	}
	public static class node_macinfo_def{
		public final static String program_root_name="program";
		public final static String command_root_name="command";
		public final static String runinfo_root_name="runinfo";
		
		public final static String runinfo_mac_root_name="runmac";
		public final static String runinfo_pg_root_name="runprocess";
		
	}
	
	//程序脚本
	public static class program_shobj_def{
		public final static String program_shobj="shobj";
		public final static String program_setupsh="setupsh";
		public final static String program_unsetupsh="unsetupsh";
		public final static String program_checksh="checksh";
		public final static String program_startsh="startsh";
		public final static String program_setup_class="setup_class";
		public final static String program_unsetup_class="unsetup_class";
		public final static String program_check_class="check_class";
		
	}
	//程序目录
	public static class program_dir_def{
		public final static String program_dir="dirname";
		public final static String program_data_dir="data_dir";
		public final static String program_bin_dir="bin_dir";
		public final static String program_sbin_dir="sbin_dir";
		public final static String program_lib_dir="lib_dir";
		
	}
	
	
}
