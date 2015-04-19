package com.hltx.venus.web.wechat.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA加密
 * @author Chen
 *
 */
public class SHA {
	
	public static byte[] encrypt(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		return md.digest(data);
	}
	
	public static String encrypt(String data) throws NoSuchAlgorithmException {
		byte[] encryptedBytes = encrypt(data.getBytes());
		return bytesToString(encryptedBytes);
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String s = SHA.encrypt("1428644147424230924chenchen");
		System.out.println(s);
	}
	
	/*public  static void main(String[] args) throws NoSuchAlgorithmException {
		String s = SHA.encrypt("1428644147424230924chenchen");
		System.out.println(s);
	}*/

	 private static String bytesToString(byte[] byteArray) {  
	        String strDigest = "";  
	        for (int i = 0; i < byteArray.length; i++) {  
	            strDigest += byteToHexString(byteArray[i]);  
	        }  
	        return strDigest;  
	    }  
	 
	 private static String byteToHexString(byte ｂ) {  
	        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
	        char[] tempArr = new char[2];  
	        tempArr[0] = Digit[(ｂ >>> 4) & 0X0F];  
	        tempArr[1] = Digit[ｂ & 0X0F];  
	  
	        String s = new String(tempArr);  
	        return s;  
	    }  
}
