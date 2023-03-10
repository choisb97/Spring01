package aop02;

/*
 < ** 횡적(공통) 관심사항 (cross concerns => Aspect ) 구현 >
 => Boy, Girl : 핵심 관심사항 (core concerns) 만 구현하면 됨.
 => 횡적(공통) 관심사항과 핵심관심사항의 연결 방법 xml, @ 방식

 ** xml 방식의 공통적 관심 사항 구현 1.
 => pointcut : 매개변수, return 값 없음  
 => 개별 advice 메서드 구현
    Before, After_returning, After_throwing, After 

 ** 용어정리
  Target : 핵심사항(Core concerns)이 구현된 객체 : Boy, Girl
  JoinPoint : 공통 관심사항이 적용될 수 있는 지점, 클라이언트가 호출하는 모든 비즈니스 메서드   
              (ex:메소드 호출 시, 객체 생성 시 등)
  Pointcut : JoinPoint 중 실제 공통사항이 적용될 대상 : doStudying()  
  Advice : 공통 관심사항(Cross-Cutting) 구현 코드 + 적용시점
         : 시점별 메서드들 (myBefore() ....)
         : 적용시점 (핵심 로직 실행 전 / 후, 정상 종료 후, 비정상 종료 후 등 )
  Aspect : Advice + Pointcut

 ** pom.xml 설정 
  1) AspectJ  
  2) AspectJ Weaver : AOP 실습 시에 반드시 추가해줄 것 
     => weaver가 AOP에서 advice를 핵심기능에 적용하는 설정 파일
*/

public class MyAspect {
	
	// Before : 핵심적 관심사항 수행 이전
	public void myBefore() {
		System.out.println("~~ 프로젝트 과제를 해야합니다 => Before");
	}

	// After_returning : 핵심적 관심사항의 정상종료
	public void myAfter_returning() {
		System.out.println("~~ 실행이 잘 된다 ㅎㅎㅎ => 핵심적 관심사항 정상종료");
	}

	// After_throwing : 핵심적 관심사항의 비정상 종료
	public void myAfter_throwing() {
		System.out.println("~~ 밤새워 복구한다 zzz => 예외발생으로 핵심적 관심사항 비정상 종료");
	}

	// After : 핵심적 관심사항의 종료
	// => 정상 종료이건, 비정상 종료이건 무조건 시행.
	public void myAfter() {
		System.out.println("** finally : 무조건 제출한다 => 아무튼 종료");
	}
	
} // class
