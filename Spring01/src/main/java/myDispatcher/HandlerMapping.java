package myDispatcher;

import java.util.HashMap;
import java.util.Map;

/*
 < ** 요청에 대한 ServiceController를 생성 후 제공 >
 => 싱글톤 패턴 객체
 => 요청명 대 ServiceController는 Map으로 처리 
*/

public class HandlerMapping {
	
	// ** Map 정의
	private Map<String, MyController> mappings; // MyController 인터페이스에 있는 것들 모두 사용 가능
	
	// ** 생성자
	// => * 싱글톤 : private
	// => Map 초기화
	private HandlerMapping() {
		mappings = new HashMap<String, MyController>();
		
		mappings.put("/mlist", new C01_mList());
		mappings.put("/mdetail", new C02_mDetail());
		mappings.put("/loginf", new C03_mLoginF());
		mappings.put("/login", new C03_mLogin());
		mappings.put("/logout", new C04_mLogout());
	}
	
	
	// ** 싱글톤 패턴
	// => 생성자를 private로, 메서드를 이용하여 생성해서 전달
	private static HandlerMapping instance = new HandlerMapping(); // new 생성은 한 번만 해야하기 때문에 private 사용
	
	public static HandlerMapping getInstance() {
		
		return instance;
	}
	// public static HandlerMapping getInstance() { return new HandlerMapping(); }
	// => 메서드 호출 시 마다 생성되므로 싱글톤 적용 안 됨. 주의!
	
	
	// ** getController
	public MyController getController(String mappingName) {
		return mappings.get(mappingName);
	}
	
} // class
