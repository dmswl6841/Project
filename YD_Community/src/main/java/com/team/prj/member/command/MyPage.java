package com.team.prj.member.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;
import com.team.prj.member.service.MemberServiceImpl;
import com.team.prj.member.vo.MemberVO;
import com.team.prj.page.serviceImpl.PageServiceImpl;

public class MyPage implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {

		PageServiceImpl pageService = new PageServiceImpl();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member"); // 세션 가져오기
		int memberNo = member.getMemberNo();
		List<BoardVO> list = pageService.myBoardList(memberNo);	//최근활동 없애고 내가 쓴 글목록(현진)
		
		String memberNick = new MemberServiceImpl().searchMemberNick(memberNo);
		int boardWriter = memberNo;
		
		// 남의 이름 클릭해서 myPage 보는 경우
		if (request.getParameter("no") != null) {
			boardWriter = Integer.parseInt(request.getParameter("no"));
			list = pageService.myBoardList(boardWriter); //최근활동 없애고 내가 쓴 글목록(현진)
			memberNick = new MemberServiceImpl().searchMemberNick(boardWriter);
		}

		request.setAttribute("boardWriter", boardWriter);
		request.setAttribute("memberNo", memberNo);
		request.setAttribute("memberNick", memberNick);
		request.setAttribute("list", list);

		// my Page 출력
		return "member/myPage";
	}

}
