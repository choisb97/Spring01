package aop02;


// ** AOP 구현
// 1단계 : 핵심적 관심사항과 공통적 관심사항 분리
// => 핵심적 관심사항만 구현
// => 공통적 관심사항(Aspect) 분리 : 별도의 클래스로 분리 -> MyAspect.java

public class Boy implements Person {

	@Override
	public void doStudying() throws Exception {

		System.out.println("~~ 게시판을 만듭니다. => 핵심적 관심사항");

		// ** Test를 위해 늘 성공으로 처리
		// => 늘 false 값이 되도록 조건을 설정
		//if (new Random().nextBoolean()) { // 실패
		if (1 == 2) { // false : 항상 성공으로 처리되도록
			throw new Exception("~~ 홀랑 다 날렸다 ㅠㅠㅠ !!! => 예외발생");
		} 
			
	} // doStudying

} // class
