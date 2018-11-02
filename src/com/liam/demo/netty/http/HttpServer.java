package com.liam.demo.netty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HttpServer {

    public static void main(String args[]) {

        //recieve request and deliver to worker
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //handle request
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new HttpInitializer());

            //block current thread until bind finished
            ChannelFuture channelFuture = bootstrap.bind(8081).sync();

            //block current thread until channel close
            channelFuture.channel().closeFuture().sync();

        }catch (InterruptedException exception){
            exception.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
