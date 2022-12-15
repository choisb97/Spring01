package iocDI01_xml;

// ** Test 1. 절차지향
class SsTV {
	
	public void turnOn() {
		System.out.println("** SsTV turnOn!!");
	}
	
	public void turnOff() {
		System.out.println("** SsTV turnOff!!");
	}
	
	public void soundUp() {
		System.out.println("** SsTV soundUp!!");
	}
	
	public void soundDown() {
		System.out.println("** SsTV soundDown!!");
	}
	
} // SsTV


class LgTV {
	
	public void powerOn() {
		System.out.println("** LgTV powerOn!!");
	}
	
	public void powerOff() {
		System.out.println("** LgTV powerOff!!");
	}
	
	public void volumeUp() {
		System.out.println("** LgTV volumeUp!!");
	}
	
	public void volumeDown() {
		System.out.println("** LgTV volumeDown!!");
	}

} // LgTV

//-------------------------------------------------------------------------------------

// ** Test 2. 객체지향 : 다형성
interface TV {
	
	void powerOn();
	void powerOff();
	public void volumeUp();
	public void volumeDown();
	
} // TV

class SsTVi implements TV {
	
	public SsTVi() {
		System.out.println("< SsTVi Default 생성자 >");
	}
	
	@Override
	public void powerOn() {
		System.out.println("** SsTVi powerOn");
	}
	
	@Override
	public void powerOff() {
		System.out.println("** SsTVi powerOff");
	}
	
	@Override
	public void volumeUp() {
		System.out.println("** SsTVi volumeUp");
	}
	
	@Override
	public void volumeDown() {
		System.out.println("** SsTVi volumeDown");
	}
	
} // SsTVi


class LgTVi implements TV {
	
	public LgTVi() {
		System.out.println("< LgTVi Default 생성자 >");
	}
	
	@Override
	public void powerOn() {
		System.out.println("** LgTVi powerOn");
	}
	
	@Override
	public void powerOff() {
		System.out.println("** LgTVi powerOff");
	}
	
	@Override
	public void volumeUp() {
		System.out.println("** LgTVi volumeUp");
	}
	
	@Override
	public void volumeDown() {
		System.out.println("** LgTVi volumeDown");
	}
	
} // LgTVi

//-------------------------------------------------------------------------------------

// ** Test 3. Factory 패턴 (IOC/DI의 입문)
class BeanFactory {
	
	// return Type : TV     request
	public TV getBean(String req) {
		
		if (req != null && req.equals("ss"))
			return new SsTVi();
		
		else if (req != null && req.equals("lg"))
			return new LgTVi();
		
		else
			return null;
			
	} // getBean
	
} // BeanFactory

//===========================================================================================================

public class TVUser01 { // 사용자 클래스

	public static void main(String[] args) {
		
		// ** Test 1. 절차지향
		// => TV 교체 필요 : 완전히 재작성
		System.out.println("< Test 1. 절차지향 >");

//		SsTV tv = new SsTV();
//		tv.turnOn();
//		tv.soundUp();
//		tv.soundDown();
//		tv.turnOff();
		
		LgTV tv = new LgTV();
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
	//-------------------------------------------------------------------------------------
		
		// ** Test 2. 객체지향 : 다형성
		// => 관련성이 없는 두 객체를 하나의 인터페이스로 묶어줌.
		// => TV 교체 필요
		//    우측의 클래스만 교체
		// => 단, 소스코드의 수정이 필요함
		System.out.println("\n< Test 2. 객체지향 : 다형성 >");
		
		// 우측 : 조상 = 좌측 : 후손
		TV tvi = new SsTVi();
		//TV tvi = new LgTVi();
		tvi.powerOn();
		tvi.volumeUp();
		tvi.volumeDown();
		tvi.powerOff();
		
	//------------------------------------------------------------------------------------------------------------
		
		// ** Test 3. 소스코드 수정 없이 실시간 객체 교체
		// => 객체를 생성해서 교체해 줄 역할자 필요 : Factory 패턴 (IOC/DI의 입문)
		// => user 클래스의 요구사항(요청하는(필요한) 클래스, 의존성_Dependency)을 Factory에게 전달할 방법 필요.
		// => 세 가지 : 1) xml // 2) @ // 3) JavaConfig(JavaCode)
		System.out.println("\n< Test 3. Factory 패턴 (IOC/DI의 입문) >");
		
		BeanFactory bf = new BeanFactory();
		
		TV tvf = bf.getBean(args[0]);
		// => 실시간으로 소스코드 수정 없이 요청 전달 -> main의 매개변수(args[]) 활용
		
		if (tvf != null) { // tvf가 null이 아니라면(null인지 확인용)
			tvf.powerOn();
			tvf.volumeUp();
			tvf.volumeDown();
			tvf.powerOff();
		} else
			System.out.println("!! TV 선택 오류 !!");
		
		// * 우클릭 - Run As - Run Configurations - Arguments - Program Arguments (ex : ss, lg, aa)

	} // main

} // class
