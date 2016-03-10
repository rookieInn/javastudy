package com.gxy.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 如果一个共享资源被多个线程同时访问，可能会遭到破坏。
 *
 * 创建并启动100个线程，每个线程都往同一个账户添加一个便士。
 * 定义一个名为Account的类模拟账户，一个名为AddAPennyTask的类向账号里添加一个便士，以及一个用于创建和启动线程的主类。
 *
 * Created by gxy on 2016/3/8.
 */
public class AccountWithoutSync {
    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            executor.execute(new AddPennyTask());
        }
        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        System.out.println("What is balance? " + account.getBalance());
    }

    private static class AddPennyTask implements Runnable {
        @Override
        public void run() {
            account.deposit(1);
        }
    }

    private static class Account {
        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        public synchronized void deposit(int amount){
            int newBalance = balance + amount;

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance = newBalance;
        }
    }

}
