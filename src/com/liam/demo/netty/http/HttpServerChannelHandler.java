package com.liam.demo.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class HttpServerChannelHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private HttpRequest request;

//    @Override
//    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject msg) throws Exception {
//
//        //netty divide http request to head part which is HttpRequest and body part which is HttpContent
//
//        if (msg instanceof HttpRequest){
//            request = (HttpRequest)msg;
//            System.out.println("uri: " + request.getUri());
//        }
//
//        if (msg instanceof HttpContent){
//            HttpContent httpContent = (HttpContent)msg;
//
//            //read
//            ByteBuf readBuf = httpContent.content();
//            System.out.println(readBuf.toString(CharsetUtil.UTF_8));
//
//            //write
//            ByteBuf writeBuf = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
//            FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, writeBuf);
//            httpResponse.headers().add("Content-Type", "text/plain");
//            httpResponse.headers().add("Content-Length", writeBuf.readableBytes());
//
//
//            channelHandlerContext.writeAndFlush(httpResponse);
//
//        }
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {

        //read info
        System.out.println("remote-address: " + channelHandlerContext.channel().remoteAddress());
        System.out.println("request-methid: " + fullHttpRequest.getMethod().name());
        System.out.println("request-uri: "+ fullHttpRequest.getUri());
        System.out.println("request-content: " + fullHttpRequest.content().toString(CharsetUtil.UTF_8));


        //write
        ByteBuf writeBuf = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
        FullHttpResponse httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, writeBuf);
        httpResponse.headers().add("Content-Type", "text/plain");
        httpResponse.headers().add("Content-Length", writeBuf.readableBytes());
        channelHandlerContext.writeAndFlush(httpResponse);
    }
}
