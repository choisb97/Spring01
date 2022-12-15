package lifeCycle01;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

/*
 < ** Bean(객체)의 LifeCycle >
 => 객체생성 -> 사용 -> 소멸
 => 해당되는 특정시점에 자동실행 : xml, @, interface

 ** Test 2. Annotation, @
 => @PostConstruct(생성 직후) , @PreDestroy(소멸 직전)
 => 실행 순서
    생성자 -> @PostConstruct(생성 직후 자동호출) -> 사용...  
         -> 컨테이너 Close -> @PreDestroy(소멸 직전 자동호출)
*/


//@Component("lca") // 다른 클래스 test 위해 주석
// getBean id("lca")랑 동일하게
class LifeCycleTestA {

	public LifeCycleTestA() {
		System.out.println("< LifeCycleTestA 기본 생성자 >");
	}
	
	@PostConstruct // 생성 직후 자동호출
	public void beginA() {
		System.out.println("** LifeCycleTestA begin()");
	} // beginA
	
	@PreDestroy // 소멸 직전 자동호출
	public void endA() {
		System.out.println("** LifeCycleTestA end()");
	} // endA
	
	public void list() {
		System.out.println("** LifeCycleTestA list()");
	} // list
	
	public void login() {
		System.out.println("** LifeCycleTestA login()");
	} // login
	
} // LifeCycleTestA

//==================================================================================================

public class LC02_anno {

	public static void main(String[] args) {

		AbstractApplicationContext sc = new
				GenericXmlApplicationContext("lifeCycle01/lc01.xml");
		
		LifeCycleTestA lc = (LifeCycleTestA)sc.getBean("lca"); // Type (LifeCycleTest)
		lc.login();
		lc.list();
		
		sc.close();
		
	} // main

} // class
