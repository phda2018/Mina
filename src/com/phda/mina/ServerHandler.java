package com.phda.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.phda.controller.LoginController;
import com.phda.transport.message.Message;
import com.phda.transport.message.request.LoginReq;
import com.phda.transport.message.request.RequestBody;
import com.phda.transport.message.response.ResponseBody;
/**
 * 服务层hanlder
 * 进行业务逻辑处理
 * @author Administrator
 *
 */
public class ServerHandler extends IoHandlerAdapter {
	private Logger logger = LoggerFactory.getLogger(ServerHandler.class);
	
	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println(session+"服务层handler创建");
	}

	/*
	 * 对象序列化传输、字符序列化传输
	 */
	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		
		RequestBody req = (RequestBody)message;
		logger.info("请求参数:[{}]", JSONObject.toJSONString(message));
		String transCode = req.getTransCode();
		//关于transCode 的判断
		ResponseBody resBody = null;
		switch (transCode) {
		case "1001"://登录操作
			LoginReq loginReq = (LoginReq)req;
			resBody = LoginController.login(loginReq.getName(), loginReq.getPwd());
			break;
		case "2001"://获取系统时间
			
			break;
		default:
			break;
		}
		
		session.write(resBody);//响应
	}
	

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("服务端发送消息:"+message.toString());
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.err.println("链接断开了");
	}
	
}
