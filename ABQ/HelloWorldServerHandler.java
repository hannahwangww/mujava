package com.company.first;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by weicong on 17-8-3.
 */
public class HelloWorldServerHandler extends ChannelInboundHandlerAdapter{
    public void channelRead(ChannelHandlerContext ctx,Object msg){//ctx是链接信息，msg是客户端发来的信息
        System.out.println("111111111");//在服务器的信息111111111
        System.out.println(ctx.channel().remoteAddress()+"->server"+msg.toString());//获取ip：/127.0.0.1:42750->server123
        ctx.write("11111");//往客户段回的信息
        ctx.flush();
    }

//    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
//
//    }

    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }

}
