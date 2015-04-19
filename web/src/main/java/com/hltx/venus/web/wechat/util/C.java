package com.hltx.venus.web.wechat.util;

public abstract class C {
	public static class WeChatSystem{
	}
	
	public static class MessageType{
		public static final String REQ_TYPE_TEXT = "text";
		public static final String REQ_TYPE_IMAGE = "image";
		public static final String REQ_TYPE_VOICE = "voice";
		public static final String REQ_TYPE_VIDEO = "vodeo";
		public static final String REQ_TYPE_LOCATION = "location";
		public static final String REQ_TYPE_LINK = "link";
		
		public static final String REQ_TYPE_EVENT = "event";
		
		public static final String RESP_TYPE_TEXT = "text";
		public static final String RESP_TYPE_IMAGE = "image";
		public static final String RESP_TYPE_VOICE = "voice";
		public static final String RESP_TYPE_VIDEO = "video";
		public static final String RESP_TYPE_MUSIC = "music";
		public static final String RESP_TYPE_NEWS = "news";
	}
	
	public static class EventType{
		public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
		public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
		public static final String EVENT_TYPE_SCAN = "scan";
		public static final String EVENT_TYPE_LOCATION = "location";
		public static final String EVENT_TYPE_CLICK = "click";
		public static final String EVENT_TYPE_VIEW = "view";
		
	}
	
	
	public static class CharSet{
		public static final String DEFAULT_ENCODING = "utf-8";
	}
	
	
	public static class MessageInfo{
		public static final String TOUSERNAME = "tousername";
		public static final String FROMUSERNAME = "fromusername";
		public static final String CREATETIME = "createtime";
		public static final String MSGTYPE = "msgtype";
		public static final String MSGID = "msgid";
		
		
		public static final String TEXT_CONTENT = "content";
		
		public static final String EVENT_EVENTKEY = "eventkey";
		public static final String EVENT_TICKET = "ticket";
		public static final String EVENT_LATITUDE = "latitude";
		public static final String EVENT_LONGITUDE ="longitude";
		public static final String EVENT_PRECISION = "precision";
		
		
		
 
	}
}
