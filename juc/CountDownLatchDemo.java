package juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author cry777
 * @program demo1
 * @description
 * @create 2022-01-11
 */
public class CountDownLatchDemo {

    static CountDownLatch countDownLatch;

    public static void main(String[] args) throws InterruptedException {
        countDownLatch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                try {
                    Thread.sleep((long) (Math.random() * 3000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ", 到餐厅了");
                countDownLatch.countDown();
            },"水友" + i + "号").start();
        }
        countDownLatch.await();
        System.out.println("人都到齐了");
    }


}
