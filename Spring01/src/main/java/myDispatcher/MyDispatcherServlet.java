package myDispatcher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 < *** Spring MVC2_ver01 >
 => MyDispatcherServlet (FrontController 역할)
    HandlerMapping, ViewResolver를 활용해서
    요청분석, Service, View를 처리 
*/

public class MyDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// ** 전역변수 정의
	private HandlerMapping hmappings;
	private ViewResolver vresolver;
	// => viewName 앞의 경로, 뒤의 확장자를 처리해줌.
	

    public MyDispatcherServlet() {
        super();
        
        hmappings = HandlerMapping.getInstance();
        vresolver = new ViewResolver();
        
        vresolver.setPrefix("/WEB-INF/views/");
        vresolver.setSuffix(".jsp");
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1. 요청(request) 분석
		// => url 또는 uri 분석해서 요청명 확인
		// => 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		String uri = request.getRequestURI();
		String mappingName = uri.substring(uri.lastIndexOf("/")); // 가장 마지막 "/"부터 뒤로 다 읽기
		// ex) http://localhost:8080/green/mlist -> /mlist
		
		String viewName = "home";
		
	//--------------------------------------------------------------------------------------------------------
		
		// 2. 해당 서비스 실행
		// => 공장(HandlerMapping)에 요청, 해당되는 컨트롤러를 전달받음
		// => 해당 서비스 메서드 실행
		MyController service = hmappings.getController(mappingName); // HandlerMapping -> getController 실행
		
		if (service != null)
			viewName = service.handleRequest(request, response);
		else
			request.setAttribute("message", mappingName + "는 없는 요청입니다.");
		
	//--------------------------------------------------------------------------------------------------------
		
		// 3. View 처리
		// => viewName 완성
		viewName = vresolver.getViewName(viewName);
		
		request.getRequestDispatcher(viewName).forward(request, response);
	
	} // doGet


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	} // doPost

} // class
