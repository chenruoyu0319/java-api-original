package io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author cry777
 * @program demo1
 * @description
 * @create 2022-01-06
 */
public class NIOServer {

    public static void main(String[] args) throws IOException {
        //创建serverSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //得到Selector对象
        try (Selector selector = Selector.open()) {
            //把ServerSocketChannel注册到selector，事件为OP_ACCEPT
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            //如果返回的>0，表示已经获取到关注的事件
            while (selector.select() > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    //获得到一个事件
                    SelectionKey next = iterator.next();
                    //如果是OP_ACCEPT，表示有新的客户端连接
                    if (next.isAcceptable()) {
                        //给该客户端生成一个SocketChannel
                        SocketChannel accept = serverSocketChannel.accept();
                        accept.configureBlocking(false);
                        //将当前的socketChannel注册到selector，关注事件为读事件，同时给socket Channel关联一个buffer
                        accept.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                        System.out.println("获取到一个客户端连接");
                        //如果是读事件
                    } else if (next.isReadable()) {
                        //通过key 反向获取到对应的channel
                        SocketChannel channel = (SocketChannel) next.channel();
                        //获取到该channel关联的buffer
                        ByteBuffer buffer = (ByteBuffer) next.attachment();
                        while (channel.read(buffer) != -1) {
                            buffer.flip();
                            System.out.println(new String(buffer.array(), 0, buffer.limit()));
                            buffer.clear();
                        }
                    }
                    iterator.remove();
                }
            }
        }
    }
}

class Client {

    public static void main(String[] args) throws IOException {
        //得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置为非阻塞
        socketChannel.configureBlocking(false);
        //提供服务器端的IP和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        //连接服务器
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("连接需要时间,客户端不会阻塞...先去吃个宵夜");
            }
        }
        //连接成功,发送数据
        String str = "hello,Java菜鸟程序员";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        socketChannel.write(byteBuffer);
        socketChannel.close();
        System.out.println("客户端退出");
    }

}
