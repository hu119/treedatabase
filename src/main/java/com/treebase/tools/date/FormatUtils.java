package com.treebase.tools.date;

import java.text.DecimalFormat;  
import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
  
/** 
 * ���ܸ�ǿ��ĸ�ʽ�������� 
 */  
public class FormatUtils {  
    private static SimpleDateFormat second = new SimpleDateFormat("yy-MM-dd hh:mm:ss");  
    private static SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");  
    private static SimpleDateFormat detailDay = new SimpleDateFormat("yyyy��MM��dd��");  
    private static SimpleDateFormat fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");  
    private static SimpleDateFormat tempTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    private static SimpleDateFormat excelDate = new SimpleDateFormat("yyyy/MM/dd");
    
    
    private static SimpleDateFormat easyuiDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
     
    
  /** 
   * ��ʽ��easyui�е�ʱ�� 
   * @param date 
   * @return 
   */  
  public static  long Easyui_dateString_to_timeval(String date) {
	  long timeval=0;
	  try {
		  if(date==null)return timeval;
		  String datestr=date;
		  if(datestr.length()==10)datestr+=" 00:00:00";
		  
		Date curdate=easyuiDate.parse(datestr);
		timeval=curdate.getTime()/1000;
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  
      return timeval;  
  }
  public static String Easyui_timeval_to_dateString(long timeval) {  
	  Date date=new Date();
	  date.setTime(timeval*1000);
	  return easyuiDate.format(date);
  }
  
  public static String timeval_to_str_def(long timeval,String formatdef) {  
	  Date date=new Date();
	  date.setTime(timeval*1000);
	  SimpleDateFormat datedef = new SimpleDateFormat(formatdef);
	  return datedef.format(date);
  }
  
    /** 
     * ��ʽ��excel�е�ʱ�� 
     * @param date 
     * @return 
     */  
    public static String formatDateForExcelDate(Date date) {  
        return excelDate.format(date);  
    }  
      
    /** 
     * �����ڸ�ʽ����Ϊ�ļ��� 
     * @param date 
     * @return 
     */  
    public static String formatDateForFileName(Date date) {  
        return fileName.format(date);  
    }  
  
    /** 
     * ��ʽ������(��ȷ����) 
     *  
     * @param date 
     * @return 
     */  
    public static String formatDateSecond(Date date) {  
        return second.format(date);  
    }  
      
    /** 
     * ��ʽ������(��ȷ����) 
     *  
     * @param date 
     * @return 
     */  
    public static String tempDateSecond(Date date) {  
        return tempTime.format(date);  
    }  
  
    public static Date tempDateSecond(String str) {  
        try {  
            return tempTime.parse(str);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return new Date();  
    }  
    /** 
     * ��ʽ������(��ȷ����) 
     *  
     * @param date 
     * @return 
     */  
    public static String formatDateDay(Date date) {  
        return day.format(date);  
    }  
      
    /** 
     * ��ʽ������(��ȷ����) 
     *  
     * @param date 
     * @return 
     */  
    public static String formatDateDetailDay(Date date) {  
        return detailDay.format(date);  
    }  
  
    /** 
     * ��double���͵����ֱ�����λС�����������룩 
     *  
     * @param number 
     * @return 
     */  
    public static String formatNumber(double number) {  
        DecimalFormat df = new DecimalFormat();  
        df.applyPattern("#0.00");  
        return df.format(number);  
    }  
  
    /** 
     * ���ַ���ת�������� 
     *  
     * @param date 
     * @return 
     * @throws Exception 
     */  
    public static Date formateDate(String date) throws Exception {  
        return day.parse(date);  
    }  
      
    /** 
     * ���ַ�����ת����Date 
     * @param date 
     * @return 
     * @throws Exception 
     */  
    public static Date parseStringToDate(String date) throws Exception {  
        return day.parse(date);  
    }  
      
    public static String formatDoubleNumber(double number) {  
        DecimalFormat df = new DecimalFormat("#");  
        return df.format(number);  
    }  
    public static long getCurrentDateTime(){
    	Date date=new Date();
    	return date.getTime();
    }
    public static long getCurrentTime(){
    	Date date=new Date();
    	return date.getTime()/1000;
    	
    }
    public static String getCurrentDate(){
    	Date date=new Date();
    	
    	return tempTime.format(date);
    }
}  