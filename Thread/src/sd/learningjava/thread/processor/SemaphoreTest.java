package sd.learningjava.thread.processor;

import java.util.concurrent.Semaphore;

class Shared{
	static int counter = 0;
}

class SemaphoreThread implements Runnable{
	Semaphore sem;
	
	public SemaphoreThread(Semaphore sem) {
		this.sem = sem;
	}
	
	@Override
	public void run() {
		String tname=Thread.currentThread().getName();
		
		System.out.println("Running the thread: "+tname);
		System.out.println("Going to acquire semaphore, thread: "+tname);
		
		try {
			sem.acquire();
			System.out.println("Acquired semaphore: "+tname);
			
			if(tname.equals("A")) {
				for(int i=0;i<1000;i++) {
					System.out.println("Thread: "+tname+", counter: "+ ++Shared.counter);
					Thread.sleep(10);
				}
			}else {
				for(int i=0;i<1000;i++) {
					System.out.println("Thread: "+tname+", counter: "+ --Shared.counter);
					Thread.sleep(10);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			System.out.println("Releasing Semaphore thread: "+tname);
			sem.release();
			System.out.println("Semaphore Released by thread: "+tname);
		}
		
	}
	
}


public class SemaphoreTest {

	public static void main(String[] args) {
		Semaphore sem = new Semaphore(1);

		Thread t1 = new Thread(new SemaphoreThread(sem),"A");
		Thread t2 = new Thread(new SemaphoreThread(sem),"B");
		
		System.out.println("Starting Threads:");
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Thread execution finished");
		System.out.println("Final value of the counter: "+Shared.counter);
	}

}
