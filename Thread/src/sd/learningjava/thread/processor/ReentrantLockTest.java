package sd.learningjava.thread.processor;

import java.util.concurrent.locks.ReentrantLock;

class ReCommonclass2 {
    private ReentrantLock relock1 = new ReentrantLock();
    private ReentrantLock relock2 = new ReentrantLock();

    public void methA() {
        System.out.println("methB: first point");

        System.out.println("methB: Going to acquire lock 1 " + Thread.currentThread().getName());
        relock1.lock();

        try {
            System.out.println("methB: Acquired lock 1 " + Thread.currentThread().getName() + " going to sleep");
            Thread.sleep(5000);
            System.out.println("methB: " + Thread.currentThread().getName() + " woke up from sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("methB: " + Thread.currentThread().getName() + " releasing lock 1");
            relock1.unlock();
        }


    }


    public void methB() {
        System.out.println("methB: first point");

        System.out.println("methB: Going to acquire lock 2 " + Thread.currentThread().getName());
        relock2.lock();

        try {
            System.out.println("methB: Acquired lock 2 " + Thread.currentThread().getName() + " going to sleep");
            Thread.sleep(5000);
            System.out.println("methB: " + Thread.currentThread().getName() + " woke up from sleep");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("methB: " + Thread.currentThread().getName() + " releasing lock 2");
            relock2.unlock();
        }


    }


}


class Threadd1 implements Runnable {
    ReCommonclass2 reCommonClass;

    public Threadd1(ReCommonclass2 reCommonClass) {
        this.reCommonClass = reCommonClass;
    }

    @Override
    public void run() {
        System.out.println("Threadd1: " + Thread.currentThread().getName() + " started");
        this.reCommonClass.methA();
        System.out.println("Threadd1: " + Thread.currentThread().getName() + " ended");

    }


}

class Threadd2 implements Runnable {
    ReCommonclass2 reCommonClass;

    public Threadd2(ReCommonclass2 reCommonClass) {
        this.reCommonClass = reCommonClass;
    }

    @Override
    public void run() {
        System.out.println("Threadd2: " + Thread.currentThread().getName() + " started");
        this.reCommonClass.methB();
        System.out.println("Threadd2: " + Thread.currentThread().getName() + " ended");
    }


}


public class ReentrantLockTest {

    public static void main(String[] args) {
        ReCommonclass2 commonClass = new ReCommonclass2();

        Thread t1 = new Thread(new Threadd1(commonClass), "FirstThread");
        Thread t2 = new Thread(new Threadd2(commonClass), "SecondThread");


        System.out.println("Starting threads");

        t1.start();
        t2.start();


        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        System.out.println("Threads finished execution");

    }

}
