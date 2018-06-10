package sd.learningjava.thread.processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class ExecutorServiceAndFuture {

    public void demoRun() {
        ExecutorService service = Executors.newFixedThreadPool(4);
        System.out.println("-- ExecutorService methods --");
        System.out.println("1. execute");
        System.out.println("2. submitRunnable");
        System.out.println("3. submitCallable");
        System.out.println("4. invokeAny");
        System.out.println("5. invokeAll");
        int choice = new Scanner(System.in).nextInt();
        if (choice == 1)
            execute(service);
        else if (choice == 2)
            submitRunnable(service);
        else if (choice == 3)
            submitCallable(service);
        else if (choice == 4)
            invokeAny(service);
        else if (choice == 5)
            invokeAll(service);

        service.shutdown();
    }

    private void execute(final ExecutorService service) {
        // ExecutorService execute method runs a task asynchronously in a thread
        // from ExecutorService thread pool. This execute method does not return
        // a result.
        System.out.println("ExecutorService execute method");
        for (int i = 0; i < 10; i++) {
            int finalI = i + 1;
            service.execute(() -> work(finalI));
        }
    }

    private void submitRunnable(final ExecutorService service) {
        // ExecutorService submit(Runnable) method runs a task asynchronously in a thread
        // from ExecutorService thread pool. This execute method returns a result of type Future.
        // Future is a wrapper kind of object which holds actual result.
        // ExecutorService.submit method is asynchronous in nature.
        // But Future.get method is not asynchronous and waits till it gets actual result.
        // When task gets completed Future.get returns null since Runnable.run does not return any value.
        System.out.println("ExecutorService submit(Runnable) method");
        for (int i = 0; i < 10; i++) {
            int finalI = i + 1;
            Future f  = service.submit(() -> work(finalI));
            System.out.println("Is future completed? -" + f.isDone());
            try {
                System.out.println("Value in future - " + f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private void submitCallable(final ExecutorService service) {
        // ExecutorService submit(Callable) method runs a task asynchronously in a thread
        // from ExecutorService thread pool. This execute method returns a result of type Future.
        // Future is a wrapper kind of object which holds actual result.
        // ExecutorService.submit method is asynchronous in nature.
        // But Future.get method is not asynchronous and waits till it gets actual result.
        // When task gets completed Future.get returns V, where V is the return type of Callable.call method.
        System.out.println("ExecutorService submit(Callable) method");
        for (int i = 0; i < 10; i++) {
            int finalI = i + 1;
            Future f  = service.submit(() -> callableWork(finalI));
            System.out.println("Is future completed? - " + f.isDone());
            try {
                System.out.println("Value in future - " + f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private void invokeAny(final ExecutorService service) {
        // ExecutorService invokeAny method assigns a collection of tasks
        // into a pool of threads. Whenever a task ends the other tasks gets
        // interrupted by the ExecutorService. The invokeAny method returns
        // the return type of first finished callable task. This invokeAny
        // method is not asynchronous.
        System.out.println("ExecutorService invokeAny method");
        List<Callable<Integer>> callableList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i + 1;
            callableList.add(() -> callableWork(finalI));
        }
        try {
            Integer result = service.invokeAny(callableList);
            System.out.println("InvokeAny result - " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void invokeAll(final ExecutorService service) {
        // ExecutorService invokeAll method assigns a collection of tasks
        // into a pool of threads. Whenever all tasks end the service stops.
        // This method is asynchronous.
        System.out.println("ExecutorService invokeAny method");
        List<Callable<Integer>> callableList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i + 1;
            callableList.add(() -> callableWork(finalI));
        }
        try {
            List<Future<Integer>> result = service.invokeAll(callableList);
            System.out.println("InvokeAll result - ");
            result.forEach(f -> {
                try {
                    System.out.println(f.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void work(final int i) {
        System.out.println("Starting work_" + i);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work_" + i + " ended");
    }

    private int callableWork(final int i) {
        System.out.println("Starting work_" + i);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Work_" + i + " ended");
        return i;
    }
}
