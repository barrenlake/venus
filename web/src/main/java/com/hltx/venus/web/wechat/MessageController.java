package com.hltx.venus.web.wechat;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hltx.venus.web.base.BaseController;
import com.hltx.venus.web.util.Logger;

@Controller
@RequestMapping("/msg")
public class MessageController extends BaseController {
	
	private Logger logger = Logger.getInstance(MessageController.class);
	
/*	@Autowired
	private TextService textService;
	
	@RequestMapping("/receive")
	@ResponseBody
	public String handler(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		Map<String, String> mapMsg = (Map<String, String>) request.getAttribute("message");
		logger.info("处理文本消息");
		TextMessage message =  extractTextMessage(mapMsg);
		if(mapMsg.get("msgtype").equalsIgnoreCase(C.MessageType.REQ_TYPE_TEXT)) {
			handleTextMsg(message);
			logger.info("响应：开始");
			return responseText(message);
		}
		return responseText(message);
	}
	
	
	private String responseText(TextMessage receiveMessage) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("fromUser", receiveMessage.getToUserName());
		map.put("toUser", receiveMessage.getFromUserName());
		map.put("createTime", receiveMessage.getCreateTime());
		map.put("content", receiveMessage.getContent());
		map.put("msgtype", receiveMessage.getMsgType());
		String msg = MessageUtil.bulidTextResponseMessage(map);
		logger.info(msg);
		return msg;
	}
	
	
	
	private void handleTextMsg(TextMessage message){
		textService.save(message);
		
		logger.info("处理文本消息---保存");
		System.out.println("处理文本消息");
	}

	
	private TextMessage extractTextMessage(Map<String, String> message) {
		TextMessage msg = new TextMessage();
		msg.setFromUserName(message.get(MessageInfo.FROMUSERNAME));
		msg.setToUserName(message.get(MessageInfo.TOUSERNAME));
		msg.setMsgType(message.get(MessageInfo.MSGTYPE));
		msg.setMsgId(Long.parseLong(message.get(MessageInfo.MSGID)));
		msg.setCreateTime(Long.parseLong(message.get(MessageInfo.CREATETIME)));
		msg.setContent(message.get(MessageInfo.TEXT_CONTENT));
		return msg;
	}
	*/
	
}
