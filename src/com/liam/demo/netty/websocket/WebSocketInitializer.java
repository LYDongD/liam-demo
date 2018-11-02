package com.liam.demo.netty.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;



public class WebSocketInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        channel.pipeline().addLast(new HttpServerCodec())
            .addLast(new ChunkedWriteHandler()) //support async big file
            .addLast(new HttpObjectAggregator(65536)) // convert to fullHttpRequest
            .addLast(new WebSocketServerProtocolHandler("/ws")) //do some websocket init logic, hand shake, other controls
            .addLast(new TextWebSocketFrameHandler()); //just handle text msg

    }
}
