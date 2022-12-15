package myDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;
import vo.MemberVO;

public class C02_mDetail implements MyController {
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		
		// MemberDetail
		MemberService service= new MemberService();
		
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("id"));
		
		vo = service.selectOne(vo);
		if (vo != null) {
			request.setAttribute("apple", vo);
			
		} else {
			request.setAttribute("message", "** " + request.getParameter("id") + "님의 자료는 존재하지 않습니다 **"); 
		}	
		
		return "/member/memberDetail"; // 최종 return 값은 viewName
	}

} // C02_mDetail
