package lifeCycle01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/*
 < ** Bean(객체)의 LifeCycle >
 => 객체생성 -> 사용 -> 소멸
 => 해당되는 특정 시점에 자동 실행 : xml, @, interface
 
 ** Test 1. xml
 => xml에 해당 시점 별 속성에 등록
    init-method="begin" destroy-method="end"
 => 실행 순서
    생성자 -> init-method(생성 직후 자동호출) -> 사용....
      -> 컨테이너 Close -> destroy-method(소멸 직전 자동호출)
*/

class LifeCycleTest {

	public LifeCycleTest() {
		System.out.println("< LifeCycleTest 기본 생성자 >");
	}
	
	public void begin() {
		System.out.println("** LifeCycleTest begin()");
	} // begin
	
	public void end() {
		System.out.println("** LifeCycleTest end()");
	} // end
	
	public void list() {
		System.out.println("** LifeCycleTest list()");
	} // list
	
	public void login() {
		System.out.println("** LifeCycleTest login()");
	} // login
	
} // LifeCycleTest

//=======================================================================================================

public class LC01_xml {

	public static void main(String[] args) {

		AbstractApplicationContext sc = new
				GenericXmlApplicationContext("lifeCycle01/lc01.xml");
		
		LifeCycleTest lc = (LifeCycleTest)sc.getBean("lc"); // Type (LifeCycleTest)
		lc.login();
		lc.list();
		
		sc.close();
		
	} // main

} // class
