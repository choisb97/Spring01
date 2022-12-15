package iocDI03_jc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/*
 < Java bean Comfiguration class를 이용한 DI >
 => Test02 : 스피커 1개 사용 TV
 => 생성자를 이용한 주입 & JC에서 xml 병행 사용
 
 < JC에서 xml 병행 사용하기 >
 => @ImportResource("iocDI03_jc/app09.xml")
 => AiTVs 생성은 xml 로 
*/

// < ** Test2) : JC에서 xml과 병행사용 >
@ImportResource("iocDI03_jc/app09.xml") // #47 xml 없이 Test하기 위해 주석
@Configuration // 기본
public class JavaConfig02 {

	// 1) 고전적 방법 (직접 new)
	@Bean 
	// => Bean을 생성하고, 인스턴스를 컨테이너에 전달해줌.
	public TV tvs() { // 메서드명이 getBean의 id와 동일해야 함.
		return new SsTVs();
	} // tvs
	
//-----------------------------------------------------------------------
	
	// 2) IOC/DI -> 생성자 주입
	@Bean
	public TV tvl() {
		//return new LgTVs("Pink", 1230000, new Speaker()); // new Speaker 바로 생성 가능
		return new LgTVs("Pink", 1230000, sp());
	} // tvl
	
	// => Speaker 생성
	public Speaker sp() { // Speaker Type으로 메서드 생성 후 return으로 사용 가능
		return new Speaker();
	} // sp
	
//-----------------------------------------------------------------------
	
	// 3) IOC/DI -> xml 병행 사용 Test
	// => xml로 생성 (app09.xml)
	// => xml 사용하지 않음 : setter를 이용해서 Speaker 전달은 안 됨. -> Test 하기 위해 #18 주석
	//    -> java.lang.NullPointerException: ... 오류 발생
	@Bean
	public TV tva() {
		return new AiTVs();
	} // tva
	
} // class
