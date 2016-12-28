package org.infozech.netty.NettySpring.server;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;

@Service
@PropertySource("netty-server.properties")
public class TcpServer {
	
	@Autowired
	@Qualifier("serverBootstrap")
	private ServerBootstrap b;
	
	//This was originally coded in spring 4, below autowiring is  Not Valid in Spring 3 , So changing to @Resource  
	/*@Autowired
	@Qualifier("tcpChannelFutures")*/
	@Resource(name = "tcpChannelFutures")
	private List<ChannelFuture> futures;
		
	public ServerBootstrap getB() {
		return b;
	}

	public void setB(ServerBootstrap b) {
		this.b = b;
	}

	public List<ChannelFuture> getFutures() {
		return futures;
	}
	
	public void setFutures(List<ChannelFuture> futures) {
		this.futures = futures;
	}

	@PostConstruct
	public void start() {
		try{
		
			for(ChannelFuture f : futures){
				f.sync();
			}
		}
		catch (Exception e) {
			e.getMessage();
		}
		
	}
	
	@PreDestroy
	public void stop(){
		try{
			for(ChannelFuture f:futures){
				f.sync().channel().closeFuture();
			}
		}
		catch(Exception e){
			e.getMessage();
		}
	}
}
