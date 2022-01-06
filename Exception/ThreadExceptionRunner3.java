package exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author cry777
 * @program demo1
 * @description
 * @create 2022-01-06
 */
public class ThreadExceptionRunner3 {

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
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandle());
//        ExecutorService exec = Executors.newCachedThreadPool(new HandleThreadFactory());
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new ThreadExceptionRunner());
        exec.shutdown();

    }
}
