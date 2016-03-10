package com.gxy.multithreading;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者和消费者
 *
 * 假设使用缓冲区存储整数。
 * 缓冲区的大小是受限的。
 * 缓冲区提供write(int)方法将一个int值添加到缓冲区中，还提供read()从缓冲区中读取和删除一个int值。
 * 为了同步这个操作，使用具有两个条件的锁：notEmpty(即缓冲区非空)和notFull(即缓冲区未满)。
 * 当任务向缓冲区添加一个int时，如果缓冲区是空的，那么任务将等待notEmpty状态。
 *
 * 缓冲区实际上是一个先进先出的队列。
 * 状态和锁绑在一起。
 * 在应用一个状态之前必须获取一个锁。
 *
 * Created by gxy on 2016/3/8.
 */
public class ConsumerProducer {
    private static Buffer buffer = new Buffer();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.execute(new ProducerTask());
        executor.execute(new ConsumerTask());
        executor.shutdown();
    }

    private static class ProducerTask implements Runnable {
        @Override
        public void run() {
            try {
                int i = 1;
                while (true) {
                    System.out.println("Producer writes " + i);
                    buffer.write(i++);

                    Thread.sleep((int)(Math.random() * 10000));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static class ConsumerTask implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    System.out.println("\t\tConsumer reads " + buffer.read());

                    Thread.sleep((int)(Math.random() * 10000));
                }
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static class Buffer {
        private static final int CAPACITY = 1; //buffer size
        private LinkedList<Integer> queue = new LinkedList<>();

        private static Lock lock = new ReentrantLock();

        private static Condition notEmpty = lock.newCondition();
        private static Condition notFull = lock.newCondition();

        public void write(int value) {
            lock.lock();
            try{
                while(queue.size() == CAPACITY) {
                    System.out.println("Wait for notFull condition");
                    notFull.await();
                }
                
                queue.offer(value);
                notEmpty.signal();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public int read() {
            int value = 0;
            lock.lock();
            try{
                while (queue.isEmpty()) {
                    System.out.println("\t\tWait for notEmpty condition");
                    notEmpty.await();
                }

                value = queue.remove();
                notFull.signal();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
                return value;
            }
        }
    }
}
