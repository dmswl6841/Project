package com.team.prj.common;

import java.io.IOException;

import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.MainCommand;
import com.team.prj.manager.command.BoardList;
import com.team.prj.manager.command.MemberList;
import com.team.prj.manager.command.memberSelectList;
import com.team.prj.study.command.StudyForm;


@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HashMap<String, Command> map = new HashMap<>(); // 요청과 실행문을 매핑하기 위해

	public FrontController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		// 초기화 하는 메소드(Mapping하는 부분을 작성)
		map.put("/main.do", new MainCommand()); // 처음접속하는 페이지

		map.put("/StudyForm.do", new StudyForm());//게시글입력폼 호출
		
		
		//관리자
		//map.put("/memberMain.do", new MemberMain()); //메인 화면
		map.put("/memberList.do", new MemberList()); //회원 전체 목록
		map.put("/memberSearchList.do", new memberSelectList()); //회원 검색
		map.put("/boardList.do", new BoardList()); //게시글 전체 목록
		//
		//map.put("/boardSearchList.do", new BoardSelectList()); //게시글 검색
		//map.put("/XwordList.do", new XwordList()); //금지어 전체 목록
		//map.put("/XwordSearchList.do", new XwordSearchList()); //금지어 검색
		//map.put("/XwordInsert.do", new XwordInsert()); //금지어 입력
		//map.put("/XwordUpdate.do", new XwordUpdate()); //금지어 수정
		//map.put("/XwordDelete.do", new XwordDelete()); //금지어 삭제

	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 서비스(실행)하는 메소드
		request.setCharacterEncoding("utf-8"); // 한글 깨짐 방지
		String uri = request.getRequestURI(); // 요청된 URI를 확인한다.
		String contextPath = request.getContextPath(); // 요청 URL로 부터 contextPath를 확인한다.
		String page = uri.substring(contextPath.length()); // 실제로 요청된 것 (uri에서 contextPath의 길이만큼 빼면 실제 요청한것)

		Command command = map.get(page); // 실제 수행할 Command를 찾음 = new MainCommand();
		String viewPage = command.exec(request, response); // 요청 Command를 수행하고 결과를 받음
		System.out.println("viewPage1 : " + viewPage);

		// viewResolve 보여줄 페이지 선택
		if (!viewPage.endsWith(".do") && !viewPage.equals(null)) {
			if (viewPage.startsWith("ajax:")) {
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5));
				return;
			}
			viewPage = "/WEB-INF/views/" + viewPage + ".jsp"; // 시스템에서 접근가능한 폴더를 더해주고
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response); // 원하는 페이지를 호출에서 전달함
		} else {
			response.sendRedirect(viewPage); // .do로 권한 위임 처리
		}
		System.out.println("viewPage2 : " + viewPage);
	}
}
