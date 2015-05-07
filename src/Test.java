

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ListModel;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Test {
	public static void main(String[] args) {
//		double a = 9.1212341234112312367d;
//		float b = 9.1212341234112312367f;
//		System.out.println(BigDecimal.valueOf(a));
//		System.out.println(BigDecimal.valueOf(b));
//		List<String> list = null;
//		for(String s : list){
//			System.out.println(s);
//		}
//		final String a = "hello";
//		a = "world";
//		String a = null;
//		System.out.println(new BigDecimal(String.valueOf(a)));
//		double a = 100.5;
//		double b = 1.01;
//		BigDecimal aBigDecimal = new BigDecimal(0.0);
//		System.out.println(new BigDecimal(0).compareTo(aBigDecimal));
//		System.out.println(a + b);
//		String aString  = "u1,u2,u3";
//		String bString = "u1";
//		System.out.println(Arrays.toString(aString.split(",")));
//		System.out.println(Arrays.toString(bString.split(",")));
//		List<String> aList = new ArrayList<String>(Arrays.asList(aString.split(",")));
//		List<String> bList = new ArrayList<String>(Arrays.asList(bString.split(",")));
//		System.out.println(aList);
//		System.out.println(bList);
//		System.out.println(aList.removeAll(bList));
//		System.out.println(aList);
		int a = 0b000;
		int b = 0b001;
		int c = 0b010;
		int d = 0b100;
		System.out.println((b|c|d&a) << 1);
	}
	
	public static void test (){
		throw new RuntimeException("test");
	}
}
