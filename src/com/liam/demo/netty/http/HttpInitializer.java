package com.liam.demo.netty.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast("httpServerCodec", new HttpServerCodec()) //httpServerCodec contains httpCoder and httpDecoder
                .addLast("aggregator", new HttpObjectAggregator(65536))
                .addLast("httpServerHandler", new HttpServerChannelHandler()); //  server logic entrance
    }
}
