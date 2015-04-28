

import java.math.BigDecimal;

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
		String a = null;
		System.out.println(new BigDecimal(String.valueOf(a)));
	}
	
	public static void test (){
		throw new RuntimeException("test");
	}
}
