package com.phda.controller;

import java.util.HashMap;
import java.util.Map;

import com.phda.mina.User;
import com.phda.transport.message.Message;
import com.phda.transport.message.response.ErrorRes;
import com.phda.transport.message.response.LoginRes;
import com.phda.transport.message.response.ResponseBody;
/**
 * 用户登录处理，返回token
 * @author Administrator
 *
 */
public class LoginController {
	
	private static Map<String,User> usersMap = new HashMap<String, User>();//存储系统下的用户
	private static Map<String,String> userToken = new HashMap<String, String>();//存储用户名与token
	
	static {
		usersMap.put("phda", new User("phda", "123"));
		usersMap.put("hujb", new User("hujb", "123"));
		usersMap.put("wpp", new User("wpp", "123"));
	}
	
	/**
	 * 用户登录
	 * code 1001
	 */
	public static ResponseBody login(String name,String pwd) {
		
		LoginRes loginRes = new LoginRes(null);
		
		if( usersMap.containsKey(name) ) {//如果用户存在
			String tempPwd = usersMap.get(name).getPwd();
			if(tempPwd.equals(pwd)) {//密码相同
				
				loginRes.setToken("");
				userToken.put(name, "");
				
			}else {//密码错误
				return new ErrorRes("1001", null, "密码错误");
			}
		}else {//不存在
			return new ErrorRes("1001", null, "用户名错误");
		}
		return loginRes;
	}
	
	/**
	 * 判断传输的token 是否正确
	 * @param name
	 * @param token
	 * @return
	 */
	public static boolean isExistToken(String name,String token) {
		String tempToken = userToken.get(name);
		if(tempToken == null)
			return false;
		if(tempToken.equals(token))
			return true;
		return false;
	}
	
	
	
	
	
	
	
}
