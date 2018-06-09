package sd.learningjava.thread.bean;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchBean {

    private final CountDownLatch latch;

    public CountDownLatchBean(CountDownLatch latch) {
        this.latch = latch;
    }

    public void work() {
        System.out.println(Thread.currentThread().getName() + " started to work");
        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() + " finished its work");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " exiting work method");
    }
}
