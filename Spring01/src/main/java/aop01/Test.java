package aop01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {

	public static void main(String[] args) {

		// ** Java 기초
		// => 클래스 교체를 위해서는 소스코드를 수정해야 함.
		Boy boy = new Boy();
		Girl girl = new Girl();
		
		boy.doStudying();
		girl.doStudying();
		
		// ** IOC/DI 적용
		// => 공통 조상으로 묶어준다 (Interface Person 적용)
		// => 클래스 교체를 위해서 소스코드 수정 없이 가능.
		System.out.println("** IOC/DI 적용 **");
		
		AbstractApplicationContext sc = new GenericXmlApplicationContext("aop01.xml");
		
		Person programmerB = (Person)sc.getBean("boy"); // Object이기 때문에 Type Person으로 맞춤
		Person programmerG = (Person)sc.getBean("girl");
		
		programmerB.doStudying();
		programmerG.doStudying();
		
		sc.close();
		
	} // main

} // class
