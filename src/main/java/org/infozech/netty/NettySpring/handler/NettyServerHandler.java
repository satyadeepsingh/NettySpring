package org.infozech.netty.NettySpring.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.infozech.netty.NettySpring.logger.DataLogWriter;
import org.infozech.netty.NettySpring.model.BytesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


@Service
@Qualifier("serverHandler")
@Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
	
	@Autowired
	@Qualifier("bytesDao")
	BytesDao bytesDao;
	
	@Autowired
	@Qualifier("dataLogWriter")
	DataLogWriter dataLogWriter;

	private List<String> bytelist; 
	private StringBuilder sb;
	
	private static final Logger logger = Logger.getLogger(NettyServerHandler.class);
	
	public void handlerAdded(ChannelHandlerContext arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void handlerRemoved(ChannelHandlerContext arg0) throws Exception {
		
		//dataLogWriter.logWriter();
	}

	public void channelActive(ChannelHandlerContext arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void channelInactive(ChannelHandlerContext arg0) throws Exception {
		dataLogWriter.logWriter();
	}

	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		
		bytelist = Collections.synchronizedList(new ArrayList<String>());
		ByteBuf in = Unpooled.copiedBuffer((ByteBuf) msg);
		
		byte[] b = new byte[in.capacity()];
		sb = new StringBuilder();
		
		
		for (int i = 0; i < in.capacity(); i ++) {
		      b[i] = in.getByte(i);
		  //    sb.append(String.format("%02x", b[i]));
		      System.out.printf(String.format("%02x", b[i]));
		      bytelist.add(String.format("%02x", b[i]));
		 //  logger.info(b[i]);
		   
		 }
		
		bytesDao.setByteList(bytelist); 
		
	}

	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		
		Iterator itr = bytesDao.getByteList().iterator();
		
		while(itr.hasNext()) {
			System.out.print(itr.next());
		}
		System.out.println(bytesDao.getByteList().size());
		//System.out.println("\n");
		//ctx.close();
	}

	public void channelRegistered(ChannelHandlerContext arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		
	//	ctx.close();

	}

	public void channelWritabilityChanged(ChannelHandlerContext arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		logger.error(cause.getMessage());

	}


}
