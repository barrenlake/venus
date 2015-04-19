package com.hltx.venus.web.wechat;


import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hltx.venus.web.base.BaseController;
import com.hltx.venus.web.wechat.util.C;

@Controller
@RequestMapping("/event")
public class EventController extends BaseController {
	
	@RequestMapping("/listener")
	public static void listener(Map<String, String> eventMsg) {
		String eventType = eventMsg.get(C.MessageType.REQ_TYPE_EVENT);
		if(eventType.equalsIgnoreCase(C.EventType.EVENT_TYPE_SUBSCRIBE)) {
			if(eventMsg.containsKey(C.MessageInfo.EVENT_TICKET)) {
				//二维码事件--订阅
			} else {
				//普通订阅
			}
		} else if(eventType.equalsIgnoreCase(C.EventType.EVENT_TYPE_SCAN)) {
			//如果用户已经关注公众号，则微信会将带场景值扫描事件推送给开发者
			
		} else if(eventType.equalsIgnoreCase(C.EventType.EVENT_TYPE_UNSUBSCRIBE)) {
			
		} else if(eventType.equalsIgnoreCase(C.EventType.EVENT_TYPE_LOCATION)) {
			/*
			 * 用户同意上报地理位置后，每次进入公众号会话时，
			 * 都会在进入时上报地理位置，或在进入会话后每5秒上报一次地理位置，
			 * 公众号可以在公众平台网站中修改以上设置。
			 * 上报地理位置时，微信会将上报地理位置事件推送到开发者填写的URL
			 */
			
			
		} else if(eventType.equalsIgnoreCase(C.EventType.EVENT_TYPE_CLICK)) {
			//用户点击自定义菜单后，微信会把点击事件推送给开发者，
			//请注意，点击菜单弹出子菜单，不会产生上报。 
		} else if(eventType.equalsIgnoreCase(C.EventType.EVENT_TYPE_VIEW)) {
			//点击菜单跳转链接时的事件推送 
		} 
		
		
	}
	
	public static void handler() {
		
	}
}
