package com.treebase.tools.string;

public class mystring {

	public static boolean isEmpty(String str){
		if(str==null||str.length()==0)return true;
		return false;
	}
//	public static boolean isNumeric(String str){
//	    Pattern pattern = Pattern.compile("[0-9]*");
//	    return pattern.matcher(str).matches();   
//	}
	public static boolean isNumeric(String str){
		String reg = "^[0-9]+(.[0-9]+)?$";  
	    return str.matches(reg);  
	}
	
	public static long myStringToLong(String str){
		if(!isNumeric(str))return 0;
		return Long.parseLong(str);
	}
	public static int myStringToInt(String str){
		if(!isNumeric(str))return 0;
		return Integer.parseInt(str);
	}
}
