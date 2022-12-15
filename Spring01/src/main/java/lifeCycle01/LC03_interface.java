package lifeCycle01;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

/*
 < ** Bean(객체)의 LifeCycle >
 => 객체생성 -> 사용 -> 소멸
 => 해당되는 특정시점에 자동실행 : xml, @, interface
 
 ** Test 3. interface
 => 각 시점별로 자동호출되는 메서드를 구현해놓은 interface 를 활용
 -> InitializingBean : afterPropertiesSet() -> 생성 직후 자동호출
 -> DisposableBean : destroy() -> 소멸 직전 자동호출
 => 실행 순서
    생성자 -> afterPropertiesSet()(생성 직후 자동호출) -> 사용...  
          -> 컨테이너 Close -> destroy()(소멸 직전 자동호출)
*/

@Component("lci")
class LifeCycleTestI implements InitializingBean, DisposableBean {
	
	@Override
	public void afterPropertiesSet() throws Exception { // 생성 직후 자동호출
		System.out.println("** LifeCycleTestI afterPropertiesSet()");
	}
	
	@Override
	public void destroy() throws Exception { // 소멸 직전 자동호출
		System.out.println("** LifeCycleTestI destroy()");
	}

	public LifeCycleTestI() {
		System.out.println("< LifeCycleTestI 기본 생성자 >");
	}
	
	public void list() {
		System.out.println("** LifeCycleTestI list()");
	} // list
	
	public void login() {
		System.out.println("** LifeCycleTestI login()");
	} // login
	
} // LifeCycleTestI

//=========================================================================================

public class LC03_interface {

	public static void main(String[] args) {

		AbstractApplicationContext sc = new
				GenericXmlApplicationContext("lifeCycle01/lc01.xml");
		
		LifeCycleTestI lc = (LifeCycleTestI)sc.getBean("lci"); // Type (LifeCycleTest)
		lc.login();
		lc.list();
		
		sc.close();
		
	} // main

} // class
