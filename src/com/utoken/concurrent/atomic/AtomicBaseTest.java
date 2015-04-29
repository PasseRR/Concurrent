package com.utoken.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * atomic基本类型：AtomicInteger、AtomicLong、AtomicBoolean
 * @author xiehai
 * @version 1.0
 * @date 2015年4月29日上午10:10:22
 */
public class AtomicBaseTest {
	public static void main(String[] args) {
		testAtomicBoolean();
	}
	
	/**
	 * 测试AtomicInteger
	 */
	public static void testAtomicInteger(){
		ExecutorService es = Executors.newFixedThreadPool(10);
		AtomicIntegerThread ait = new AtomicIntegerThread();
		for(int i = 0; i < 100; ++i){
			es.submit(ait);
		}
		es.shutdown();
	}
	
	/**
	 * 测试AtomicLong
	 */
	public static void testAtomicLong(){
		ExecutorService es = Executors.newFixedThreadPool(10);
		AtomicLongThread alt = new AtomicLongThread();
		for(int i = 0; i < 100; ++i){
			es.submit(alt);
		}
		es.shutdown();
	}
	
	/**
	 * 测试AtomicBoolean
	 */
	public static void testAtomicBoolean(){
		ExecutorService es = Executors.newFixedThreadPool(10);
		AtomicBooleanThread alt = new AtomicBooleanThread();
		for(int i = 0; i < 100; ++i){
			es.submit(alt);
		}
		es.shutdown();
	}
	
	static class AtomicIntegerThread implements Runnable{
		
		private AtomicInteger atomicInteger = new AtomicInteger(1);
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " value = " + atomicInteger.getAndIncrement());
		}
	}
	
	static class AtomicLongThread implements Runnable{
		private AtomicLong atomicLong = new AtomicLong(1);
		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + " value = " + atomicLong.getAndIncrement());
		}
	}
	
	static class AtomicBooleanThread implements Runnable{
		private AtomicBoolean atomicBoolean = new AtomicBoolean(false);
		@Override
		public void run() {
			boolean flg = atomicBoolean.get();
			atomicBoolean.set(!flg);
			System.out.println(Thread.currentThread().getName() + " value = " + flg);

		}
	}
}
