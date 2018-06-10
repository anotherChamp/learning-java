package com.java.threads;

import java.util.concurrent.locks.ReentrantLock;

class ReCommonclass{
	private ReentrantLock relock = new ReentrantLock();
	private ReentrantLock relock2 = new ReentrantLock();
	
	public void methA() {
		System.out.println("methA: first point");
		boolean trylock = false;
		
		System.out.println("methA: Going to acquire lock "+Thread.currentThread().getName());
		while(!trylock) {
			trylock = relock.tryLock();
			System.out.println("methA: HoldCount "+relock.getHoldCount()+ " Currently acquiring "+Thread.currentThread().getName());
			if(trylock) {
				try {
					System.out.println("methA: Acquired lock "+Thread.currentThread().getName() +" going to sleep");
					Thread.sleep(5000);
					System.out.println("methA: "+Thread.currentThread().getName() +" woke up from sleep");
					if(Thread.currentThread().getName().equals("FirstThread")) {
						System.out.println("methA: Lets goto methB() with "+Thread.currentThread().getName());
						methB();
						System.out.println("methA: Returned from methB to methA "+Thread.currentThread().getName());
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					System.out.println("methA: "+Thread.currentThread().getName()+" releasing lock");
					relock.unlock();
					System.out.println("methA after unlock: HoldCount "+relock.getHoldCount()+ " Currently acquiring "+Thread.currentThread().getName());
				}
			}else {
				System.out.println("methA: Waiting for the lock to be released "+Thread.currentThread().getName());
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
		
		
	}
	

	
	public void methB() {
		System.out.println("methB: first point");
		boolean trylock = false;
		
		System.out.println("methB: Going to acquire lock "+Thread.currentThread().getName());
		while(!trylock) {
			trylock = relock.tryLock();
			System.out.println("methB: HoldCount "+relock.getHoldCount()+ " Currently acquiring "+Thread.currentThread().getName());
			if(trylock) {
				try {
					System.out.println("methB: Acquired lock "+Thread.currentThread().getName() +" going to sleep");
					Thread.sleep(5000);
					System.out.println("methB: "+Thread.currentThread().getName() +" woke up from sleep");
					if(Thread.currentThread().getName().equals("SecondThread")) {
						System.out.println("methB: Lets goto methA() with "+Thread.currentThread().getName());
						methA();
						System.out.println("methB: Returned from methA to methB "+Thread.currentThread().getName());
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					System.out.println("methB: "+Thread.currentThread().getName()+" releasing lock");
					relock.unlock();
					System.out.println("methB after unlock: HoldCount "+relock.getHoldCount()+ " Currently acquiring "+Thread.currentThread().getName());
				}
			}else {
				System.out.println("methB: Waiting for the lock to be released "+Thread.currentThread().getName());
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		

	}
	
}


class ReMyThread1 implements Runnable{
	ReCommonclass reCommonClass;
	
	public ReMyThread1(ReCommonclass reCommonClass) {
		this.reCommonClass = reCommonClass;
	}
	
	@Override
	public void run() {
		System.out.println("MyThread1: "+Thread.currentThread().getName()+" started");
		this.reCommonClass.methA();
		System.out.println("MyThread1: "+Thread.currentThread().getName()+" ended");
		
	}
	
	
}

class ReMyThread2 implements Runnable{
	ReCommonclass reCommonClass;
	
	public ReMyThread2(ReCommonclass reCommonClass) {
		this.reCommonClass = reCommonClass;
	}
	
	@Override
	public void run() {
		System.out.println("MyThread2: "+Thread.currentThread().getName()+" started");
		this.reCommonClass.methB();
		System.out.println("MyThread2: "+Thread.currentThread().getName()+" ended");
	}
	
	
}



public class ReentrantLockTest {

	public static void main(String[] args) {
		ReCommonclass commonClass = new ReCommonclass();
		
		Thread t1 = new Thread(new ReMyThread1(commonClass),"FirstThread");
		Thread t2 = new Thread(new ReMyThread2(commonClass),"SecondThread");
		
		
		System.out.println("Starting threads");
		
		t1.start();
		t2.start();
				
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Threads finished execution");

	}

}
