package org.infozech.netty.NettySpring.cfg;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.infozech.netty.NettySpring.handler.NettyChildHandler;
import org.infozech.netty.NettySpring.logger.DataLogWriter;
import org.infozech.netty.NettySpring.model.BytesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;


@Configuration
@ComponentScan("org.infozech.netty")
@PropertySource("netty-server.properties")
public class NettyConfig {

	@Value("${worker.thread.count}")
	private int workerThreadCount;
	
	@Value("${boss.thread.count}")
	private int bossThreadCount;
	
	@Value("${so.keepAlive}")
	private boolean keepAlive;
	
	@Value("${so.backlog}")
	private int backlog;
	
	@Value("${so.receive.buffer.size}")
	private int receiveBuffer;
	
	@Value("${so.send.buffer.size}")
	private int sendBuffer;
	
	@Value("${port1}")
	private int port1;
	
	@Value("${port2}")
	private int port2;
	
	@Value("${port3}")
	private int port3;
	
	@Value("${port4}")
	private int port4;
	
	@Value("${port5}")
	private int port5;
	
	@Value("${port6}")
	private int port6;
	
	@Value("${port7}")
	private int port7;
	
	@Value("${port8}")
	private int port8;
	
	@Value("${port9}")
	private int port9;
	
	@Value("${port10}")
	private int port10;
	
	@Autowired
	@Qualifier("nettyChildHandler")
	private NettyChildHandler nettyChildHandler;
	
	
	@SuppressWarnings("unchecked")
	@Bean(name="serverBootstrap")
	@Scope("singleton")
	public ServerBootstrap bootstrap() throws InterruptedException{
		
		ServerBootstrap b = new ServerBootstrap();
		b.group(bossGroup(), workerGroup())
				 .channel(NioServerSocketChannel.class)
			//	 .handler(nettyLoggingHandler())
				 .childHandler(nettyChildHandler);
		
		Map<ChannelOption<?>, Object> tcpChannelOptions = tcpChannelOptions();
		Set<ChannelOption<?>> keySet = tcpChannelOptions.keySet();
		for(@SuppressWarnings("rawtypes") ChannelOption option:keySet){
			b.option(option, tcpChannelOptions.get(option));
				
		}
		
		return b;
	}
	@Bean(name="workerGroup", destroyMethod="shutdownGracefully")
	public EventLoopGroup workerGroup() {
		EventLoopGroup workerGroup = new NioEventLoopGroup(workerThreadCount);
		return workerGroup;
	}
	
	@Bean(name="bossGroup", destroyMethod="shutdownGracefully")
	public EventLoopGroup bossGroup() {
		
		EventLoopGroup bossGroup = new NioEventLoopGroup(bossThreadCount);
		return bossGroup;
	}
	
	@Bean(name="tcpChannelOption")
	public Map<ChannelOption<?>, Object> tcpChannelOptions() {
		
		Map<ChannelOption<?>, Object> options = new HashMap<ChannelOption<?>, Object>();
		options.put(ChannelOption.SO_KEEPALIVE, keepAlive);
		options.put(ChannelOption.SO_BACKLOG, backlog);
		options.put(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
		options.put(ChannelOption.SO_RCVBUF, receiveBuffer);
		options.put(ChannelOption.SO_SNDBUF, sendBuffer);
		
		return options;
	}
	
	@Bean(name="tcpChannelFutures")
	public List<ChannelFuture> tcpChannelFutures() throws InterruptedException {
		
		List<ChannelFuture> futures = new ArrayList<ChannelFuture>();
		
		futures.add(bootstrap().bind(port1));
		futures.add(bootstrap().bind(port2));
		futures.add(bootstrap().bind(port3));
		futures.add(bootstrap().bind(port4));
		futures.add(bootstrap().bind(port5));
		futures.add(bootstrap().bind(port6));
		futures.add(bootstrap().bind(port7));
		futures.add(bootstrap().bind(port8));
		futures.add(bootstrap().bind(port9));
		futures.add(bootstrap().bind(port10));
		
		return futures;
	}
	
	@Bean(name="loggingHandler")
	public LoggingHandler nettyLoggingHandler(){
		
		return new LoggingHandler(LogLevel.INFO);
	}
	
	@Bean(name="bytesDao")
	public BytesDao getBytesDao(){
		return new BytesDao();
	}
	
	@Bean(name="dataLogWriter")
	public DataLogWriter getDataLogWriter(){
		return new DataLogWriter();
	}
}
