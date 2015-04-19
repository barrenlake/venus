package com.hltx.venus.web.wechat.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * XML文档操作; 
 * @FileName xmlUtil.java
 * @author ChenCheng
 * @version 1.0
 * @Date 2012-7-30 下午1:29:00
 */
public class XmlUtil {
	/**
	 * 获到Document对象;
	 * @param file  要解析的文件;	
	 * @param ecoding 文件编码格式(以什么编码方式读取);
	 * @return   返回 org.dom4j.Document对象;
	 * @throws Exception
	 */
	public static Document getDocument(File file , String ecoding) throws Exception{
		InputStream in = new FileInputStream(file);
		return getDocument(in, ecoding);
	}

	public static Document getDocument(InputStream in, String ecoding)
			throws UnsupportedEncodingException, DocumentException, IOException {
		Reader reader = new InputStreamReader(in,ecoding);
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(reader);
		reader.close();
		return document;
	}
	
	/**
	 * 获到Document对象;
	 * @param filepath  要解析的文件所在的路径;	
	 * @param ecoding 文件编码格式(以什么编码方式读取);
	 * @return   返回 org.dom4j.Document对象;
	 * @throws Exception
	 */
	public static Document getDocument(String filepath , String ecoding) throws Exception{
		filepath = URLDecoder.decode(filepath, "utf-8");
		InputStream in = new FileInputStream(filepath);
		return getDocument(in, ecoding);
	}
	
	
	/**
	 * 获取DOM树的根节点;
	 * @param doucument org.dom4j.Document;
	 * @return 返回DOM树的根;
	 * @throws Exception 
	 */
	public static Element getRootElement(Document document) throws Exception{
		Element root = document.getRootElement();
		return root;
	}
	
	/**
	 * 获取某节点下的标签名为nodeName的子节点;
	 * @param e  DOM树中的某个Element元素;
	 * @param nodeName 节点名字;
	 * @return e Element下所有标签名为nodeName子元素;
	 */
	@SuppressWarnings({ "unchecked" })
	public static List<Element> getChildElements(Element e ,String nodeName){
		List<Element> childElements = e.elements(nodeName);
		return childElements;
	}
	
	/**
	 * 获取某节点下的所有子节点;
	 * @param e  DOM树中的某节点;
	 * @return  DOM树中e节点的子元素;
	 */
	@SuppressWarnings({"unchecked"})
	public static List<Element> getChildElements(Element e){
		List<Element> childElements  = e.elements();
		return childElements;
	}
	
	/**
	 * 以XPath方式获取标签名为nodeName 所有的子节点
	 * @param document  org.dom4j.Document对象;
	 * @param nodeName  某一节点的名字
	 * @return 返回DOM树中所有符合nodeName标签的集合;
	 */
	@SuppressWarnings({ "unchecked" })
	public static List<Element> getChildElements(Document document , String nodeName){
		List<Element> list = document.selectNodes("//"+nodeName);
		return list;
	}

	/**
	 * 选择所有带指定属性的节点
	 * @param document	{@link Document}对象
	 * @attrname 		{@link String}类型的属性名 
	 * @return			{@link List}<{@link Element}>  返回带指定属性的节点集合
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> getElementsByAttribute(Document document, String attrname){
		 return document.selectNodes("//*[@"+attrname+"]");
	}
	
	/**
	 * 查找指定name属性下的所有子节点
	 * @param document	{@link Document}对象
	 * @param attr		name属性名
	 * @return			name属性节点下的所有子节点
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> getChildElementsByNameAttr(Document document, String attr){
		Element element = (Element) document.selectSingleNode("//*[@name='"+attr+"']");
		return element.elements();
	}
	
	public static void main(String[] args) throws Exception {
		Document document = XmlUtil.getDocument(new File("D:\\menu.xml"), "utf-8");
	/*	List<Element> es = XmlUtil.getChildElementsByNameAttr(document, "工作中心");
		for(Element e : es){
			System.out.println(e.attributeValue("id")+" : " +e.getStringValue());
		}*/
		System.out.println("**********************************************************");
		List<Element> eles = XmlUtil.getElementsByAttribute(document, "id");
		for(Element e : eles){
			System.out.println(e.attributeValue("id")+" : "+e.attributeValue("name"));
		}
	}
}
