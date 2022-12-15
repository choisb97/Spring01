package spDispatcher;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.MemberService;
import vo.MemberVO;

/*
 < ** 스프링MVC : 스프링 DispatcherServlet 사용 2단계 >
 => IOC/DI 적용한 Annotation 기반의 컨트롤러

 ** @Component (bean 생성 @) 의 세분화 
 => 스프링 프레임워크에서는 클래스들을 기능별로 분류하기 위해 @을 추가함.
 => * @Controller : 사용자 요청을 제어하는 Controller 클래스
                    DispatcherServlet이 해당객체를 Controller객체로 인식하게 해 줌.    
 => * @Service : 비즈니스로직을 담당하는 Service 클래스
 => * @Repository : DB 연동을 담당하는 DAO 클래스
                    DB 연동과정에서 발생하는 예외를 변환해주는 기능 추가 

 *** @Controller 사용함
 => implements Controller를 대신함.
 => 아래와 관련된 import 삭제해야 함.
    public class LoginController implements Controller {
 => 메서드명, 매개변수, 리턴값에 자유로워짐 
    -> 메서드명은 handleRequest 사용 안 해도 됨
    -> 매개변수 다양한 정의 가능 (메서드내에서 생성할 필요 없어짐)
    -> 리턴값은 ModelAndView 또는 String 가능 함

 => 요청별 Controller를 한 클래스내에 여러 메서드로 구현할 수 있게 됨  
 => 요청명과 메서드 연결은 @RequestMapping으로 
    Controller 클래스 코드내에서 직접 매핑
 => 그러므로 xml에 설정한 SimpleUrlHandlerMapping 부분은 필요 없음
 => 그러므로 대부분 Table 별로 Controller 클래스를 작성함.  
 => Controller의 Mapping 메서드 장점
    Mapping 메서드의 매개변수로 지정된 객체에 request_ParameterName과 일치하는 컬럼(setter)존재하면 자동으로 set 해줌
------------------------------------------------------------------------------------------------------------------------------------------
 */

@Controller
public class C99_MemberController {
	
	// ** 전역변수 활용
	@Autowired
	MemberService service;
	
	//ModelAndView mv = new ModelAndView();
	// => 전역변수로 정의하면 message가 사라지지 않고 계속 남아있게 됨.
	//  -> request.setAttribute는 자동삭제 되지만 ModelAndView는 일반 객체이므로 전역으로 정의하면 addObject 값이 남아있음

	@RequestMapping(value = "/mlist")
	public ModelAndView mlist(HttpServletRequest request, HttpServletResponse response, ModelAndView mv) {

		// MemberList
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

	} // mlist

//-----------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/mdetail")
	//public ModelAndView mdetail(HttpServletRequest request, HttpServletResponse response) {
	public ModelAndView mdetail(HttpServletRequest request, HttpServletResponse response, MemberVO vo, ModelAndView mv) {
	// => Mapping 메서드
	//    : 매개변수로 지정된 객체에 request_ParameterName과 일치하는 컬럼(setter)이 존재하면 자동으로 set
	//      request.getParameter("id") 필요없게 됨. -> type 변환도 알아서 척척
		
		// MemberDetail
		//MemberVO vo = new MemberVO(); // 매개변수로 직접 넣으면 따로 생성하지 않아도 됨
		
		vo.setId(request.getParameter("id"));

		vo = service.selectOne(vo);
		if (vo != null) {
			mv.addObject("apple", vo);

		} else {
			mv.addObject("message", "** " + request.getParameter("id") + "님의 자료는 존재하지 않습니다 **");
		}	

		mv.setViewName("/member/memberDetail");
		return mv;
	} // mdetail

//-----------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/loginf")
	public ModelAndView loginf(HttpServletRequest request,	HttpServletResponse response, ModelAndView mv) {

		mv.setViewName("/member/loginForm");

		return mv;

	} // loginf

//-----------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request,	HttpServletResponse response, ModelAndView mv) {

		// 1) request 처리
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		MemberVO vo = new MemberVO();
		String uri = "/member/loginForm";

		// 2) service 처리
		vo.setId(id);
		vo = service.selectOne(vo);
		if ( vo != null ) {

			// ID 는 일치 -> Password 확인
			if ( vo.getPassword().equals(password) ) {
				// Login 성공 -> login 정보 session에 보관, home
				request.getSession().setAttribute("loginID", id);
				request.getSession().setAttribute("loginName", vo.getName());
				// getSession()은 ModelAndView 적용 X

				uri = "home" ;
			} else {
				// Password 오류
				mv.addObject("message", "** Password 오류,  다시 하세요");
			}

		} else {	// ID 오류
			mv.addObject("message", "** ID 오류,  다시 하세요");
		} //else

		mv.setViewName(uri);
		return mv;
		
	} // login

//-----------------------------------------------------------------------------------------------------------------------

	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request,	HttpServletResponse response, ModelAndView mv) {

		// ** session 인스턴스 정의 후 삭제하기
		// => 매개변수: 없거나, true, false
		// => false : session 이 없을때 null 을 return;
		HttpSession session = request.getSession(false);

		if (session != null)
			session.invalidate();

		mv.addObject("message", "** 로그아웃 되었습니다");
		mv.setViewName("home");

		return mv;

	} // logout

} // MemberController
