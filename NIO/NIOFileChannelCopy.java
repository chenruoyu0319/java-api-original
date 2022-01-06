package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author cry777
 * @program demo1
 * @description
 * @create 2022-01-06
 */
public class NIOFileChannelCopy {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("hello.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("world.txt");
        FileChannel inChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1);
        while (inChannel.read(byteBuffer) != -1) {
            //byteBuffer切换读模式
            byteBuffer.flip();
            // 写入channel
            outChannel.write(byteBuffer);
            //清空重置
            byteBuffer.clear();
        }
        fileOutputStream.close();
        fileInputStream.close();
    }
}
