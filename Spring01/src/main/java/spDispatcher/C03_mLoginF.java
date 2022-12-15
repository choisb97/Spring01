package spDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class C03_mLoginF implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,	HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("/member/loginForm");
		
		return mv;

	} // handleRequest

} // C03_mLoginF
