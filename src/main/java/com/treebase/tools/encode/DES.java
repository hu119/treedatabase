package com.treebase.tools.encode;


import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DES {
	static String password="debug info";
	public DES() {
	}
	public static String des_encode(String str){
		MD5Code code=new MD5Code();
		String pwd=code.getMD5ofStr(password);
		byte[] result= DES.encrypt(str.getBytes(),pwd);
		try {
			return base64.encodeBase64(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	public static String des_decode(String str){
		byte[] result;
		try {
			MD5Code code=new MD5Code();
			String pwd=code.getMD5ofStr(password);
			result = base64.decodeBase64(str);
			byte[] decryResult = DES.decrypt(result, pwd);
			return new String(decryResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	public static String des_getMd5(String str){
		MD5Code code=new MD5Code();
		String result=code.getMD5ofStr(str);
		return result;
	}
	//����
	public static void main(String args[]) {
		//����������
		String str = "1.2.3.4.5����";
		//���룬����Ҫ��8�ı���
		
	
		String tmp=des_encode(str);
		String result=des_decode(tmp);
			
			
		}

		/**
		* ����
		* @param datasource byte[]
		* @param password String
		* @return byte[]
		*/
		public static byte[] encrypt(byte[] datasource, String password) { 
			try{
				SecureRandom random = new SecureRandom();
				DESKeySpec desKey = new DESKeySpec(password.getBytes());
				//����һ���ܳ׹�����Ȼ��������DESKeySpecת����
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
				SecretKey securekey = keyFactory.generateSecret(desKey);
				//Cipher����ʵ����ɼ��ܲ���
				Cipher cipher = Cipher.getInstance("DES");
				//���ܳ׳�ʼ��Cipher����
				cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
				//���ڣ���ȡ���ݲ�����
				//��ʽִ�м��ܲ���
			return cipher.doFinal(datasource);
			}catch(Throwable e){
				e.printStackTrace();
			}
			return null;
		}
		/**
		* ����
		* @param src byte[]
		* @param password String
		* @return byte[]
		* @throws Exception
		*/
		public static byte[] decrypt(byte[] src, String password) throws Exception {
			// DES�㷨Ҫ����һ�������ε������Դ
			SecureRandom random = new SecureRandom();
			// ����һ��DESKeySpec����
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// ����һ���ܳ׹���
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			// ��DESKeySpec����ת����SecretKey����
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher����ʵ����ɽ��ܲ���
			Cipher cipher = Cipher.getInstance("DES");
			// ���ܳ׳�ʼ��Cipher����
			cipher.init(Cipher.DECRYPT_MODE, securekey, random);
			// ������ʼ���ܲ���
			return cipher.doFinal(src);
		}
	}