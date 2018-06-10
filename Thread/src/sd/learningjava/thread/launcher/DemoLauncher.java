package sd.learningjava.thread.launcher;

import sd.learningjava.thread.processor.*;

import java.util.Scanner;

public class DemoLauncher {

    public static void main(String... args) {

        System.out.println("1. WaitNotify");
        System.out.println("2. AwaitSignal");
        System.out.println("3. CountDownLatch");
        System.out.println("4. CyclicBarrier");
        System.out.println("5. ExecutorServiceAndFuture");
        System.out.println("Enter your choice: ");
        int choice = new Scanner(System.in).nextInt();
        switch(choice) {
            case 1:
                WaitNotify wn = new WaitNotify();
                wn.demoRun();
                break;
            case 2:
                AwaitSignal as = new AwaitSignal();
                as.demoRun();
                break;
            case 3:
                CountDownLatchProcessor cd = new CountDownLatchProcessor();
                cd.demoRun();
                break;
            case 4:
                CyclicBarrierProcessor cb = new CyclicBarrierProcessor();
                cb.demoRun();
                break;
            case 5:
                ExecutorServiceAndFuture ex = new ExecutorServiceAndFuture();
                ex.demoRun();
                break;
        }
    }
}
