package aop03;

import org.aspectj.lang.ProceedingJoinPoint;

/*
 ** 횡적(공통)관심사항 ( cross concerns => Aspect ) 구현
 => Boy, Girl : 핵심 관심사항 (core concerns)만 구현하면 됨.
 => 횡적(공통) 관심사항 과 핵심 관심사항의 연결 방법 xml, @ 방식

 ** xml 방식의 공통적 관심 사항 구현 2.
 => pointcut : 매개변수, return 값 없음
 => Around 메서드 1개로 구현
    Before, After_returning, After_throwing, After을 1개의 메서드에서 try~catch~finally 처리 

 => Around 메서드 특징
	-> Aspect와 Pointcut의 모든 Joinpoint를 아우르는 연결고리 
	-> 반드시 ProceedingJoinPoint 타입을 인자로 사용하여 pointcut을 처리 (그렇지 않으면 오류)
	   joinPoint.proceed();로 핵심적 관심사항을 처리 함

 ** ProceedingJoinPoint : JoinPoint를 상속 했으며 proceed() 메서드를 가짐
 => JoinPoint (I) -> ProceedingJoinPoint (I) -> JoinPointImpl
 => 예외상황 처리 시에 Throwable 사용해야 됨.
    계층도 : Object -> Throwable -> Error, Exception -> RuntimeException (unChecked)

 ** JoinPoint 
 => PointCut을 지원해주는 클래스 (다양한 지원 메서드를 가지고 있음-> 매개변수 전달 등.. )
 => 핵심적 관심사항으로 들어가는 모든 데이터 (before를 통해) 사항을 가지고 있으며 처리할 수 있도록 해줌
*/
public class MyAspect {
	
	public void myAround(ProceedingJoinPoint joinpoint) { 
		
		// Before: 핵심적 관심사항 수행 이전
		System.out.println("~~ 프로젝트 과제를 해야 됩니다 => Before");
		
		try {
			// PointCut : 핵심적 관심사항 수행
			// => Around 메서드의 매개변수를 통해 전달받아 수행
			joinpoint.proceed();
			// => Throwable로 예외처리를 해야함
			//    Throwable - Exception
			
			// After_returning : 핵심적 관심사항 의 정상종료
			System.out.println("~~ 실행이 잘 된다 ㅎㅎㅎ => 핵심적 관심사항 정상종료");
			
		} catch (Throwable e) {
			// After_throwing : 핵심적 관심사항의 비정상종료 
			System.out.println("~~ 밤새워 복구한다 zzz => 예외발생으로 핵심적 관심사항 비정상 종료 ");
			
			// => 발생된 예외를 Throwable로 처리(처리 & 종료) 했으므로 main까지 전달되지 않음 (확인 후 Test)
			// => main으로 전달하려면 예외 발생시켜주면 됨.
			// throw new Exception(e) ;  // Exception은 Checked -> try~catch 처리해야 함
			throw new RuntimeException(e); // unChecked
			
		} finally {
			// After: 최종(아무튼) 종료 -> 정상 종료이건, 비정상 종료이건 무조건 시행
			System.out.println("~~ finally : 무조건 제출한다 => 아무튼 종료");
		}
		
	} //myAround

} // class
