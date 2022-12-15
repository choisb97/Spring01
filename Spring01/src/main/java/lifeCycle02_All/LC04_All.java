package lifeCycle02_All;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


/*
 < ** Bean(객체)의 LifeCycle >
 => 객체생성 -> 사용 -> 소멸
 => 해당되는 특정 시점에 자동 실행 : xml, @, interface
 
 ** Test 4. All
 => 실행순서 : 생성 -> @ Annotation -> Interface -> xml 
 				-> 컨테이너 close, 객체 소멸 시작 -> @ Annotation -> Interface -> xml 
*/


class LifeCycleTest implements InitializingBean, DisposableBean {

	public LifeCycleTest() {
		System.out.println("< LifeCycleTest 기본 생성자 >");
	}
	
	// 1. xml 적용 메서드
	public void begin() {
		System.out.println("** LifeCycleTest_x begin()");
	} // begin
	
	public void end() {
		System.out.println("** LifeCycleTest_x end()");
	} // end
	
//-------------------------------------------------------------------------

	// 2. @ 적용 메서드
	@PostConstruct
	public void beginA() {
		System.out.println("** LifeCycleTest_a beginA()");
	} // beginA
	
	@PreDestroy
	public void endA() {
		System.out.println("** LifeCycleTest_a endA()");
	} // endA
	
//-------------------------------------------------------------------------
	
	// 3. interface 적용 메서드
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("** LifeCycleTest_i afterPropertiesSet()");
	}
	
	@Override
	public void destroy() throws Exception {
		System.out.println("** LifeCycleTest_i destroy()");
	}
	
	
	public void list() {
		System.out.println("** LifeCycleTest list()");
	} // list
	public void login() {
		System.out.println("** LifeCycleTest login()");
	} // login
	
} // LifeCycleTest

//==========================================================================================

public class LC04_All {

	public static void main(String[] args) {

		AbstractApplicationContext sc = new
				GenericXmlApplicationContext("lifeCycle02_All/lc_All.xml");
		
		LifeCycleTest lc = (LifeCycleTest)sc.getBean("lc"); // Type (LifeCycleTest)
		lc.login();
		lc.list();
		
		sc.close();
		
	} // main

} // class
