package sd.learningjava.thread.bean;

import java.util.concurrent.TimeUnit;

public class SynchronizedBean {

    public synchronized void metA() {

        System.out.println("Inside metA with " + Thread.currentThread().getName());
        try {
            System.out.println(Thread.currentThread().getName() + " is going to sleep for 5 seconds");
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() + " back from sleep");
            System.out.println(Thread.currentThread().getName() + " is going to wait");
            this.wait();
            System.out.println(Thread.currentThread().getName() + " wait is over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Bye metA - " + Thread.currentThread().getName());
    }

    public synchronized void metB() {

        System.out.println("Inside metB with " + Thread.currentThread().getName());
        try {
            System.out.println(Thread.currentThread().getName() + " is going to sleep for 5 seconds");
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() + " back from sleep");
            System.out.println(Thread.currentThread().getName() + " is going to notify");
            this.notifyAll();
            System.out.println(Thread.currentThread().getName() + " notified waiting thread(s)");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Bye metB - " + Thread.currentThread().getName());
    }
}
