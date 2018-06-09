package sd.learningjava.thread.processor;

import sd.learningjava.thread.bean.CountDownLatchBean;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchProcessor {

    public void demoRun() {
        CountDownLatch latch = new CountDownLatch(4);
        for(int i = 1; i <= 4; i++) {
            CountDownLatchBean b = new CountDownLatchBean(latch);
            Thread t = new Thread(b::work);
            t.setName("t" + i);
            t.start();
            System.out.println(t.getName() + " started");
        }
        try {
            System.out.println("Main thread is waiting");
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("CountDownLatch is broken");
    }
}
