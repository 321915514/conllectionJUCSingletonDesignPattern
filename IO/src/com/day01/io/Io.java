package com.day01.io;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ThreadTest implements Runnable{
    private static volatile int num = 100;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    @Override
    public  void run() {
        lock.lock();
        try {
            while (num >= 0) {
                System.out.println(Thread.currentThread().getName() + " : " + num--);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock();
        }
    }
}
public class Io {
        public static void main(String[] args) {
            ThreadTest threadTest = new ThreadTest();
            new Thread(threadTest,"A").start();
            new Thread(threadTest,"B").start();
            new Thread(threadTest,"C").start();
        }

    }

