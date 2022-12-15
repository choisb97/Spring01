package memberDI;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


/*
 < MemberList 출력 >
 => xml, @ 활용

 ** Test 1. MemberService를 주입받아 처리
 ** Test 2. MemberService는 MemberDAO를 주입받아 처리
*/


//==========================================================================================================

public class MemberUser {

	public static void main(String[] args) {
		
		// 1. 스프링 컨테이너 구동(생성)
		AbstractApplicationContext sc = new
				GenericXmlApplicationContext("memberDI/member.xml");
		
		// 2. 필요한 객체를 전달받음
		System.out.println("\n< Test 1. MemberService를 주입받아 List 출력 >");
		
		MemberService service = (MemberService)sc.getBean("ms"); // memberservice
		
		// 3. 서비스 실행
		List<MemberVO> list = service.selectList();
		
		for (MemberVO m : list) {
			System.out.println(m);
		} // foreach
		
		sc.close();
		
	} // main

} // class
