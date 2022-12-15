package iocDI03_jc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 < Java Bean Configuration class를 이용한 DI >
 => Test04 : 스피커 두 개 중 선택
 => xml에서 JC 파일 import, @ 병행 사용 
 => JC에서는 LgTVsi, AiTVsi, SpeakerB 생성
*/

@Configuration
public class JavaConfig04 {

	@Bean
	public TV tvl() {
		return new LgTVsi("Green", 550000, new SpeakerB());
	} // tvl
	
	@Bean
	public TV tva() {
		return new AiTVsi();
	} // tva
	// @Autowired Test
	
} // class
