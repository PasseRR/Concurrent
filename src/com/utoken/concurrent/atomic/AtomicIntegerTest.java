package com.utoken.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger Test
 * @author xiehai
 * @version 1.0
 * @date 2015年4月29日上午10:10:22
 */
public class AtomicIntegerTest {
	public static void main(String[] args) {
		ExecutorService es = Executors.newFixedThreadPool(10);
		TestAtomicIntegerThread tat = new TestAtomicIntegerThread();
		for(int i = 0; i < 100; ++i){
			es.submit(tat);
		}
		es.shutdown();
	}
	
	static class TestAtomicIntegerThread implements Runnable{
		
		private AtomicInteger atomicInteger = new AtomicInteger(1);
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " value = " + atomicInteger.getAndIncrement());
		}
	}
}
