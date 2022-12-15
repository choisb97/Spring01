package iocDI03_jc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


/*
 < Java bean configuration class를 이용한 DI >
 => Bean 컨테이너 : AnnotationConfigApplicationContext 사용
 => Test03 : 스피커 2개 사용 TV
 */


interface Speakeri {

	void volumeUp();

	void volumeDown();

} // i_Speakeri


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

// ** TV는 Speaker를 사용 ( TV 는 Speaker에 대한 의존관계성립 )
// 1) 고전적 방법 (직접 new)
class SsTVsi implements TV {

	private Speakeri speaker = new SpeakerA();

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
class LgTVsi implements TV {

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

		System.out.println("< LgTVsi 초기화 생성자 : color, price => " + color + price + " >");
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

// 3) IOC/DI : JC와 @ Test
// => TV, SpeakerB는 JC에서 생성하고
//    @Autowired로 주입받음.
class AiTVsi implements TV {

	@Autowired(required = false)
	@Qualifier("spa")
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

public class TVUser10_JC03 {

	public static void main(String[] args) {

		// 1. 스프링 컨테이너 구동(생성)
		AbstractApplicationContext sc = new
				AnnotationConfigApplicationContext(JavaConfig03.class);

	//----------------------------------------------------------------------------------------

		// 2. 필요한 객체를 전달받고 서비스 실행
		System.out.println("\n** Test 1. xml로 생성  (SsTVsi) **");

		TV tvs = (TV)sc.getBean("tvs");
		tvs.powerOn();
		tvs.volumeUp();
		tvs.volumeDown();
		tvs.powerOff();

	//----------------------------------------------------------------------------------------

		System.out.println("\n** Test 2. IOC/DI -> 생성자 주입 (AiTVsi) **");

		TV tvl = (TV)sc.getBean("tvl");
		tvl.powerOn();
		tvl.volumeUp();
		tvl.volumeDown();
		tvl.powerOff();

	//----------------------------------------------------------------------------------------

		System.out.println("\n** Test 3. IOC/DI -> @Autowired Speaker **");

		TV tva = (TV)sc.getBean("tva");
		tva.powerOn();
		tva.volumeUp();
		tva.volumeDown();
		tva.powerOff();

		sc.close();

		System.out.println("\n< Program 종료 >");

	} // main

} // class
