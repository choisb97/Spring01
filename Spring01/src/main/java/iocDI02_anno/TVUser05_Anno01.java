package iocDI02_anno;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;


/*
 < Annotation, @, 애너테이션 >
 => 컴파일러에게 알려줌

 **** @ Annotation 애너테이션 활용방법 *********************

 1. xml에 context 네임스페이스 추가 [NameSpaces] 이용
 => Component-scan 설정  : 
    <context:component-scan base-package="iocDI02_anno"/>
     
 2. 소스코드 
 => import 확인 : org.springframework.context.support.*;
 => 생성을 원하는 TV 클래스 위에 @Component("tv") 설정

 => @Component("tv")
    <bean ....> </bean> -> xml
    new 생성자() -> Java Code

 3. Test : 오류 메시지 확인하기
 => @ Test 1 생성 -> <bean ... />
 
 => @ Test 2 id명 미지정(둘다 beanname 없이) 
 => ...NoSuchBeanDefinitionException: 
     No bean named 'tv' is defined ....
     
 => @ Test 3 id명 중복되는 경우 (둘 다 beanname ("tv") 지정 )  
 => ...annotation.ConflictingBeanDefinitionException: ....
     ...non-compatible bean definition of same name and class ...
*/

interface TV {
	
	void powerOn();
	void powerOff();
	public void volumeUp();
	public void volumeDown();
	
} // TV

// @Component : .xml - <bean class="iocDI01_xml.LgTVi" id="tv" lazy-init="true"/> 와 같은 의미
//@Component("tv") // 다른 클래스 Test 위해 주석
class SsTVi implements TV {
	
	public SsTVi() {
		System.out.println("< SsTVi Default 생성자 >");
	}
	
	@Override
	public void powerOn() {
		System.out.println("** SsTVi powerOn");
	}
	
	@Override
	public void powerOff() {
		System.out.println("** SsTVi powerOff");
	}
	
	@Override
	public void volumeUp() {
		System.out.println("** SsTVi volumeUp");
	}
	
	@Override
	public void volumeDown() {
		System.out.println("** SsTVi volumeDown");
	}
	
} // SsTVi

// 필요한 경우에만 사용해도 됨.
//@Component // 다른 클래스 Test 위해 주석
// => 생성확인

// @Component("tv")
// => 동일 id 사용 Test => 같은 id를 쓰는 경우 에러
// ~~~.ConflictingBeanDefinitionException: Annotation-specified bean name 'tv' for bean class ~~~~~ same name ~~~
class LgTVi implements TV {
	
	public LgTVi() {
		System.out.println("< LgTVi Default 생성자 >");
	}
	
	@Override
	public void powerOn() {
		System.out.println("** LgTVi powerOn");
	}
	
	@Override
	public void powerOff() {
		System.out.println("** LgTVi powerOff");
	}
	
	@Override
	public void volumeUp() {
		System.out.println("** LgTVi volumeUp");
	}
	
	@Override
	public void volumeDown() {
		System.out.println("** LgTVi volumeDown");
	}
	
} // LgTVi

public class TVUser05_Anno01 {

	public static void main(String[] args) {

		// 1. 스프링_Container) 생성
		// => Spring_Container 구동(생성)                                  
		AbstractApplicationContext sc = new                       // package명/ 사용 권장
									GenericXmlApplicationContext("iocDI02_anno/app05.xml"); // 작업지시서
		
	//---------------------------------------------------------------------------------------------------------

		// 2. 필요한 객체를 전달받고 서비스 실행 
		TV tv = (TV)sc.getBean("tv"); // Object -> Type (TV)로 맞춰줌.
		
		if (tv != null) {
			tv.powerOn();
			tv.volumeUp();
			tv.volumeDown();
			tv.powerOff();
		} else
			System.out.println("!! TV 선택 오류 !!");

		sc.close();
		
	} // main

} // class
