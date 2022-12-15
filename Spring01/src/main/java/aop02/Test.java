package aop02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {

	public static void main(String[] args) {

		// ** IOC/DI 적용
		// => 공통 조상으로 묶어준다 (Interface Person 적용)
		// => 클래스 교체를 위해서 소스코드 수정 없이 가능.
		
		AbstractApplicationContext sc = new GenericXmlApplicationContext("aop02.xml");
		
		Person programmerB = (Person)sc.getBean("boy"); // Object이기 때문에 Type Person으로 맞춤
		Person programmerG = (Person)sc.getBean("girl");
		
		try {
			System.out.println("** Boy Test **");
			programmerB.doStudying();
			
			System.out.println("\n** Girl Test **");
			programmerG.doStudying();
			
		} catch (Exception e) {
			System.out.println("\n** Main Exception => " + e.toString());
		}
		
		sc.close();
		
	} // main

} // class
