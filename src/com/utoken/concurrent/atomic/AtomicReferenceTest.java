package com.utoken.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * atomic引用类型：
 * @author xiehai
 * @version 1.0
 * @date 2015年4月29日下午3:15:56
 */
public class AtomicReferenceTest {
	private static final String BEFORE_CHANGING = "before";
	private static final String AFTER_CHANGING = "after";
	
	static class AtomicReferenceThread implements Runnable{
//		private static AtomicReference<String> atomicReference = new AtomicReference<String>(BEFORE_CHANGING);
		
		private AtomicReference<String> atomicReference;
		
		public AtomicReferenceThread(){
			
		}
		
		public AtomicReferenceThread(AtomicReference<String> atomicReference) {
			this.atomicReference = atomicReference;
		}
		
		@Override
		public void run() {
			if(atomicReference.compareAndSet(BEFORE_CHANGING, AFTER_CHANGING)){
				System.out.println(Thread.currentThread().getName() + "改变了AtomicReference的引用!");
			} else {
				System.out.println(Thread.currentThread().getName() + "没能改变AtomicReference的引用!");
			}
		}
	}
	
	/**
	 * ABA问题，什么是ABA问题呢，当某些流程在处理过程中是顺向的，<br>
	 * 也就是不允许重复处理的情况下，在某些情况下导致一个数据由A变成B，<br>
	 * 再中间可能经过0-N个环节后变成了A，此时A不允许再变成B了，<br>
	 * 因为此时的状态已经发生了改变，例如：银行资金里面做一批账目操作，<br>
	 * 要求资金在80-100元的人，增加20元钱，时间持续一天，<br>
	 * 也就是后台程序会不断扫描这些用户的资金是否是在这个范围，<br>
	 * 但是要求增加过的人就不能再增加了，如果增加20后，<br>
	 * 被人取出10元继续在这个范围，那么就可以无限套现出来，<br>
	 * 就是ABA问题了，类似的还有抢红包或中奖，<br>
	 * 比如每天每个人限量3个红包，中那个等级的奖的个数等等。
	 * @author xiehai
	 * @version 1.0
	 * @date 2015年4月29日下午5:48:09
	 */
	static class AtomicStampedReferenceThread implements Runnable{
		private AtomicStampedReference<String> atomicStampedReference;
		
		public AtomicStampedReferenceThread(AtomicStampedReference<String> atomicStampedReference){
			this.atomicStampedReference = atomicStampedReference;
		}
		
		@Override
		public void run() {
			final int stamp = atomicStampedReference.getStamp();
			if (atomicStampedReference.compareAndSet(BEFORE_CHANGING,
					AFTER_CHANGING, stamp, stamp + 1)) {
				System.out.println(Thread.currentThread().getName() + "修改了对象!"
						+ atomicStampedReference.getStamp());
			}
		}
	}
	
	static class AtomicStampedReferenceBackThread implements Runnable{
		private AtomicStampedReference<String> atomicStampedReference;
		
		public AtomicStampedReferenceBackThread(AtomicStampedReference<String> atomicStampedReference){
			this.atomicStampedReference = atomicStampedReference;
		}
		
		@Override
		public void run() {
			final int stamp = atomicStampedReference.getStamp();
			if (atomicStampedReference.compareAndSet(AFTER_CHANGING,
					BEFORE_CHANGING, stamp, stamp + 1)) {
				System.out.println(Thread.currentThread().getName() + "修改回去了对象!"
						+ atomicStampedReference.getStamp());
			}
		}
	}
	
	/**
	 * 测试AtomicReference
	 */
	public static void testAtomicReference(){
		AtomicReference<String> atomicReference = new AtomicReference<String>(BEFORE_CHANGING);
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i = 0; i < 10; i++){
			es.execute(new AtomicReferenceThread(atomicReference));
		}
		es.shutdown();
	}
	
	public static void testAtomicStampedReference(){
		AtomicStampedReference<String> atomicStampedReference = new AtomicStampedReference<String>(
				BEFORE_CHANGING, 0);
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for(int i = 0; i < 20; i++){
			executorService.execute(new AtomicStampedReferenceThread(atomicStampedReference));
			executorService.execute(new AtomicStampedReferenceBackThread(atomicStampedReference));
		}
		executorService.shutdown();
	}
	
	public static void main(String[] args) {
		testAtomicStampedReference();
	}
}
