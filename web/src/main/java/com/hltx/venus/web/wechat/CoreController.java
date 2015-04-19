package com.hltx.venus.web.wechat;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hltx.venus.web.base.BaseController;
import com.hltx.venus.web.util.Logger;
import com.hltx.venus.web.wechat.util.C;
import com.hltx.venus.web.wechat.util.HttpClientUtil;
import com.hltx.venus.web.wechat.util.MessageUtil;
import com.hltx.venus.web.wechat.util.SHA;

@Controller
public class CoreController extends BaseController {
	private final String TOKEN = "chenchen";
	Logger logger = Logger.getInstance(CoreController.class);
	
	@Autowired
	private MessageController messageController;

	/**
	 * 处理微信发送的Get请求
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */
	@RequestMapping(value = "/core", method = { RequestMethod.GET })
	@ResponseBody
	public String varifySignature(String signature, String timestamp,
			String nonce, String echostr, HttpServletRequest request) {
		if (varifySignature(signature, timestamp, nonce))
			return echostr;
		else
			logger.info(request.getRequestURI()+ "验证异常");
		return ERROR;
	}

	/**
	 * 处理微信转发的POST请求
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @return
	 */
	@RequestMapping(value = "/core", method = { RequestMethod.POST })
	public String execute(String signature, String timestamp, String nonce,
			String echostr, HttpServletRequest request) {
		logger.info("处理post请求");
		if (varifySignature(signature, timestamp, nonce)){
			Map<String, String> message;
			try {
				message = MessageUtil.getReceiveMessage(request.getInputStream());
				logger.info(message.get("msgtype"));
				return handleMessage(message, request);
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		} else {
			logger.info(request.getRequestURI()+ "异常请求");
		}
		return null;
	}

	/**
	 * 
	 * @param message
	 */
	private String handleMessage(Map<String, String> message, HttpServletRequest request) {
		request.setAttribute("message", message);
		if(message.containsKey(C.MessageType.REQ_TYPE_EVENT)) {
			return "forward:/event/listener";
		} else {
			logger.info("转发请求");
			return "forward:/msg/receive";
		}
	}
	
	
	
	/**
	 * 是否来自微信
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	private Boolean varifySignature(String signature, String timestamp,
			String nonce) {
		String token = TOKEN;
		try {
			if (signature != null
					&& signature.equalsIgnoreCase(buildSignature(token,
							timestamp, nonce)))
				return true;
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
			System.out.print(e.getMessage());
		}
		return false;
	}

	/**
	 * 建造signature
	 * 
	 * @param list
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	private String buildSignature(String... datas)
			throws NoSuchAlgorithmException {
		String[] sortedDatas = sortByDictionary(datas);
		StringBuilder builder = new StringBuilder();
		for (String s : sortedDatas) {
			builder.append(s);
		}
		logger.info(builder.toString());
		logger.info(SHA.encrypt(builder.toString()));
		return SHA.encrypt(builder.toString());
	}

	/**
	 * 字典顺序排序
	 * 
	 * @param list
	 * @return
	 */

	private String[] sortByDictionary(String... datas) {
		Arrays.sort(datas);
		return datas;
	}

	/**
	 * 获取AccessToken， 每个调用都要使用此Token
	 * 
	 * @return
	 */
	private String getAccessToken() {
		/*
		 * https://api.weixin.qq.com/cgi-bin/token
		 * ?grant_type=client_credential&appid=APPID&secret=APPSECRET
		 */
		String APPID = "wx8f83f515ed61fc75";
		String APPSECRET = "ed50357a3635fbca93cf7c410e739091";
		String uri = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
				+ APPID + "&secret=" + APPSECRET;
		String res = HttpClientUtil.sendGetRequest(uri, null);
		if (res.contains("access_token"))
			return JSON.parseObject(res).getString("access_token");
		else
			return ERROR;
	}

	private String getWeChatServer(String accessToken) {
		String uri = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token="
				+ accessToken;
		String res = HttpClientUtil.sendGetRequest(uri, null);

		/*
		 * if(!res.contains("errcode")) {
		 * JSON.parseObject(res).getString("ip_list"); }
		 */
		System.out.println(res);
		return null;

	}

	
	public static void main(String[] args) {
		String s = new CoreController().getAccessToken();
		//String accessToken = "yNMichznftbzQH956q9XnGHlBk8WweiBeQ2VpmW2iwuM9GXVP2-"
				//+ "uWRV2ImHlkGgoy0rtiyp4WvA1F5pvljeMuGs4C8l6C29rXuEDAb0G-Jk";
		// String s = new CoreController().getWeChatServer(accessToken);
		 System.out.println(s);
		/*
		 * String ss = "{'ip_list':['101,12','12312.123']}";
		 * JSON.parseObject(ss).getJSONObject("ip_list").get List<String> list =
		 * JSON.parseArray(ss, String.class); for(String s : list) {
		 * System.out.println(s); }
		 */
		/*
		 * {"ip_list":["101.226.62.77","101.226.62.78","101.226.62.79",
		 * "101.226.62.80"
		 * ,"101.226.62.81","101.226.62.82","101.226.62.83","101.226.62.84"
		 * ,"101.226.62.85"
		 * ,"101.226.62.86","101.226.103.59","101.226.103.60","101.226.103.61"
		 * ,"101.226.103.62"
		 * ,"101.226.103.63","101.226.103.69","101.226.103.70","101.226.103.71"
		 * ,"101.226.103.72"
		 * ,"101.226.103.73","140.207.54.73","140.207.54.74","140.207.54.75"
		 * ,"140.207.54.76"
		 * ,"140.207.54.77","140.207.54.78","140.207.54.79","140.207.54.80"
		 * ,"182.254.11.203"
		 * ,"182.254.11.202","182.254.11.201","182.254.11.200","182.254.11.199"
		 * ,"182.254.11.198"]}
		 */
	}

}
