package com.liam.demo.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {

    private int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {

        //create a event loop group
//        EventLoopGroup bossGroup = new NioEventLoopGroup(1, new ThreadFactory() {
//            public Thread newThread(Runnable r) {
//                return new Thread("nettyDemoBoss");
//            }
//        });
//        EventLoopGroup workerGroup = new NioEventLoopGroup(3, new ThreadFactory() {
//            public Thread newThread(Runnable r) {
//                return new Thread("nettyDemoBoss");
//            }
//        });

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();


        //create a serverBootStrap
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);


            //bind and start netty server to accept incoming connections
            ChannelFuture channelFuture = serverBootstrap.bind(this.port).sync();

            //gracefully shut down the server
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            throw new RuntimeException("start netty server fail", e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }

    public static void main(String args[]) throws Exception{

        EchoServer echoServer = new EchoServer(10001);
        echoServer.run();
    }
}
