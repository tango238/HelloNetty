package com.plugram.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Bootstrapの生成
		ServerBootstrap bootstrap = new ServerBootstrap(
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(),
						Executors.newCachedThreadPool()));
		// Pipelineを設定
		// Handlerの定義はServerPipelineFactoryで定義
		bootstrap.setPipelineFactory(new ServerPipelineFactory());
		// ポート番号(9999)を使用して起動
		bootstrap.bind(new InetSocketAddress(9999));
		
		System.out.println("HelloNetty is started...");
	}
}
