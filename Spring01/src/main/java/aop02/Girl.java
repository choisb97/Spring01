package aop02;


// ** AOP 구현
// 1단계 : 핵심적 관심사항과 공통적 관심사항 분리
// => 핵심적 관심사항만 구현
// => 공통적 관심사항(Aspect) 분리 : 별도의 클래스로 분리 -> MyAspect.java

public class Girl implements Person {

	@Override
	public void doStudying() throws Exception {
		
		System.out.println("~~ 회원관리를 만듭니다. => 핵심적 관심사항");

		//if (new Random().nextBoolean()) { // 실패
		if (true) {
			throw new Exception("~~ 홀랑 다 날렸다 ㅠㅠㅠ !!! => 예외발생");
		} 
		
	} // doStudying

} // class
