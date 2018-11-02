package com.liam.demo.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {


    private String host;

    private int port;


    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() throws Exception {

        //init event loop group
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //init bootstap
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });

            //start the netty client
            ChannelFuture channelFuture = bootstrap.connect(this.host, this.port).sync();

            //wait until the connection is closed
            channelFuture.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String args[]) throws Exception{

        EchoClient echoClient = new EchoClient("127.0.0.1", 10001);
        echoClient.connect();

    }
}
