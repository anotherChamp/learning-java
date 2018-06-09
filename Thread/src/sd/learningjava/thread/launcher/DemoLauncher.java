package sd.learningjava.thread.launcher;

import sd.learningjava.thread.processor.WaitNotify;

public class DemoLauncher {

    public static void main(String... args) {

        WaitNotify w = new WaitNotify();
        w.demoRun();
    }
}
