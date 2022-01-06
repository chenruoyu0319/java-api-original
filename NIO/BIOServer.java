package io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author cry777
 * @program demo1
 * @description
 * @create 2022-01-06
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {
        //创建线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //创建serverSocket
        ServerSocket serverSocket = new ServerSocket(6666);
        for (; ; ) {
            System.out.println("等待连接中...");
            //监听,等待客户端连接
            Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");
            executorService.execute(() -> handler(socket));
        }
    }

    //编写一个handler方法,和客户端通讯
    public static void handler(Socket socket) {
        byte[] bytes = new byte[1024];
        System.out.println("当前线程信息: " + Thread.currentThread().getName());
        try {
            //通过socket获取输入流
            InputStream inputStream = socket.getInputStream();
            //循环读取客户端发送的数据
            while (inputStream.read(bytes) != -1) {
                System.out.println(Thread.currentThread().getName() + " : 发送信息为 :" + new String(bytes, 0, bytes.length));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
