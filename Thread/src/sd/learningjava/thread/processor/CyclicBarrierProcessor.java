package sd.learningjava.thread.processor;

import sd.learningjava.thread.bean.CyclicBarrierBean;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierProcessor {

    public void demoRun() {
        CyclicBarrier cb = new CyclicBarrier(5);
        for(int i = 1; i <= 4; i++) {
            CyclicBarrierBean b = new CyclicBarrierBean(cb);
            Thread t = new Thread(b::work);
            t.setName("t" + i);
            t.start();
            System.out.println(t.getName() + " started");
        }
        try {
            System.out.println("Main thread is waiting");
            cb.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("CyclicBarrier is broken");
    }
}
