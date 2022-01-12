package blockingQueue;

import org.openjdk.jol.info.ClassLayout;

import java.util.stream.IntStream;

/**
 * @author cry777
 * @program demo1
 * @description
 * @create 2022-01-12
 */
public class YesLockTest {

    static Object yesLock;

    public static void main(String[] args) throws InterruptedException {
        yesLock = new Object();
        System.out.println("无锁时对象布局：" + ClassLayout.parseInstance(yesLock).toPrintable());
        IntStream.rangeClosed(1,4).forEach(i->{getYesLock();});
        Thread.sleep(5000L);
        System.out.println("无竞争之后，此时的对象布局：" + ClassLayout.parseInstance(yesLock).toPrintable());
        getYesLock();//此时再来一次加锁
    }


    private static void getYesLock() {
        new Thread(() -> {
            try {
                synchronized (yesLock) {
                    System.out.println("线程[" + Thread.currentThread().getName() + "]" +
                            ":重量级锁状态对象布局:" + ClassLayout.parseInstance(yesLock).toPrintable());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

}

