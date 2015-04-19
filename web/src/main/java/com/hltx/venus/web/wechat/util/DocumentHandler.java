package com.hltx.venus.web.wechat.util;


import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
/**
 * 
 * @author Chencheng;
 *
 */
public class DocumentHandler {
	private Configuration cfg = null;
	
	/**
	 * 构造子 private;
	 * @param directory	存放模版的目录
	 */
	private DocumentHandler(String directory) {
		cfg = new Configuration(Configuration.VERSION_2_3_22);
		try {
			cfg.setDirectoryForTemplateLoading(new File(getRealPath(directory)));
			cfg.setDefaultEncoding("UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 静态工厂
	 * 获取{@link DocumentHandler}对象
	 * @param directory	模版文件所在的目录文件
	 * @return			{@link DocumentHandler}对象
	 */
	public static DocumentHandler getInstance(String directory) {
		return new DocumentHandler(directory);
	}
	
	/**
	 * 得到目录的真实路径
	 * @param filename
	 * @return
	 */
	private String getRealPath(String filename) {
		URL url = DocumentHandler.class.getClassLoader().getResource(filename);
		if(url == null) {
			throw new RuntimeException("file is not exist!");
		}
		
		return url.getPath();
	}
	/**
	 * 得到一个带缓冲的输出流
	 * os 为写入的目的地；
	 * @return
	 */
	private Writer getWriter(OutputStream os) {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(
					new OutputStreamWriter(os,"UTF-8"));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return out;
	}
	/**
	 * 关闭流
	 * @param out	带缓冲的输出流
	 */
	private void close(Writer out) {
		if(out==null)
			return;
		try {
			out.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 导出DOC 文件
	 * @param template		模版文件
	 * @param hash要真充的参数
	 */
	public ByteArrayOutputStream  process(String template, Map<String, Object> map) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();

		Template t = null;
		Writer out = null;
		try {
			t = cfg.getTemplate(template);
			out = getWriter(bos);
			t.process(map, out);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			close(out);
		}
		return bos;
	}
	
	
	/**
	 * 将数据加入到SimpleHash（Map）中;
	 * @param sh {@link SimpleHash}对象
	 * @throws UnsupportedEncodingException 
	 */
	 /*abstract void getData(SimpleHash sh);*/
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("toUser", "123");
		/*ByteArrayOutputStream os = DocumentHandler.getInstance("file").process("text_message.ftl", map);
		String s = new String(os.toByteArray(), "utf-8");
		System.out.println(s);*/
	}
	
}
