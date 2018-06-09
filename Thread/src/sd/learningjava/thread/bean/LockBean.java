package sd.learningjava.thread.bean;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockBean {

    private final Lock lock = new ReentrantLock();
    private final Condition lockCondition = lock.newCondition();

    public void metA() {

        System.out.println("Inside metA with " + Thread.currentThread().getName());
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " is going to sleep for 5 seconds");
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() + " back from sleep");
            System.out.println(Thread.currentThread().getName() + " is going to wait");
            lockCondition.await();
            System.out.println(Thread.currentThread().getName() + " wait is over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println("Bye metA - " + Thread.currentThread().getName());
    }

    public void metB() {

        System.out.println("Inside metB with " + Thread.currentThread().getName());
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " is going to sleep for 5 seconds");
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() + " back from sleep");
            System.out.println(Thread.currentThread().getName() + " is going to notify");
            lockCondition.signalAll();
            System.out.println(Thread.currentThread().getName() + " notified waiting thread(s)");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        System.out.println("Bye metB - " + Thread.currentThread().getName());
    }
}
