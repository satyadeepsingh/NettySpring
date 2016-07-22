package org.infozech.netty.NettySpring.logger;

import org.apache.log4j.Logger;
import org.infozech.netty.NettySpring.model.BytesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("dataLogWriter")
public class DataLogWriter {
	
	@Autowired
	@Qualifier("bytesDao")
	BytesDao bytesDao;
	
	private static final Logger logger = Logger.getLogger(DataLogWriter.class);
	public void logWriter(){
		logger.info("Data from "+ bytesDao.getSocketAddress()+ " client: "+" *******  " + "Data: " 
				+ bytesDao.getByteList() );
		}

}
