package com.gxy.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore信号量
 * 信号量可以用来限制访问共享资源的线程数。
 * 在访问资源之前，线程必须从信号量获取许可。
 * 在访问完资源之后，这个线程必须将许可返回给信号量。
 *
 * 为了创建信号量，必须使用可选择的公平策略来确定许可的数量。
 * 任务通过调用信号量的acquire()方法来获得许可，通过调用信号量的release()方法来释放许可。
 * 一旦获得许可，信号量中的可用许可总数减1。
 * 一旦许可被释放，信号量中可用许可的总数加1。
 *
 * java.util.concurrent.Semaphore
 * +Semaphore(numberOfPermits: int) 创建一个带指定数目许可的信号量。公平策略为false
 * +Semaphore(numberOfPermits: int, fair:boolean) 创建一个带指定数目许可和公平策略的信号量。
 * +acquire():void 获取这个信号量的许可。如果无许可可用，线程就被锁住直到有可用许可为止。
 * +release():void 释放一个许可给该信号量。
 *
 * Created by gxy on 2016/3/9.
 */
public class ThreadCooperationUseSemaphore {
    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            executor.execute(new AddAPennyTask());
        }

        executor.shutdown();

        while (!executor.isTerminated()) {}

        System.out.println("What is balance ?" + account.getBalance());
    }

    public static class AddAPennyTask implements  Runnable {
        public void run() {
            account.deposit(1);
        }
    }

    public static class Account {
        private static Semaphore semaphore = new Semaphore(1);
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public void deposit(int amount) {
            try {
                semaphore.acquire();
                int newBalance= balance + amount;
                Thread.sleep(5);

                balance = newBalance;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
               semaphore.release();
            }

        }
    }
}
