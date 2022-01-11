package juc;

import java.util.concurrent.Semaphore;

/**
 * @author cry777
 * @program demo1
 * @description
 * @create 2022-01-11
 */
public class SemaphoreDemo {

    int count;
    final Semaphore semaphore   = new Semaphore(1); // 初始化信号量
    // 用信号量保证互斥
    void addOne() {
        try {
            semaphore.acquire();   //对应down，计数减一
            count+=1;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();  //对应up，计数加一
        }
    }
}
