package com.liam.demo.netty.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class HttpClientHandler extends SimpleChannelInboundHandler<FullHttpResponse> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        String msg = "are you ok?";
        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1,
                HttpMethod.GET,
                new URI("http://127.0.0.1:8081").toASCIIString(),
                Unpooled.wrappedBuffer(msg.getBytes("UTF-8")));

        request.headers().set("Content-Length", request.content().readableBytes());

        ctx.writeAndFlush(request);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpResponse fullHttpResponse) throws Exception {
        System.out.println("http clinet recieved msg: " + fullHttpResponse.content().toString(CharsetUtil.UTF_8));
    }
}
