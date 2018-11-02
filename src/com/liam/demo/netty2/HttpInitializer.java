package com.liam.demo.netty2;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpInitializer extends ChannelInitializer {

    @Override
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline channelPipeline = channel.pipeline();
        channelPipeline.addLast("httpServerCodec", new HttpServerCodec()) //httpServerCodec contains httpCoder and httpDecoder
                .addLast("aggregator", new HttpObjectAggregator(65536))
                .addLast("httpServerHandler", new HttpServerChannelHandler()); //  server logic entrance
    }
}
