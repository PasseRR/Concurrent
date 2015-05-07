package com.utoken.concurrent.atomic;

import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;

/**
 * atomic�������ͣ�
 * @author xiehai
 * @version 1.0
 * @date 2015��4��29������3:15:56
 */
public class AtomicReferenceTest {
	/**
	 * ABA���⣬ʲô��ABA�����أ���ĳЩ�����ڴ����������˳��ģ�<br>
	 * Ҳ���ǲ������ظ����������£���ĳЩ����µ���һ��������A���B��<br>
	 * ���м���ܾ���0-N�����ں�����A����ʱA�������ٱ��B�ˣ�<br>
	 * ��Ϊ��ʱ��״̬�Ѿ������˸ı䣬���磺�����ʽ�������һ����Ŀ������<br>
	 * Ҫ���ʽ���80-100Ԫ���ˣ�����20ԪǮ��ʱ�����һ�죬<br>
	 * Ҳ���Ǻ�̨����᲻��ɨ����Щ�û����ʽ��Ƿ����������Χ��<br>
	 * ����Ҫ�����ӹ����˾Ͳ����������ˣ��������20��<br>
	 * ����ȡ��10Ԫ�����������Χ����ô�Ϳ����������ֳ�����<br>
	 * ����ABA�����ˣ����ƵĻ�����������н���<br>
	 * ����ÿ��ÿ��������3����������Ǹ��ȼ��Ľ��ĸ����ȵȡ�
	 * @author xiehai
	 * @version 1.0
	 * @date 2015��4��29������5:48:09
	 */
	static class AtomicReferenceThread implements Runnable{
		private AtomicReference<String> atomicReference = new AtomicReference<String>();
		
		@Override
		public void run() {
			
		}
	}
	
	public static void main(String[] args) {
		 // �½�AtomicLongArray����
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
