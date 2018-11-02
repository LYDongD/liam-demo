package com.liam.demo.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDateTime;

public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {
        System.out.println("received msg from client: " +  textWebSocketFrame.text());

        //reply
        channelHandlerContext.channel().writeAndFlush(new TextWebSocketFrame("server time: " + LocalDateTime.now()));
    }

    //connect -> active -> close -> inactive -> removed

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        super.handlerRemoved(ctx);
        System.out.println("socket removed");
    }

    // ChannelHandlerContext的Channel激活时调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("socket actived");
    }

    //Channel处于非活跃状态
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("socket inactived");
    }

    //有异常抛出时会调用。
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("socket exception");
        cause.printStackTrace();
        ctx.close();
    }

}
