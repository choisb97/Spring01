package iocDI01_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/*
 < 스프링이 제공하는 BeanFactory >
 => 스프링 컨테이너
 => AbstractApplicationContext 와 GenericXmlApplicationContext 계층도 
 => Object -> DefaultResourceLoader (C) -> AbstractApplicationContext (A) 
       -> GenericApplicationContext (C) -> GenericXmlApplicationContext (C) 

 public abstract class AbstractApplicationContext extends DefaultResourceLoader
       implements ConfigurableApplicationContext, DisposableBean {....

 public class GenericXmlApplicationContext extends GenericApplicationContext {...

 => 컨테이너는 xml(설정파일), @, JavaCode(JavaConfig) 등의 
    전달사항을 통해 요구하는 클래스를 생성, 보관, 제공

 ** xml (설정파일) 
*/

public class TVUser02 {

	public static void main(String[] args) {

		// 1. 콩공장(Java_BeanFactory, 스프링_Container) 생성
		// => Spring_Container 구동(생성)                                  
		AbstractApplicationContext sc = new              // package명/ 사용 권장
							GenericXmlApplicationContext("iocDI01_xml/app02.xml"); // 작업지시서
							// => 설정파일(요구사항 목록)을 매개변수로 전달
							// => GenericXmlApplicationContext("app02.xml");
						    //    xml문을 "src/main/resources"에 두면 패키지는 생략가능.
		
	//---------------------------------------------------------------------------------------------------------

		// 2. 필요한 객체를 전달받고 서비스 실행 
	    // => 필요한 객체를 설정파일(app02.xml)을 이용해서 Spring 컨테이너에게 요청 
	            
        // => Spring 컨테이너는 getBean 메서드를 실행해서 해당 객체를 제공
        // => 실시간으로 소스코드 수정 없이 전달받음
		TV tv = (TV)sc.getBean("tv"); // Object -> Type (TV)로 맞춤.
		// getBean("xml - bean class=의 id")
		
		if (tv != null) { // null인지 확인
			tv.powerOn();
			tv.volumeUp();
			tv.volumeDown();
			tv.powerOff();
		} else
			System.out.println("!! TV 선택 오류 !!");
		
	//---------------------------------------------------------------------------------------------------------
		
		// 3. singleton(싱글톤) Test
		// => 스프링 프레임워크의 모든 작업은 싱글톤을 기본으로 함.
		// => 싱글톤(한 개의 인스턴스만 허용하는 것) 적용 Test
		// => 설정파일의 scope 속성에 "prototype" / "singleton" (default는 싱글톤)
		// => 생성자 실행횟수와 아래의 주솟값 확인해보기
		//    SsTVi 2개, LgTVi 2개 
		TV tvl = (TV)sc.getBean("tv");
		TV tvs1 = (TV)sc.getBean("tvs");
		TV tvs2 = (TV)sc.getBean("tvs");
		
		// Test 3-1) Default : 모두 싱글톤
		// => 모두 생성자는 한 번(1회만) 실행, 클래스(TV) 별로 동일 주솟값
		
		// Test 3-2) prototype / singleton 모두 지정 후 비교
		// => SsTVi는 scope="prototype"으로 지정
		//    SsTVi는 생성자가 두 번, 주솟값도 서로 다름.
		System.out.println("\n< singleton(싱글톤) Test >");
		System.out.println("** tv(Lg) => " + tv);
		System.out.println("** tvl(Lg) => " + tvl);
		
		System.out.println("\n** tvs1 => " + tvs1); // prototype
		System.out.println("** tvs2 => " + tvs2); // prototype

		sc.close();
		
		// 우클릭 - Run As - Java Application
		
	} // main

} // class
