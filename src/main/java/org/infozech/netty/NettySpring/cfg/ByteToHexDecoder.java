package org.infozech.netty.NettySpring.cfg;

import java.util.List;

import org.springframework.stereotype.Service;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

@Service
public class ByteToHexDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		
		if(in.readableBytes() < 229)
			return ; 
		out.add(in.readBytes(229));

	}
	
	
}
