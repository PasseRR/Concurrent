package com.utoken.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * atomic引用类型：
 * @author xiehai
 * @version 1.0
 * @date 2015年4月29日下午3:15:56
 */
public class AtomicReferenceTest {
	private static final String BEFORE_CHANGING = "before";
	private static final String AFTER_CHANGING = "after";
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
	
	public static void main(String[] args) {
		AtomicReference<String> atomicReference = new AtomicReference<String>(BEFORE_CHANGING);
		ExecutorService es = Executors.newFixedThreadPool(10);
		for(int i = 0; i < 10; i++){
			es.execute(new AtomicReferenceThread(atomicReference));
		}
		es.shutdown();
	}
}
