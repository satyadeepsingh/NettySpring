package org.infozech.netty.NettySpring.handler;

import org.apache.log4j.Logger;
import org.infozech.netty.NettySpring.model.BytesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

@Service
@Qualifier("nettyChildHandler")
public class NettyChildHandler extends ChannelInitializer<SocketChannel> {

	private static final Logger logger = Logger.getLogger(NettyChildHandler.class);

	@Autowired
	NettyServerHandler nettyServerHandler;
	
	@Autowired
	@Qualifier("bytesDao")
	BytesDao bytesDao;
	
	public NettyServerHandler getNettyServerHandler() {
		return nettyServerHandler;
	}

	public void setNettyServerHandler(NettyServerHandler nettyServerHandler) {
		this.nettyServerHandler = nettyServerHandler;
	}

	
	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		
		ChannelPipeline p = ch.pipeline();
		logger.info("*************************************************************");
		System.out.println("New Client connected:" + ch.localAddress());
		bytesDao.setSocketAddress(ch.localAddress());
		logger.info("Connected to client" + ch.localAddress());
		p.addLast(nettyServerHandler);
		
	}

}
