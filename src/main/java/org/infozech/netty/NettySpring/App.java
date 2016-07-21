package org.infozech.netty.NettySpring;

import org.infozech.netty.NettySpring.cfg.NettyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
    public static void main( String[] args )
    {
       logger.debug("Starting application context");
       System.out.println("Starting application context");
       
       AbstractApplicationContext context = new AnnotationConfigApplicationContext(NettyConfig.class);
       
       context.registerShutdownHook();
    }
}
