package com.gxy.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 *
 * java提供Executor接口来执行线程池中的任务，提供ExecutorService接口来管理和控制任务。
 * ExecutorService是Executor的子接口
 *
 * 为了创建一个Executor对象，可以使用Executors类中的静态方法。
 * newFixedThreadPool(int)方法在线程池中创建了固定数目的线程。
 * 如果线程完成了任务的执行，它可以被重新使用来执行另外一个任务。
 * 如果线程池中所有的线程都不是处于空闲状态，而且有任务在等待执行，那么在关机之前，如果由于一个错误终止了一个线程，就会创建一个新线程来替代它。
 * 如果线程池中所有的线程都不是处于空闲状态，而且有任务在等待执行，那么newCachedThreadPool()方法就会创建一个新线程。
 * 如果缓冲池中的线程在60秒内都没有被使用就该终止它。
 * 对于多个小任务而言，一个缓冲池已经足够。
 *
 *
 * java.util.concurrent.Executor
 *  +execute(Runnable object): void        //Executes the runnable task
 *
 * java.util.concurrent.Executor extends Executor
 *  +shutdown(): void   关闭执行器，但允许完成执行器中的任务。一旦关闭它就不能接受新任务。
 *  +shutdownNow(): List<Runnable> 即使线程池中还有未完成的线程，还是会立即关闭执行器。返回未完成的任务的清单。
 *  +isShutDown(): boolean 如果执行器已经被关闭则返回true
 *  +isTerminated(): boolean 如果线程池中所有任务都被终止，则返回true
 *
 * java.util.concurrent.Executors
 *  +newFixedThreadPool(numberOfThreads:int):ExecutorService
 *      创建一个线程池，该线程池可以并发的线程数固定不变。
 *      在线程的当前任务结束后，它可以被重用以执行另一任务。
 *  +newCachedThreadPool():ExecutorService
 *      创建一个线程池，它可以按需创建新线程，但当前面创建的线程可用时，则重用它们。
 *
 * 注意： 如果需要为一个任务创建一个线程，就使用Thread类。
 *        如果需要为多个任务创建线程，最好使用线程池。
 *
 * Created by gxy on 2016/3/7.
 */
public class ExecutorDemo {

    public static void main(String[] args) {
        //1 三个任务并发执行
        ExecutorService executor = Executors.newFixedThreadPool(3);
        //2 三个任务按照顺序执行
        ExecutorService executor1 = Executors.newFixedThreadPool(1);
        //3 newCachedThreadPool 为每个等待的任务创建一个新线程，三个任务都并发的执行
        ExecutorService executor2 = Executors.newCachedThreadPool();

        executor1.execute(new PrintChar('a', 100));
        executor1.execute(new PrintChar('b', 100));
        executor1.execute(new PrintNum(100));

        executor1.shutdown();

    }

}
