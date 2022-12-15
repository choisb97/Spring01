package spDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class C04_mLogout implements Controller {
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,	HttpServletResponse response) {
		
		// ** session 인스턴스 정의 후 삭제하기
    	// => 매개변수: 없거나, true, false
    	// => false : session 이 없을때 null 을 return;
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession(false);
		
    	if (session != null)
    		session.invalidate();
    	
    	mv.addObject("message", "** 로그아웃 되었습니다");
    	mv.setViewName("home");

    	return mv;
		
	} // handleRequest

} // C04_mLogout
