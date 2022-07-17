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
import com.team.prj.board.command.BoardView;
import com.team.prj.board.command.FreeBoardForm;
import com.team.prj.board.command.FreeBoardList;
import com.team.prj.board.command.NoticeBoardForm;
import com.team.prj.board.command.NoticeBoardList;
import com.team.prj.board.command.QnaBoardForm;
import com.team.prj.board.command.QnaBoardList;
import com.team.prj.board.command.TotalBoardList;

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
		// board 게시판
		map.put("/totalBoardList.do", new TotalBoardList()); // 커뮤니티 메인 처음화면
		map.put("/freeBoardList.do", new FreeBoardList()); // 자유 게시판 처음화면
		map.put("/qnaBoardList.do", new QnaBoardList()); // qna 게시판 처음화면
		map.put("/noticeBoardList.do", new NoticeBoardList()); // 공지 게시판 처음화면
		map.put("/boardView.do", new BoardView()); // 글 상세보기화면
		map.put("/freeBoardForm.do", new FreeBoardForm()); // 자유게시판 글쓰기 화면
		map.put("/qnaBoardForm.do", new QnaBoardForm()); // Qna게시판 글쓰기 화면
		map.put("/noticeBoardForm.do", new NoticeBoardForm()); // 공지게시판 글쓰기 화면
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
