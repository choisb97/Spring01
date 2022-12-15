package spDispatcher;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.MemberService;
import vo.MemberVO;

public class C01_mList implements Controller {
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		// MemberList
		ModelAndView mv = new ModelAndView();
		
		MemberService service= new MemberService();
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
    	list = service.selectList();
    	if (list != null) {
    		mv.addObject("banana", list); // request.setAttribute("banana", list)와 동일
    		
    	} else {
    		mv.addObject("message", "** 출력 자료가 없습니다 **");
    		// request.setAttribute("message", "** 출력 자료가 없습니다 **")
    	}
    	
    	mv.setViewName("/member/memberList");
		return mv;
    	
	} // handleRequest
	
} // C01_mList
