package iocDI03_jc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/*
 < Java bean configuration class를 이용한 DI >
 => Bean 컨테이너 : AnnotationConfigApplicationContext 사용
 => Test02 : 스피커 1개 사용 TV
*/


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

// ** TV는 Speaker를 사용 ( TV 는 Speaker에 대한 의존관계성립 )
// 1) 고전적 방법 (직접 new)
class SsTVs implements TV {
	
	private Speaker speaker = new Speaker(); // 직접 생성
	
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
class LgTVs implements TV {
	
	private Speaker speaker;
	private String color;
	private int price;
	
	public LgTVs() {
		System.out.println("< LgTVs 기본 생성자 >");
	}
	
	public LgTVs(String color, int price, Speaker speaker) {
		this.speaker = speaker;
		this.color = color;
		this.price = price;
		
		System.out.println("< LgTVs 초기화 생성자 : color, price => " + color + price + " >");
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

// 3) IOC/DI -> xml 병행 사용 Test
class AiTVs implements TV {
	
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

public class TVUser09_JC02 {

	public static void main(String[] args) {
		
		// 1. 스프링 컨테이너 구동(생성)
		AbstractApplicationContext sc = new
				AnnotationConfigApplicationContext(JavaConfig02.class);
		
	//----------------------------------------------------------------------------------------
		
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

		System.out.println("\n** Test 3. IOC/DI -> xml 병행 사용 Test **");
		
		TV tva = (TV)sc.getBean("tva");
		tva.powerOn();
		tva.volumeUp();
		tva.volumeDown();
		tva.powerOff();
		
		sc.close();
		
		System.out.println("\n< Program 종료 >");
		
	} // main

} // class
