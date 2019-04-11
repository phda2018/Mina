package com.phda.mina;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
/**
 * 服务层hanlder
 * 进行业务逻辑处理
 * @author Administrator
 *
 */
public class ServerHandler extends IoHandlerAdapter {

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(session+"服务层handler创建");
		
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		String msg = message.toString();
		System.out.println("服务层接收到消息："+msg);
		if("over".equals(msg)) {
//			session.closeNow();//断开连接
			session.closeOnFlush();//断开连接
		}else
			session.write(msg);
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
