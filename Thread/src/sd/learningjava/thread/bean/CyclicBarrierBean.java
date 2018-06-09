package sd.learningjava.thread.bean;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierBean {

    private final CyclicBarrier cb;

    public CyclicBarrierBean(CyclicBarrier cb) {
        this.cb = cb;
    }

    public void work() {
        System.out.println(Thread.currentThread().getName() + " started to work");
        try {
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() + " finished its work");
            cb.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " exiting work method");
    }
}
