package sd.learningjava.thread.processor;

import sd.learningjava.thread.bean.SynchronizedBean;

import java.util.concurrent.TimeUnit;

public class WaitNotify {

    public void demoRun() {

        SynchronizedBean a = new SynchronizedBean();
        Thread t1 = new Thread(a::metA);
        Thread t2 = new Thread(a::metB);
        Thread t3 = new Thread(a::metA);
        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");
        t1.start();
        t3.start();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
