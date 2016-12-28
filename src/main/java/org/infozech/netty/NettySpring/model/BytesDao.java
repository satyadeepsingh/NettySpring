package org.infozech.netty.NettySpring.model;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("bytesDao")
public class BytesDao {

	private InetSocketAddress SocketAddress = null;
	
	private List<String> byteList = Collections.synchronizedList(new ArrayList<String>());

	public InetSocketAddress getSocketAddress() {
		return SocketAddress;
	}

	public void setSocketAddress(InetSocketAddress socketAddress) {
		SocketAddress = socketAddress;
	}

	public List<String> getByteList() {
		return byteList;
	}

	public void setByteList(List<String> byteList) {
		this.byteList = byteList;
	}
	
	
	
	
}
