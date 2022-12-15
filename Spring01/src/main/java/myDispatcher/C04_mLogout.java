package myDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class C04_mLogout implements MyController {
	
	@Override
	public String handleRequest(HttpServletRequest request,	HttpServletResponse response) {
		
		// ** session 인스턴스 정의 후 삭제하기
    	// => 매개변수: 없거나, true, false
    	// => false : session 이 없을때 null 을 return;
		HttpSession session = request.getSession(false);
		
    	if (session != null)
    		session.invalidate();
    	
    	String uri = "home";
    	
    	request.setAttribute("message", "** 로그아웃 되었습니다"); 
    	
		return uri; // 최종 return 값은 viewName
		
	} // handleRequest

} // class
