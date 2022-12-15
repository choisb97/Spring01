package iocDI01_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/*
 < TV의 의존성 처리 >
  => TV는 Speaker를 사용
  => 생성자 주입, setter 주입
 
 < 의존성 해결 >
  1) 고전적 방법 (직접 new)
  2) IOC/DI -> 생성자 주입 
  3) IOC/DI -> setter 주입
  => setter 보다는 생성자 주입을 권장함 (최초 1회 적용 후 변경 불가능하기 때문)

 ** IOC: 제어의 역전 (외부에서 Control)
 ** DI : 주입 받음으로 해결 
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

public class TVUser03_Speaker {

	public static void main(String[] args) {
		
		// 1. 스프링 컨테이너 구동(생성)
		AbstractApplicationContext sc = new  // package명/ 사용 권장
				GenericXmlApplicationContext("iocDI01_xml/app03.xml");
		
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
		
		// ** xml 구문과 비교 **
		//TV tvlg = new LgTVs("Yellow", 12345000, new Speaker());

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
