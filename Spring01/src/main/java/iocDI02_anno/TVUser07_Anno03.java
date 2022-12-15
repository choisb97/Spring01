package iocDI02_anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

/*
 < TV의 의존성 처리 >
  => TV는 Speaker를 사용
  => Speaker Interface 적용 : SpeakerA, SpeakerB
  => 여러 개 중 선택 : 명시적 자동주입 
     @Autowired , @Qualifier("spA")

 ** Test1) : spA, spB 생성후 @Autowired 만 한 경우 
 ...UnsatisfiedDependencyException: Error creating bean with name 'tva': Unsatisfied 
 ....matching bean but found 2: spA,spB
 
 ** Test2) : spA만 생성 후 @Autowired만 한 경우 
 => Type 일치 하는경우가 한 개이기 때문에 정상실행

 => xml, @ 병행 가능
 Speaker의 생성은 xml에 <bean ..> 등록 후 Test
*/


interface Speakeri {
	
	void volumeUp();
	
	void volumeDown();
	
} // i_Speakeri


@Component("spa") // 다른 Test 위해 주석
class SpeakerA implements Speakeri {
	
	public SpeakerA () {
		System.out.println("< SpeakerA 기본 생성자 >");
	}
	
	public void volumeUp() {
		System.out.println("** SpeakerA volumeUp");
	}
	
	public void volumeDown() {
		System.out.println("** SpeakerA volumeDown");
	}
	
} // c_SpeakerA


@Component("spb") // 다른 Test 위해 주석
class SpeakerB implements Speakeri {
	
	public SpeakerB () {
		System.out.println("< SpeakerB 기본 생성자 >");
	}
	
	public void volumeUp() {
		System.out.println("** SpeakerB volumeUp");
	}
	
	public void volumeDown() {
		System.out.println("** SpeakerB volumeDown");
	}
	
} // c_SpeakerB

//--------------------------------------------------------------------------

// ** Speaker에 대한 의존성(Dependency) 해결
// 1) 고전적 방법 (직접 new)
@Component("tvs") // 다른 클래스 Test 위해 주석
class SsTVsi implements TV {
	
	private Speakeri speaker = new SpeakerA(); // new 생성했기 때문에 
	
	public SsTVsi() {
		System.out.println("< SsTVsi 기본 생성자 >");
	}
	
	@Override
	public void powerOn() {
		System.out.println("** SsTVsi powerOn");
	}
	
	@Override
	public void powerOff() {
		System.out.println("** SsTVsi powerOff");
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
@Component("tvl") // 다른 클래스 Test 위해 주석
class LgTVsi implements TV {
	
	@Autowired(required = false) // 다른 Test 위해 주석
	@Qualifier("spb") // Speaker가 여러 개이기 때문에 A, B 중 어떤 걸 쓸 건지 명시해줘야 됨. / 다른 Test 위해 주석
	private Speakeri speaker;
	private String color;
	private int price;
	
	public LgTVsi() {
		System.out.println("< LgTVsi 기본 생성자 >");
	}
	
	public LgTVsi(String color, int price, Speakeri speaker) {
		this.speaker = speaker;
		this.color = color;
		this.price = price;
		
		System.out.println("\n< LgTVsi 초기화 생성자 : color, price => " + color + price + " >");
	}
	
	@Override
	public void powerOn() {
		System.out.println("** LgTVsi powerOn");
	}
	
	@Override
	public void powerOff() {
		System.out.println("** LgTVsi powerOff");
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
@Component("tva") // 다른 클래스 Test 위해 주석
class AiTVsi implements TV {
	
	@Autowired(required = false) // 다른 Test 위해 주석
	@Qualifier("spa") // 다른 Test 위해 주석
	private Speakeri speaker;
	private String color;
	private int price;
	
	public AiTVsi() {
		System.out.println("< AiTVsi 기본 생성자 >");
	}
	
	public void setSpeaker(Speakeri speaker) {
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
		System.out.println("** AiTVsi powerOn : price, color => " + price + color);
	}
	
	@Override
	public void powerOff() {
		System.out.println("** AiTVsi powerOff");
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

public class TVUser07_Anno03 {

	public static void main(String[] args) {
		
		// 1. 스프링 컨테이너 구동(생성)
		AbstractApplicationContext sc = new
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
