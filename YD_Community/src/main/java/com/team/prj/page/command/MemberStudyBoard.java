package com.team.prj.page.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;
import com.team.prj.member.vo.MemberVO;
import com.team.prj.page.serviceImpl.PageServiceImpl;
import com.team.prj.study.vo.StudyVO;

public class MemberStudyBoard implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 내 스터디 게시글 목록
		
		
		PageServiceImpl pageService = new PageServiceImpl();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		int memberNo = member.getMemberNo();
		
	
		List<StudyVO> list = pageService.myStudyBoardList(memberNo);
		request.setAttribute("list", list);
		return "page/memberStudyBoard";
		
		
		
//		PageService pageDao = new PageServiceImpl();
//		List<BoardVO> list = new ArrayList<BoardVO>();
//		list = pageDao.myBoardList();
//		
//		
//		request.setAttribute("list", list);
//		return "page/memberBoard";
	}

}
