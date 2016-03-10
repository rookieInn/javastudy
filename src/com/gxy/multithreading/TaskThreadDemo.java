package com.gxy.multithreading;

/**
 * Created by gxy on 2016/3/6.
 */


public class TaskThreadDemo {

    public static void main(String[] args) {
        Runnable printA = new PrintChar('a', 100);
        Runnable printB = new PrintChar('b', 100);
        Runnable print100 = new PrintNum(100);

        Thread thread1 = new Thread(printA);
        Thread thread2 = new Thread(printB);
        Thread thread3 = new Thread(print100);

        thread1.start();
        thread2.start();
        thread3.start();
    }

}

class PrintNum implements Runnable {
    private int lastNum;

    public PrintNum(int n) {
        lastNum = n;
    }

    @Override
    public void run() {
        for (int i = 0; i < lastNum; i++) {
            System.out.print(" " + i);
        }
    }
}

class PrintChar implements Runnable {
    private char charToPrint; // the character to print
    private int times; //The number of times of repeat

    public PrintChar(char c, int t) {
        charToPrint = c;
        times = t;
    }

    @Override
    public void run() {
        for (int i = 0; i < times; i++) {
            System.out.print(charToPrint);
        }
    }

}
