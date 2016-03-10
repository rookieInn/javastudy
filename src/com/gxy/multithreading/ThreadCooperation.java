package com.gxy.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程间协作
 *
 * 通过保证在临界区上多个线程的相互排斥，线程同步完全可以避免竞争状态的发生，但是有时候。还需要线程之间的相互协作。
 * 使用条件便于线程间通信。
 * 一个线程可以指定在某种条件下该做什么。
 * 条件是通过调用Lock对象的newCondition()方法而创建对象。
 * 一旦创建了条件，就可以使用await()、signal()和signalAll()方法来实现线程之间的相互通信。
 * await()方法可以让当前线程都处于等待状态知道条件发生。
 * signal()方法唤醒一个等待的线程，而signalAll()唤醒所有等待的线程。
 *
 * 假设创建并启动两个任务，一个用来向账户中存款，另一个从同一账户中提款。当提款的数额大于账户的当前余额时，提款线程必须等待。
 * 不管什么时候，只要向账户新存入一笔资金，存储线程必须通知提款线程重新尝试。
 * 如果余额仍未达到提款的数额，提款线程必须继续等待新的存款。
 *
 * Created by gxy on 2016/3/8.
 */
public class ThreadCooperation {
    private static Account account = new Account();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.execute(new DepositTask());
        executor.execute(new WithdrawTask());
        executor.shutdown();

        System.out.println("Thread 1\t\tThread 2\t\tBalance");
    }

    public static class DepositTask implements Runnable {
        public void run() {
            try{
                while(true) {
                    account.deposit((int)(Math.random() * 10) + 1);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static class WithdrawTask implements  Runnable {
        @Override
        public void run() {
            while (true) {
                account.withdraw((int)(Math.random() * 10) + 1);
            }
        }
    }

    public static class Account {
        private static Lock lock = new ReentrantLock();

        private static Condition newDeposit = lock.newCondition();

        private int balance = 0;

        public int getBalance() {
            return balance;
        }

        /**
         * 取钱
         * @param amount
         */
        public void withdraw(int amount) {
            lock.lock();
            try{
                while (balance < amount) {
                    System.out.println("\t\tWait for a deposit");
                    newDeposit.await();
                }

                balance -= amount;
                System.out.println("\t\tWithdraw " + amount + "\t\t" + getBalance());
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        /**
         * 存钱
         * @param amount
         */
        public void deposit(int amount) {
            lock.lock();
            try{
                balance += amount;
                System.out.println("Deposit " + amount + "\t\t\t\t\t" + getBalance());

                newDeposit.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}
