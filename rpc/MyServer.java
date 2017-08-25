package org.rpc;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.RPC.Server;

class Biz implements Bizable{
	@Override
	public String hello(String name){
		System.out.println("被调用了");
		return "hello "+name;
	}

	@Override
	public long getProtocolVersion(String protocol, long clientVersion)
			throws IOException {
		System.out.println("Biz.getProtocalVersion()="+MyServer.VERSION);
		return MyServer.VERSION;
	}
}

public class MyServer {
	public static int PORT = 3242;
	public static long VERSION = 23234l;
	
	public static void main(String[] args) throws IOException {
		final Server server = RPC.getServer(new Biz(), "127.0.0.1", PORT, new Configuration());
		server.start();
	}

}
