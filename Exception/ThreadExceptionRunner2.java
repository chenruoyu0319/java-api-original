package exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author cry777
 * @program demo1
 * @description
 * @create 2022-01-06
 */
public class ThreadExceptionRunner2 {

    public static void main(String[] args) {
        try {
            Thread thread = new Thread(new ThreadExceptionRunner());
            thread.start();
        } catch (Exception e) {
            System.out.println("========");
            e.printStackTrace();
        } finally {

        }
        System.out.println(123);
        ExecutorService exec = Executors.newCachedThreadPool(new HandleThreadFactory());
        exec.execute(new ThreadExceptionRunner());
        exec.shutdown();
    }
}


class MyUncaughtExceptionHandle implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}

class HandleThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        System.out.println("create thread t");
        Thread t = new Thread(r);
        System.out.println("set uncaughtException for t");
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandle());
        return t;
    }

}
