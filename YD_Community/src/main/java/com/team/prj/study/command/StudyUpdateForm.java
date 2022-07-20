package com.team.prj.study.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.board.service.BoardService;
import com.team.prj.board.serviceImpl.BoardServiceImpl;
import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;
import com.team.prj.study.service.StudyService;
import com.team.prj.study.studyImpl.StudyServiceImpl;
import com.team.prj.study.vo.StudyVO;

public class StudyUpdateForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {

		int studyNo = Integer.parseInt(request.getParameter("studyNo"));

		StudyService dao = new StudyServiceImpl();
		StudyVO vo = new StudyVO();
		vo = dao.studyViewList(studyNo);
		request.setAttribute("vo", vo);
		return "study/studyUpdate";
	}

}
