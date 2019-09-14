package com.day01.juc;


import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;
import java.util.stream.LongStream;

public class Juc {
    public static void main(String[] args) {
/*        MyThread thread = new MyThread();
        new Thread(thread).start();
        while (true){
            if (thread.isFlag()){
                System.out.println("--------------");
                break;
            }
        }*/
  /*      MyThread1 myThread1 = new MyThread1();
        for (int i = 0; i <10 ; i++) {
            new Thread(myThread1).start();
        }*/
        /*ThreadList threadList = new ThreadList();
        for (int i = 0; i <10 ; i++) {
            new Thread(threadList).start();
        }*/
       /* CountDownLatch latch = new CountDownLatch(5);
        TestCountDownLatch countDownLatch=new TestCountDownLatch(latch);
        long start= (int) System.currentTimeMillis();
        for (int i = 0; i <5 ; i++) {
            new Thread(countDownLatch).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end=System.currentTimeMillis();
        System.out.println("时间是:"+(end-start)*24);*/

       /* TestCallable testCallable = new TestCallable();
        FutureTask<Integer> integerFutureTask = new FutureTask<>(testCallable);//也可实现闭锁的功能
        new Thread(integerFutureTask).start();
        try {
            Integer result = integerFutureTask.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        Clerk clerk = new Clerk();
        Consumer consumer = new Consumer(clerk);
        Productor productor=new Productor(clerk);
        new Thread(consumer,"消费者 A").start();
        new Thread(new Consumer(clerk),"消费者 B").start();
        new Thread(productor,"生产者 A").start();
        new Thread(new Productor(clerk),"生产者 B").start();
        AlterThread alterThread = new AlterThread();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 20; i++) {
                    alterThread.loopA(i);
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 20; i++) {
                    alterThread.loopB(i);
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 20; i++) {
                    alterThread.loopC(i);
                    System.out.println("---------------------------");
                }
            }
        },"C").start();

    }





    }
class MyThread implements Runnable{
    private volatile boolean flag=false;
    public boolean isFlag() {
        return flag;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        flag=true;

        System.out.println("flag"+isFlag());
    }
}
//cas
class MyThread1 implements Runnable{
    private AtomicInteger num=new AtomicInteger();

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int n =num.getAndIncrement();
        System.out.println(n);
    }
}
class ThreadList implements Runnable{
    /*private static List<String> list=new ArrayList<>();*/
    private static List<String> list=new CopyOnWriteArrayList<>();//写入并复制,添加效率低因为每次都会添加都会复制,开销大,不推荐,如果并发迭代可以用
    static {
        list.add("aa");
        list.add("bb");
        list.add("cc");
    }
    @Override
    public void run() {
        Iterator<String> iterator=list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        list.add("ee");

    }
}
class TestCountDownLatch implements Runnable{
    private CountDownLatch contDownLatch;//用于在哪个线程之前或线程之后执行

    public TestCountDownLatch(CountDownLatch contDownLatch) {
        this.contDownLatch = contDownLatch;
    }
    @Override
    public void run() {
        synchronized (this){
            try {
                for (int i = 0; i < 50000; i++) {
                    if (i % 2 == 0) {
                        System.out.println(i);
                    }
                }
            }finally {
                contDownLatch.countDown();
            }
        }
    }
}
//Callable有返回值,相较于实现runnerable有返回值,能抛出异常
class TestCallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
            int sum=0;
        for (int i = 0; i <=100 ; i++) {
            sum+=i;
        }
        return sum;
    }
}
/*
//消费者
class Consumer implements Runnable {
    private Clerk clerk;
    public  Consumer(Clerk clerk) {
        this.clerk = clerk;
    }
    @Override
    public  void run() {
        System.out.println("消费产品");
        while (true){
            clerk.sale();
        }
    }
}
//生产者
class Productor implements Runnable{
    private Clerk clerk;

    public  Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public  void run() {
        System.out.println("生产产品");
        while (true){
            clerk.get();
        }
    }
}
//店员
class Clerk{
    private int product=0;
    public synchronized void get(){
        while(product>=10){
            System.out.println("买满了");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.println("买一个产品,库存还有:"+ ++product);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.notify();
    }
    public synchronized void sale(){
        while(product<=0){
            System.out.println("没货了");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.println("卖一个产品,剩余"+product--+"个产品");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.notify();
    }
}
*/
//消费者
class Consumer implements Runnable {
    private Clerk clerk;
    public  Consumer(Clerk clerk) {
        this.clerk = clerk;
    }
    @Override
    public  void run() {
        System.out.println(Thread.currentThread().getName()+"消费产品");
        while (true){
            clerk.sale();
        }
    }
}
//生产者
class Productor implements Runnable{
    private Clerk clerk;

    public  Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public  void run() {
        System.out.println(Thread.currentThread().getName()+"生产产品");
        while (true){
            clerk.get();
        }
    }
}
//店员
class Clerk{
    private int product=0;
    private ReentrantLock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();
    public  void get(){
        lock.lock();
        try {
            while (product >= 10) {
                System.out.println("买满了");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+": 买一个产品,库存还有:" + ++product);
            condition.signalAll();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            lock.unlock();
        }

    }

    public  void sale() {
        lock.lock();
        try {
            while (product <= 0) {
                System.out.println("没货了");
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+": 卖一个产品,剩余" + --product + "个产品");
            condition.signalAll();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            lock.unlock();
        }
    }
}
class AlterThread{
    private int number=1;
    private Lock lock=new ReentrantLock();
    private Condition condition1=lock.newCondition();
    private Condition condition2=lock.newCondition();
    private Condition condition3=lock.newCondition();

    public void loopA(int loopNum){
        lock.lock();
        try {
            if (number != 1) {
                condition1.await();
            }
            for (int i = 1; i <=1 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+loopNum);
            }
            number=2;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void loopB(int loopNum){
        lock.lock();
        try {
            if (number != 2) {
                condition2.await();
            }
            for (int i = 1; i <=1 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+loopNum);
            }
            number=3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void loopC(int loopNum){
        lock.lock();
        try {
            if (number != 3) {
                condition3.await();
            }
            for (int i = 1; i <=1 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i+"\t"+loopNum);
            }
            number=1;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
class HasStatic{
     private static int x=100;
     public static void main(String args[]){
                 HasStatic hs1=new HasStatic();
                hs1.x++;
                 HasStatic  hs2=new HasStatic();
                  hs2.x++;
                 hs1=new HasStatic();
                 hs1.x++;
                HasStatic.x--;
              System.out.println("x="+x);
     }
  }//笔试题
class TestReadWriteLock{
    public static void main(String[] args) {
        TestReadWriteLock readWriteLock=new TestReadWriteLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLock.set((int)(Math.random()*101));
            }
        },"write").start();
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    readWriteLock.get();
                }
            }).start();
        }
    }
    private int number;
    private ReadWriteLock lock=new ReentrantReadWriteLock();
    public void get(){
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+" : "+number);
        }finally {
            lock.readLock().unlock();
        }
    }
    public void set(int number){
        lock.writeLock().lock();
        try{
            System.out.println("write");
            this.number=number;
        }finally {
            lock.writeLock().unlock();
        }
    }
}

//线程池
class ThreadPool{
    public static void main(String[] args){
        ExecutorService pool=Executors.newFixedThreadPool(5);
        pool.submit(new Thread(new Runnable(){
            public void run(){
                for(int i=0;i<=100;i++){
                    if(i%2==0){
                        System.out.println(i);
                    }
                }
            }
        }));
        pool.shutdown();
    }

}
class ThreadPoolExecutor{

        public static void main(String[] args) throws Exception {
            ScheduledExecutorService pool=Executors.newScheduledThreadPool(5);
            for (int i = 0; i <5; i++) {
                Future<Integer> result=pool.schedule(new Callable<Integer>(){
                    @Override
                    public Integer call() throws Exception {
                        int num=new Random().nextInt(100);
                        System.out.println(Thread.currentThread().getName()+" : "+num);
                        return num;
                    }
                },3,TimeUnit.SECONDS);
                System.out.println(result.get());
            }

            pool.shutdown();
        }


    }
class TestFolkJoin {
    public static void main(String[] args) {
        Instant start = Instant.now();
        Long sum = LongStream.rangeClosed(0L, 100000000000L).parallel().reduce(0L, Long::sum);
        System.out.println(sum);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
    }
}

class test{
       public static void main(String[] args) {
           Instant start = Instant.now();
           long sum=0L;
           for (long i=0L;i<=100000000000L;i++){
               sum+=i;
           }
           System.out.println(sum);
           Instant end = Instant.now();
           System.out.println(Duration.between(start, end).toMillis());
       }
    }
