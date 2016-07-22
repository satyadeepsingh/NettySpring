package org.infozech.netty.NettySpring.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.infozech.netty.NettySpring.logger.DataLogWriter;
import org.infozech.netty.NettySpring.model.BytesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;


@Service
@Qualifier("serverHandler")
@Sharable
public class NettyServerHandler implements ChannelInboundHandler {
	
	@Autowired
	@Qualifier("bytesDao")
	BytesDao bytesDao;
	
	@Autowired
	@Qualifier("dataLogWriter")
	DataLogWriter dataLogWriter;

	List<Byte> bytelist = new ArrayList<Byte>();
	
	private static final Logger logger = Logger.getLogger(NettyServerHandler.class);
	
	public void handlerAdded(ChannelHandlerContext arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	public void handlerRemoved(ChannelHandlerContext arg0) throws Exception {
		

	}

	public void channelActive(ChannelHandlerContext arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	public void channelInactive(ChannelHandlerContext arg0) throws Exception {
		dataLogWriter.logWriter();
	}

	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf in = Unpooled.copiedBuffer((ByteBuf) msg);
		
		byte[] b = new byte[in.capacity()];
		
		
		
		for (int i = 0; i < in.capacity(); i ++) {
		      b[i] = in.getByte(i);
		      System.out.printf(String.format("%02x", b[i]));
		    //  logger.info(b[i]);
		      bytelist.add(b[i]);
		 }
		
		bytesDao.setByteList(bytelist); 
		 /*
		 List<Byte> newbytelist = bytesdao.getBytelist();
		 for(byte bytes:newbytelist){
			 System.out.println(String.format("%02x", bytes));
		 }*/
		// test.get();
         ctx.write(in);
	}

	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		
		//ctx.close();

	}

	public void channelRegistered(ChannelHandlerContext arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	public void channelUnregistered(ChannelHandlerContext arg0) throws Exception {
		

	}

	public void channelWritabilityChanged(ChannelHandlerContext arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		logger.info(cause.getMessage());

	}

	public void userEventTriggered(ChannelHandlerContext arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub

	}

}
