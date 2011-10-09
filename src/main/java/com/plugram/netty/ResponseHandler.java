package com.plugram.netty;


import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;

public class ResponseHandler extends SimpleChannelUpstreamHandler {

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		// リクエストを受け取るときは以下の行のコメントを外す
		// HttpRequest request = (HttpRequest) e.getMessage();
		
		String message = "Hello Netty!!";
		
		HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
		// HTTP HeaderのCONTENT-LENGTHを設定する
		HttpHeaders.setContentLength(response, message.length());
		
		// チャンネルを取得しmessageを書き込む。
		// Nettyは非同期で処理されるので
		ChannelFuture future = e.getChannel().write(message);
		
		// 送信が終わればコネクションを閉じる
		future.addListener(ChannelFutureListener.CLOSE);
	}
}
