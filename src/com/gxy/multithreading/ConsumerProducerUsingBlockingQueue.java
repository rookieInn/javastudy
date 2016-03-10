package com.gxy.multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 阻塞队列试图向一个满队列添加元素或者从空队列中删除元素时会导致线程阻塞。
 * 
 * BlockingQueue接口扩展java.util.Queue,并且提供同步的put和take方法向队列头添加元素，以及从队列尾删除元素。
 * +put(element:E):void     在队尾插入一个元素。如果队列已满则等待。
 * +take():E                返回并删除这个队列的头。如果队列为空则等待。
 * ArrayBlockingQueue使用数组实现阻塞队列。必须指定一个容量或者可选的公平性来构造ArrayBlockingQueue。
 * LinkedBlockingQueue使用链表实现阻塞队列。可以创建受制或不受限的阻塞队列。
 * PriorityBlockingQueue是优先队列。可以创建不受限的或受限的优先队列。
 *
 * 对于不受限队列而言，put方法将永远不会阻塞。
 *
 * Created by gxy on 2016/3/9.
 */
public class ConsumerProducerUsingBlockingQueue {
    private static ArrayBlockingQueue<Integer> buffer = new ArrayBlockingQueue<Integer>(2);

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
                    System.out.println("Producer writers " + i);
                    buffer.put(i++);
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
            try{
                while(true) {
                    System.out.println("\t\tConsumer reads " + buffer.take());
                    Thread.sleep((int)(Math.random() * 10000));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
