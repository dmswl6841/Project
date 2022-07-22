package com.team.prj.page.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;
import com.team.prj.member.service.MemberServiceImpl;
import com.team.prj.member.vo.MemberVO;
import com.team.prj.page.serviceImpl.PageServiceImpl;

public class MemberBoard implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 내 게시글 목록
		PageServiceImpl pageService = new PageServiceImpl();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		int memberNo = member.getMemberNo();
		List<BoardVO> list = pageService.myBoardList(memberNo);
		
		String memberNick = new MemberServiceImpl().searchMemberNick(memberNo);
		int boardWriter = memberNo;
		
		if(request.getParameter("no")!=null) {
			boardWriter = Integer.parseInt(request.getParameter("no"));
			list = pageService.myBoardList(boardWriter); //최근활동 없애고 내가 쓴 글목록(현진)
			memberNick = new MemberServiceImpl().searchMemberNick(boardWriter);
		}
		//int memberNo = (int) request.getAttribute("memberNo");
	//	List<LogVO> list = pageService.myLogList(memberNo);
	
		
		//request.setAttribute("boardWriter", memberNo);

		request.setAttribute("boardWriter", boardWriter);
		request.setAttribute("memberNo", memberNo);
		request.setAttribute("memberNick", memberNick);
		request.setAttribute("list", list);
		
		return "page/memberBoard";
		
		
		
//		PageService pageDao = new PageServiceImpl();
//		List<BoardVO> list = new ArrayList<BoardVO>();
//		list = pageDao.myBoardList();
//		
//		
//		request.setAttribute("list", list);
//		return "page/memberBoard";
	}

}
