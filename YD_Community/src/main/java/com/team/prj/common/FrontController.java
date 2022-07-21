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
import com.team.prj.board.command.AjaxBoardSearche;
import com.team.prj.board.command.BoardDelete;
import com.team.prj.board.command.BoardForm;
import com.team.prj.board.command.BoardInsert;
import com.team.prj.board.command.BoardUpdate;
import com.team.prj.board.command.BoardUpdateForm;
import com.team.prj.board.command.BoardView;
import com.team.prj.board.command.FreeBoardList;

import com.team.prj.board.command.HotBoardList;
import com.team.prj.board.command.NoticeBoardForm;

import com.team.prj.board.command.NoticeBoardList;
import com.team.prj.board.command.QnaBoardList;
import com.team.prj.board.command.TotalBoardList;
import com.team.prj.comments.command.CommentDelete;
import com.team.prj.comments.command.CommentInsert;
import com.team.prj.comments.command.CommentUpdate;
import com.team.prj.comments.command.CommentUpdateForm;
import com.team.prj.manager.command.ManagerBoardDelete;
import com.team.prj.manager.command.ManagerBoardList;
import com.team.prj.manager.command.ManagerBoardSearch;
import com.team.prj.manager.command.ManagerMain;
import com.team.prj.manager.command.ManagerMemberDelete;
import com.team.prj.manager.command.ManagerMemberList;
import com.team.prj.manager.command.ManagerMemberSearch;
import com.team.prj.manager.command.MemberUpdateAuthor;
import com.team.prj.manager.command.XwordDelete;
import com.team.prj.manager.command.XwordInsert;
import com.team.prj.manager.command.XwordInsertForm;
import com.team.prj.manager.command.XwordList;
import com.team.prj.manager.command.XwordSearch;
import com.team.prj.manager.command.XwordUpdate;
import com.team.prj.manager.command.XwordUpdateForm;
import com.team.prj.member.command.AjaxMemberIdCheck;
import com.team.prj.member.command.AjaxMemberNicknameCheck;
import com.team.prj.member.command.KakaoJoin;
import com.team.prj.member.command.KakaoLogin;
import com.team.prj.member.command.MemberJoin;
import com.team.prj.member.command.MemberJoinForm;
import com.team.prj.member.command.MemberJoinout;
import com.team.prj.member.command.MemberLogin;
import com.team.prj.member.command.MemberLoginForm;
import com.team.prj.member.command.MemberLogout;
import com.team.prj.member.command.MemberUpdate;
import com.team.prj.member.command.MemberUpdateSubmit;
import com.team.prj.member.command.MyPage;
import com.team.prj.page.command.MemberBoard;
import com.team.prj.page.command.MemberRecent;
import com.team.prj.page.command.MemberScrap;
import com.team.prj.page.command.MemberStudy;
import com.team.prj.page.command.MemberStudyBoard;
import com.team.prj.page.command.MemberStudyRecent;

import com.team.prj.recommend.command.RecommendInsert;
import com.team.prj.scrap.command.ScrapInsert;


import com.team.prj.study.command.AjaxNoticeSearche;
import com.team.prj.study.command.StudyDelete;
import com.team.prj.study.command.StudyForm;
import com.team.prj.study.command.StudyInsert;
import com.team.prj.study.command.StudyList;
import com.team.prj.study.command.StudyUpdate;
import com.team.prj.study.command.StudyUpdateForm;
import com.team.prj.study.command.StudyView;

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

		//////////////////////board 게시판//////////////////////
		map.put("/totalBoardList.do", new TotalBoardList()); // 커뮤니티 메인 처음화면
		map.put("/freeBoardList.do", new FreeBoardList()); // 자유 게시판 처음화면
		map.put("/qnaBoardList.do", new QnaBoardList()); // qna 게시판 처음화면
		map.put("/noticeBoardList.do", new NoticeBoardList()); // 공지 게시판 처음화면

		map.put("/hotBoardList.do", new HotBoardList()); // 인기 게시판 처음화면
		
		map.put("/boardForm.do", new BoardForm()); // 글쓰기폼 호출


		map.put("/boardView.do", new BoardView()); // 글 상세보기화면
		map.put("/boardInsert.do", new BoardInsert()); //글입력
		
		map.put("/boardUpdate.do", new BoardUpdate()); // 글 수정하기
		map.put("/boardUpdateForm.do", new BoardUpdateForm()); // 글수정폼
		map.put("/boardDelete.do", new BoardDelete()); // 글 삭제하기
		
		map.put("/ajaxBoardSearche.do", new AjaxBoardSearche()); // 글 삭제하기
		////////////////////////////////////////////////////
		


        //////////////////Recommend & Scrap기능/////////////////////
		map.put("/recommendInsert.do", new RecommendInsert()); // 글 추천하기
		map.put("/scrapInsert.do", new ScrapInsert()); // 글 추천하기
		
		
		

		map.put("/StudyForm.do", new StudyForm());//게시글입력폼 호출
    
		//관리자
		map.put("/managerMain.do", new ManagerMain()); //관리자 메인 화면
		map.put("/managerMemberList.do", new ManagerMemberList()); //회원 전체 목록
		map.put("/managermemberSearch.do", new ManagerMemberSearch()); //회원 검색
		map.put("/managerMemberDelete.do", new ManagerMemberDelete()); //회원삭제
		map.put("/memberUpdateAuthor.do", new MemberUpdateAuthor()); //회원 가입승인
		map.put("/managerBoardList.do", new ManagerBoardList()); //게시글 전체 목록
		map.put("/managerBoardSearch.do", new ManagerBoardSearch()); //게시글 검색
		map.put("/managerBoardDelete.do", new ManagerBoardDelete());
		map.put("/XwordList.do", new XwordList()); //금지어 전체 목록
		map.put("/XwordSearch.do", new XwordSearch()); //금지어 검색
		map.put("/XwordInsertForm.do", new XwordInsertForm()); //금지어 입력 팝업창
		map.put("/XwordInsert.do", new XwordInsert());
		map.put("/XwordUpdateForm.do", new XwordUpdateForm()); //금지어 수정 팝업창
		map.put("/XwordUpdate.do", new XwordUpdate()); //금지어 수정
		map.put("/XwordDelete.do", new XwordDelete()); //금지어 삭제

		//스터디게시판
		map.put("/StudyList.do", new StudyList());//게시글리스트폼 호출
		map.put("/StudyInsert.do", new StudyInsert());//게시글등록
		map.put("/StudyView.do", new StudyView());//게시글세부내용
		map.put("/StudyDelete.do", new StudyDelete());
		map.put("/StudyUpdateForm.do", new StudyUpdateForm());
		map.put("/StudyUpdate.do", new StudyUpdate());
		map.put("/StudyUpdate.do", new StudyUpdate());
		map.put("/ajaxNoticeSearche.do", new AjaxNoticeSearche());
		
	
		//마이페이지//
		map.put("/memberBoard.do", new MemberBoard()); //내가 쓴 자유게시판 글 목록
		map.put("/memberStudyBoard.do", new MemberStudyBoard()); //내가 쓴 스터디 글 목록
		
		map.put("/memberRecent.do", new MemberRecent()); // 최근활동 자유게시판
		map.put("/memberStudyRecent.do", new MemberStudyRecent()); // 최근활동 자유게시판
		
		map.put("/memberScrap.do", new MemberScrap()); //스크랩
		
		map.put("/memberStudy.do", new MemberStudy()); //찜한스터디
	
		//로그인 테스트용//
		map.put("/memberJoin.do",new MemberJoin());
		map.put("/memberJoinForm.do",new MemberJoinForm());

		map.put("/memberLogin.do", new MemberLogin());
		map.put("/memberLoginForm.do", new MemberLoginForm());
		map.put("/memberLogout.do", new MemberLogout());
		map.put("/memberJoinForm.do", new MemberJoinForm());
		map.put("/memberJoin.do", new MemberJoin());
		map.put("/ajaxMemberIdCheck.do", new AjaxMemberIdCheck());
		map.put("/ajaxMemberNicknameCheck.do", new AjaxMemberNicknameCheck());
		
		map.put("/KakaoLogin.do", new KakaoLogin());
		map.put("/KakaoJoin.do", new KakaoJoin());
		
		map.put("/myPage.do", new MyPage());
		map.put("/memberUpdate.do", new MemberUpdate());
		map.put("/memberUpdateSubmit.do", new MemberUpdateSubmit());
		map.put("/memberJoinout.do", new MemberJoinout());
		
		
		// 댓글
		map.put("/commentInsert.do", new CommentInsert());
		map.put("/commentDelete.do", new CommentDelete());
		map.put("/commentUpdateForm.do", new CommentUpdateForm());
		map.put("/commentUpdate.do", new CommentUpdate());
		
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 서비스(실행)하는 메소드
		request.setCharacterEncoding("utf-8"); // 한글 깨짐 방지
		String uri = request.getRequestURI(); // 요청된 URI를 확인한다.
		String contextPath = request.getContextPath(); // 요청 URL로 부터 contextPath를 확인한다.
		String page = uri.substring(contextPath.length()); // 실제로 요청된 것 (uri에서 contextPath의 길이만큼 빼면 실제 요청한것)
		System.out.println("page: " + page);
		Command command = map.get(page); // 실제 수행할 Command를 찾음 = new MainCommand();
		String viewPage = command.exec(request, response); // 요청 Command를 수행하고 결과를 받음
		System.out.println("viewPage: " + viewPage);
		// viewResolve 보여줄 페이지 선택
		if (!viewPage.endsWith(".do") && !viewPage.equals(null)) {
			if (viewPage.startsWith("ajax:")) {
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5));
				return;
			}
			viewPage = viewPage + ".tiles"; // 시스템에서 접근가능한 폴더를 더해주고
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response); // 원하는 페이지를 호출에서 전달함
		} else {
			response.sendRedirect(viewPage); // .do로 권한 위임 처리
		}
		System.out.println("resolved viewPage: " + viewPage);
		System.out.println();
	}
}
