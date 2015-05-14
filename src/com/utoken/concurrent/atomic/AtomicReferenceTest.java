package com.utoken.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * atomic�������ͣ�
 * @author xiehai
 * @version 1.0
 * @date 2015��4��29������3:15:56
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
				System.out.println(Thread.currentThread().getName() + "�ı���AtomicReference������!");
			} else {
				System.out.println(Thread.currentThread().getName() + "û�ܸı�AtomicReference������!");
			}
		}
	}
	
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
				System.out.println(Thread.currentThread().getName() + "�޸��˶���!"
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
				System.out.println(Thread.currentThread().getName() + "�޸Ļ�ȥ�˶���!"
						+ atomicStampedReference.getStamp());
			}
		}
	}
	
	/**
	 * ����AtomicReference
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
