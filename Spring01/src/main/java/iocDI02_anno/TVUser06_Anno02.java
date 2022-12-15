package iocDI02_anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

/*
 < TV의 의존성 처리 >
 => TV는 Speaker를 사용
 => 생성자 주입, setter 주입
 
 < @Autowired >
 => 멤버변수에 직접 생성된 주솟값을 전달
    그러므로 Speaker는 반드시 이미 생성되어 있어야 함.
 => 자동주입: 있으면 주입, 없으면 Exception 발생
 => 적용순위
 1) 주로 멤버변수 위에 선언하며,
    스프링 컨테이너는 해당변수의 타입을 체크하고,
    그 타입의 객체가 메모리에 존재하는지 확인하여
    해당 변수에 대입 해준다.
    
 2) 동일타입의 객체가 둘 이상이면 @Qualifier에 명시된 객체 주입
 
 3) 동일타입의 객체가 둘 이상이고 @Qualifier 없으면 
     id가 같은 객체 찾아 있으면 주입, 없으면 오류

 ** Test : Speaker 생성 안 되어있는 경우 오류메시지 확인
       => ...Injection of autowired dependencies failed;.....

 ** required 속성
    => true : 해당 타입의 등록된 빈이 없을 때 주입 시 Exception이 발생
    => false: 해당 타입의 등록된 빈이 없을 때 Exception이 발생하지 않음 
          ( 있으면 주입, 없으면 null )    
   
 1) @Autowired(required=true) : default 
       => BeanCreationException <- NoSuchBeanDefinitionException: .... as autowire...  
 2) @Autowired(required=false) 
       => 실행문에서 접근 시 NullPointerException 발생
*/

// Speaker가 하나이기 때문에 id 부여할 필요 없음. (생성만!)
//@Component // 주석걸면 오류 Test, 다른 클래스 Test 위해 주석
class Speaker {
	
	public Speaker () {
		System.out.println("< Speaker 기본 생성자 >");
	}
	
	public void volumeUp() {
		System.out.println("** Speaker volumeUp");
	}
	
	public void volumeDown() {
		System.out.println("** Speaker volumeDown");
	}
	
} // Speaker

//--------------------------------------------------------------------------

// 1) 고전적 방법 (직접 new)
//@Component("tvs") // 다른 클래스 Test 위해 주석
class SsTVs implements TV {
	
	private Speaker speaker = new Speaker(); // new로 직접 생성
	
	public SsTVs() {
		System.out.println("< SsTVs 기본 생성자 >");
	}
	
	@Override
	public void powerOn() {
		System.out.println("** SsTVs powerOn");
	}
	
	@Override
	public void powerOff() {
		System.out.println("** SsTVs powerOff");
	}
	
	@Override
	public void volumeUp() {
		speaker.volumeUp();
	}
	
	@Override
	public void volumeDown() {
		speaker.volumeDown();
	}
	
} // SsTVs

//--------------------------------------------------------------------------

// 2) IOC/DI -> 생성자 주입
//@Component("tvl") // 다른 클래스 Test 위해 주석
class LgTVs implements TV {
	
	/*
	 ** Speaker 생성 : Speaker에서 처리 -> @Component
	 => new Speaker() 처리 되었음.
	    그러므로 생성된 주소를 전달만 받겠다는 표시만 해주면 된다.
	    Speaker speaker = new Speaker(); 
	
	 @Autowired(required = false)
	 ** 자동주입
	 => Speaker는 생성되어있어야 함
	    Speaker speaker = new Speaker(); 구문의 "=" 역할
	 => 메모리에서 타이ㅂ이 일치하는 객체를 찾아 있으면 제공
	 => 없으면 오류 : UnsatisfiedDependencyException: Error creating bean wit...
	 => required = false
	    못 찾으면 null return
	    -> 그러므로 사용 구문(volumeUp, volumeDown)에서 NullPointerException 발생
	*/
	//@Autowired (required = false) // 다른 클래스 Test 위해 주석
	// @Autowired : 자동주입 받겠다는 의미
	private Speaker speaker; // 객체형
	private String color;
	private int price;
	
	public LgTVs() {
		System.out.println("< LgTVs 기본 생성자 >");
	}
	
	public LgTVs(String color, int price, Speaker speaker) {
		this.speaker = speaker;
		this.color = color;
		this.price = price;
		
		System.out.println("\n< LgTVs 초기화 생성자 : color, price => " + color + price + " >");
	}
	
	@Override
	public void powerOn() {
		System.out.println("** LgTVs powerOn");
	}
	
	@Override
	public void powerOff() {
		System.out.println("** LgTVs powerOff");
	}
	
	@Override
	public void volumeUp() {
		speaker.volumeUp();
	}
	
	@Override
	public void volumeDown() {
		speaker.volumeDown();
	}
	
} // LgTVs

//--------------------------------------------------------------------------

// 3) IOC/DI -> setter 주입
//@Component("tva") // 다른 클래스 Test 위해 주석
class AiTVs implements TV {
	
	//@Autowired (required = false) // 다른 클래스 Test 위해 주석
	private Speaker speaker;
	private String color;
	private int price;
	
	public AiTVs() {
		System.out.println("< AiTVs 기본 생성자 >");
	}
	
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	@Override
	public void powerOn() {
		System.out.println("** AiTVs powerOn : price, color => " + price + color);
	}
	
	@Override
	public void powerOff() {
		System.out.println("** AiTVs powerOff");
	}
	
	@Override
	public void volumeUp() {
		speaker.volumeUp();
	}
	
	@Override
	public void volumeDown() {
		speaker.volumeDown();
	}
	
} // AiTVs

//=============================================================================================================

public class TVUser06_Anno02 {

	public static void main(String[] args) {
		
		// 1. 스프링 컨테이너 구동(생성)
		AbstractApplicationContext sc = new   // package명/ 사용 권장
				GenericXmlApplicationContext("iocDI02_anno/app05.xml");
		
		// 2. 필요한 객체를 전달받고 서비스 실행
		System.out.println("\n** Test 1. 고전적 방법 (직접 new) **");
		
		TV tvs = (TV)sc.getBean("tvs");
		tvs.powerOn();
		tvs.volumeUp();
		tvs.volumeDown();
		tvs.powerOff();
		
	//----------------------------------------------------------------------------------------
		
		System.out.println("\n** Test 2. IOC/DI -> 생성자 주입 **");
		
		TV tvl = (TV)sc.getBean("tvl");
		tvl.powerOn();
		tvl.volumeUp();
		tvl.volumeDown();
		tvl.powerOff();

	//----------------------------------------------------------------------------------------

		System.out.println("\n** Test 3. IOC/DI -> setter 주입 **");
		
		TV tva = (TV)sc.getBean("tva");
		tva.powerOn();
		tva.volumeUp();
		tva.volumeDown();
		tva.powerOff();
		
		sc.close();
		
		System.out.println("\n< Program 종료 >");
		
	} // main

} // class
