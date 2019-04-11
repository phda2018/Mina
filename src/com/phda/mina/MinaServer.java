package com.phda.mina;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSessionConfig;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaServer {
	
	public MinaServer() {
		IoAcceptor  connect = new NioSocketAcceptor();//创建连接对象
		
		IoSessionConfig sessionConfig = connect.getSessionConfig();//获取连接配置对象
		sessionConfig.setReadBufferSize(1024);
		sessionConfig.setIdleTime(IdleStatus.BOTH_IDLE, 10);

		DefaultIoFilterChainBuilder chain = connect.getFilterChain();
	        //设定这个过滤器将以对象为单位读取数据
	    //ProtocolCodecFilter filter= new ProtocolCodecFilter(new ObjectSerializationCodecFactory());
	    ProtocolCodecFilter filter= new ProtocolCodecFilter(new TextLineCodecFactory());
	    chain.addLast("objectFilter",filter);
	    
		connect.setHandler(new ServerHandler());//创建handler 对象进行逻辑处理
		try {
			connect.bind(new InetSocketAddress(8089));
			IoServiceListener ioServiceListener;
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		MinaServer server = new MinaServer();
		System.out.println("hello,start programe");
		
		
		
		
		
	}
	
	
}
