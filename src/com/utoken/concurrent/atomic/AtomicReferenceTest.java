package com.utoken.concurrent.atomic;

import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;

/**
 * atomic引用类型：
 * @author xiehai
 * @version 1.0
 * @date 2015年4月29日下午3:15:56
 */
public class AtomicReferenceTest {
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
		private AtomicReference<String> atomicReference = new AtomicReference<String>();
		
		@Override
		public void run() {
			
		}
	}
	
	public static void main(String[] args) {
		 // 新建AtomicLongArray对象
        long[] arrLong = new long[] {10, 20, 30, 40, 50};
        AtomicLongArray ala = new AtomicLongArray(arrLong);

        ala.set(0, 100);
        for (int i=0, len=ala.length(); i<len; i++) 
            System.out.printf("get(%d) : %s\n", i, ala.get(i));

        System.out.printf("%20s : %s\n", "getAndDecrement(0)", ala.getAndDecrement(0));
        System.out.printf("%20s : %s\n", "decrementAndGet(1)", ala.decrementAndGet(1));
        System.out.printf("%20s : %s\n", "getAndIncrement(2)", ala.getAndIncrement(2));
        System.out.printf("%20s : %s\n", "incrementAndGet(3)", ala.incrementAndGet(3));

        System.out.printf("%20s : %s\n", "addAndGet(100)", ala.addAndGet(0, 100));
        System.out.printf("%20s : %s\n", "getAndAdd(100)", ala.getAndAdd(1, 100));

        System.out.printf("%20s : %s\n", "compareAndSet()", ala.compareAndSet(2, 31, 1000));
        System.out.printf("%20s : %s\n", "get(2)", ala.get(2));
	}
}
