package notify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static org.apache.curator.shaded.com.google.common.base.Preconditions.checkNotNull;

/**
 * @author cry777
 * @program demo1
 * @description
 * @create 2022-01-18
 */
public class ArrayBlockingQueue<E> {

    ReentrantLock lock;

    Condition notEmpty;

    Condition notFull;

    Object[] items;

    int count;

    int putIndex;

    /**
     * ArrayBlockingQueue构造函数
     *
     * @param capacity
     * @param fair
     * @return
     */
    public ArrayBlockingQueue(int capacity, boolean fair) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.items = new Object[capacity];
        lock = new ReentrantLock(fair);
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }
    
    /**
     * 出队
     * @param
     * @return E
     */
    public E take() throws InterruptedException {
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0) { // 队列为空时, 阻塞当前消费者
                notEmpty.await();
            }
            // 队列没满, 通知生产者生产元素
            return dequeue();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 入队
     * @param e
     * @return void
     */
    public void put(E e) throws InterruptedException {
        checkNotNull(e);
        final ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == items.length) {
                notFull.await();
            }
            // 队列没满, 通知消费者获取元素
            enqueue(e);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 通知消费元素
     * @param x
     * @return void
     */
    private void enqueue(E x) {
        final Object[] items = this.items;
        items[putIndex] = x;
        if (++putIndex == items.length) {
            putIndex = 0;
        }
        count++;
        notEmpty.signal(); // 队列没满时，通知消费者获取元素
    }

    /**
     * 通知生产元素
     * @param x
     * @return void
     */
    private void dequeue(E x) {
        // ...
        notEmpty.signal(); // 队列没满时，通知生产者生产元素
    }
}
