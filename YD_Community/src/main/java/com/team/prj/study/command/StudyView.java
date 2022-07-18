package com.team.prj.study.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.common.Command;
import com.team.prj.study.service.StudyService;
import com.team.prj.study.studyImpl.StudyServiceImpl;
import com.team.prj.study.vo.StudyVO;

public class StudyView implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 글 상세보기
		
		int study_no = Integer.parseInt(request.getParameter("study_no"));
		System.out.println(study_no);
		StudyService studyDao = new StudyServiceImpl();
		List<StudyVO> studyviewlist = new ArrayList<>();
		studyviewlist = studyDao.studyViewList(study_no);
		
		request.setAttribute("studyviewlist", studyviewlist);
		System.out.println("값" + studyviewlist);
		
		return "study/StudyView";
	}

}
