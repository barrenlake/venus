package com.hltx.venus.web.wechat.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class IOUtil {
	
	/**
	 * 
	 * @param filename
	 * @return
	 */
	public static String load(String filename) {
		StringBuffer buffer = new StringBuffer();
		InputStream is = IOUtil.class.getClassLoader().getResourceAsStream(filename);
		BufferedReader reader = null;
		try {
			reader = getReader(is);
			String line = null;
			do{
				line = reader.readLine();
				if(line != null)
					buffer.append(line.trim());
			}while(line != null);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(reader != null)
					reader.close();
			} catch (IOException e) {
				
			}
		}
		System.out.println(buffer.toString());
		return buffer.toString();
	}
	
	private static BufferedReader getReader(InputStream is) throws UnsupportedEncodingException {
		BufferedReader reader = null;
		reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		return reader;
		
	}
	/*
	private BufferedWriter getWriter(OutputStream os) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(
					new OutputStreamWriter(os,"UTF-8"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return writer;
	}*/
	
/*	private String getRealPath(String filename) {
		URL url = IOUtil.class.getClassLoader().getResource(filename);
		if(url == null) {
			throw new RuntimeException("file is not exist!");
		}
		return url.getPath();
	}
	*/
	
}
