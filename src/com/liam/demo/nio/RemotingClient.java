package com.liam.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class RemotingClient {

    //多路复用器、选择器（具体看使用的操作系统以及jdk版本，1.5有可能就是select而1.7就是epoll）
    private Selector selector;

    private SocketChannel channel;

    public RemotingClient init(String serverIp, int port) throws IOException {

        // 获取socket通道、
        // 在reactor模式中的资源
        // select、epoll函数中的fd（个人理解如有错误请求指正）
        SocketChannel channel = SocketChannel.open();

        // 将该通道设置为非阻塞
        channel.configureBlocking(false);

        // 获取多路复用器实例
        selector = Selector.open();

        // 客户端连接服务器，需要调用channel.finishConnect();才能实际完成连接。
        channel.connect(new InetSocketAddress(serverIp, port));

        // 为该通道注册SelectionKey.OP_CONNECT事件，也就是将channel的fd和感兴趣的事件添加到多路复用器中
        channel.register(selector, SelectionKey.OP_CONNECT);

        return this;
    }


    /**
     * 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理
     *
     * @throws IOException
     */
    public void listen() throws IOException {

        // 轮询访问selector
        while (true) {

            // 当注册的事件到达时，方法返回；否则,该方法会一直阻塞
            selector.select();

            // 获得selector中选中的项的迭代器，选中的项为注册的事件
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            while (it.hasNext()) {

                SelectionKey key = it.next();
                // 删除已选的key,以防重复处理
                it.remove();

                // 连接事件发生
                if (key.isConnectable()) {

                    channel = (SocketChannel) key.channel();

                    // 如果正在连接，则完成连接
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }

                    // 设置非阻塞
                    channel.configureBlocking(false);

                    // 向服务器端发送信息
                    channel.write(ByteBuffer.wrap("hi server".getBytes()));

                    // 在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限。
                    channel.register(selector, SelectionKey.OP_READ);


                } else if (key.isReadable()) {// 获得了可读的事件
                    read(key);
                }
            }
        }
    }

    /**s
     * 处理读取服务器端发来的信息 的事件
     *
     * @param key
     * @throws IOException
     */
    public void read(SelectionKey key) throws IOException {

        // 服务器可读取消息:得到事件发生的Socket通道
        SocketChannel channel = (SocketChannel) key.channel();

        //创建读取的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data);
        System.out.println("get msg from server:" + msg);
    }

    public void sendMsg(String msg) throws IOException{
        if (channel != null){
            channel.write(ByteBuffer.wrap(msg.getBytes()));
        }
    }


    public static void main(String[] args) throws IOException {
        RemotingClient remotingClient = new RemotingClient().init("127.0.0.1", 9981);
        remotingClient.listen();
//        remotingClient.sendMsg("hi , just test");

    }
}
