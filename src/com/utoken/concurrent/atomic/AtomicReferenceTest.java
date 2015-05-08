package com.utoken.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * atomic�������ͣ�
 * @author xiehai
 * @version 1.0
 * @date 2015��4��29������3:15:56
 */
public class AtomicReferenceTest {
	private static final String BEFORE_CHANGING = "before";
	private static final String AFTER_CHANGING = "after";
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
				System.out.println(Thread.currentThread().getName() + "�ı���AtomicReference������!");
			} else {
				System.out.println(Thread.currentThread().getName() + "û�ܸı�AtomicReference������!");
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
