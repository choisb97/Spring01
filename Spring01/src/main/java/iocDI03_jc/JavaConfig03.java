package iocDI03_jc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/*
 < Java bean Comfiguration class를 이용한 DI >
 => Test03 : 스피커 2개 중 선택
 => 생성자를 이용한 주입.. & JC에서 @, xml 병행 사용
 
 *** JC와 @ 병행 사용
 *** JC, @, xml 병행 사용
 => JC 내에서 xml로 생성된 객체를 직접 사용하는 것은 허용되지 않음. 
    단, User 클래스에서는 사용 가능

 ** 실습
 => SsTVsi, Speaker는 xml로 생성
 => LgTVsi, AiTVsi는 JC의 @Bean으로 생성
 => LgTVsi (Speaker 생성자주입) 
    AiTVsi (Speaker @Autowired) 
*/

// ** Test3) : JC에서 xml, User클래스의 @ 병행사용
@ImportResource("iocDI03_jc/app10.xml")
@Configuration
public class JavaConfig03 {
	
	// 1) xml과 병행사용 Test
	// => SsTVsi는 xml에서 생성(app10.xml)
	
//----------------------------------------------------------------
	
	// 2) 생성자 주입 : LgTVsi
	@Bean
	public TV tvl() {
		return new LgTVsi("Blue", 770000, spb());
		// return new LgTVsi("Blue", 770000, new SpeakerB());
	} // tvl
	
	//@Bean // app10.xml Test 위해 주석
	public Speakeri spa() {
		return new SpeakerA();
	} // spa
	
	public Speakeri spb() { 
	// spb() : @Autowired가 없기 때문에 @Bean 필요 없음.
		return new SpeakerB();
	} // spb
	
//----------------------------------------------------------------
	
	// 3) xml, @ 과 병행 사용 Test ( AiTVsi )
	// => Speaker에 @Autowired(required = false) 적용 Test
	// => @Bean : Bean을 생성하고, 인스턴스를 컨테이너에 전달해줌.
	
	// 3-1) JC에서 SpeakerA 생성 후 확인 (@Bean 적용 전, 후 비교 #40)
	// -> @Bean 적용 전 : User 클래스로 전달 안 됨.
	// -> 위 spb()는 JC 코드 내에서만 사용하기 때문에 @Bean은 필요 없음.
	// -> @Bean 적용 후 : User 클래스로 전달 잘 됨.
	
	// 3-2) xml에서 SpeakerA 생성 후 확인 (app10.xml Test 위해 #40 주석)
	@Bean
	public TV tva() {
		return new AiTVsi();
	} // tva

} // class
