package com.team.prj.study.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team.prj.common.Command;
import com.team.prj.member.vo.MemberVO;
import com.team.prj.study.service.StudyService;
import com.team.prj.study.studyImpl.StudyServiceImpl;
import com.team.prj.study.vo.StudyVO;

public class StudyView implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 글 상세보기
		HttpSession session = request.getSession();
		int memberNo = 0;
		if (session.getAttribute("member") != null) {
			MemberVO member = (MemberVO) session.getAttribute("member");
			memberNo = member.getMemberNo();
		}

		int study_no = Integer.parseInt(request.getParameter("study_no"));
		StudyService studyDao = new StudyServiceImpl();
		StudyVO vo = new StudyVO();
		vo = studyDao.studyViewList(study_no);

		request.setAttribute("vo", vo);
		request.setAttribute("memberNo", memberNo);
		return "study/StudyView";
	}

}
