package com.hltx.venus.web.wechat.util;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;

public abstract class MessageUtil {

	/*
	 * public static Map<String, String> getMessage(HttpServletRequest request)
	 * { try { return getMessage(request.getInputStream()); } catch (IOException
	 * e) {
	 * 
	 * } return null; }
	 */

	public static Map<String, String> getReceiveMessage(InputStream is) {
		Map<String, String> message = null;
		try {
			Document doc = XmlUtil.getDocument(is, C.CharSet.DEFAULT_ENCODING);
			@SuppressWarnings("unchecked")
			List<Element> elements = doc.getRootElement().elements();
			message = new HashMap<String, String>();
			for (Element e : elements) {
				message.put(e.getName().toLowerCase(), e.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	public static String bulidTextResponseMessage(Map<String, Object> message) {
		String template = getMessageTemplate(message.get(C.MessageInfo.MSGTYPE).toString());
		try {
			return new String(DocumentHandler.getInstance("file").process(template, message).toByteArray(), C.CharSet.DEFAULT_ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/*public static String buildResponseMessage(BaseMessage message) {
		String template = getMessageTemplate(message.getMsgType());
		try {
			return new String(DocumentHandler.getInstance("file").process(template, message).toByteArray(), C.CharSet.DEFAULT_ENCODING);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ""
	}*/
	
	private static String getMessageTemplate(String msgType) {
		String template = null;
		if (msgType.equalsIgnoreCase(C.MessageType.RESP_TYPE_TEXT)) {
			template = "text_message.ftl";
		}
		return template;
		
	}

}
