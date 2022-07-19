package com.team.prj.page.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team.prj.common.Command;
import com.team.prj.member.vo.MemberVO;
import com.team.prj.page.serviceImpl.PageServiceImpl;
import com.team.prj.study.vo.StudyVO;

public class MemberStudyRecent implements Command{

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 최근활동 목록
		PageServiceImpl pageService = new PageServiceImpl();
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member"); //세션 가져오기
		int memberNo = member.getMemberNo();
		//int memberNo = (int) request.getAttribute("memberNo");
		List<StudyVO> list = pageService.myStudyLogList(memberNo);
		request.setAttribute("list", list);
		return "page/memberStudyRecent";
	}
 
	
}
