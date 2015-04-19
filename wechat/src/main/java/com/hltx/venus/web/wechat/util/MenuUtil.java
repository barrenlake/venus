package com.hltx.venus.web.wechat.util;


public class MenuUtil {
	//yNMichznftbzQH956q9XnBsJVdQ5SX-XK1PHKF-dLy0mlkiwF9WxlbMoZlBEXYX53wo6sGZzYZJZGTQkvpjg73-I3FdTp5xrK8IbgsfJw4o
	
	public static String loadMenu(String filename){
		return IOUtil.load(filename);
	}
	
	public static String createMenuRequest(String menu, String access_token) {
		String uri = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
		return HttpClientUtil.sendPostRequest(uri, menu);
	}
	
	/*public static String buildMenu() ｛
		
	｝
	*/
	
	
	public static void main(String[] args) {
		
		//String access_token = "yNMichznftbzQH956q9XnBsJVdQ5SX-XK1PHKF-dLy0mlkiwF9WxlbMoZlBEXYX53wo6sGZzYZJZGTQkvpjg73-I3FdTp5xrK8IbgsfJw4o";
		String access_token = "RNtoxSYTC5ynF7Iuba-74inkQp4tv6mdtFKoEa8N5FaOixB-jtPr1UP-h7stbY6CTaxFazOrmohCM0zW4KUrwCwLXZfDjNy_nSO3DnEUHXw";
		String menu = MenuUtil.loadMenu("file/menu.config");
		String s = createMenuRequest(menu, access_token);
		System.out.println(s);
	}
	
}
