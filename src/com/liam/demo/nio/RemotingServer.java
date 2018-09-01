package com.liam.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class RemotingServer {

    //多路复用器
    private Selector selector;

    public RemotingServer init(int port) throws IOException {

        // 获取一个ServerSocket通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(port));

        // 获取多路复用器对象
        selector = Selector.open();

        // 将通道管理器与通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件，
        // 只有当该事件到达时，Selector.select()会返回，否则一直阻塞。
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        return this;
    }

    public void listen() throws IOException {

        System.out.println("服务器端启动成功");

        // 使用轮询访问selector
        while (true) {

            selector.select();

            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();

            while (ite.hasNext()) {

                SelectionKey key = ite.next();
                ite.remove();

                // 客户端请求连接事件
                if (key.isAcceptable()) {

                    ServerSocketChannel server = (ServerSocketChannel) key.channel();

                    // 获得客户端连接通道
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);

                    // 在与客户端连接成功后，为客户端通道注册SelectionKey.OP_READ事件。
                    channel.register(selector, SelectionKey.OP_READ);

                    System.out.println("accept client connect request");

                } else if (key.isReadable()) {// 有可读数据事件

                    // 获取客户端传输数据可读取消息通道。
                    SocketChannel channel = (SocketChannel) key.channel();

                    // 创建读取数据缓冲器
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    channel.read(buffer);
                    String message = new String(buffer.array());
                    System.out.println("get msg from client: " + message);

                    //echo back
                    ByteBuffer outbuffer = ByteBuffer.wrap(("hi client, received your msg: " + message).getBytes());
                    channel.write(outbuffer);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new RemotingServer().init(9981).listen();
    }



}