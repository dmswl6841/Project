package com.team.prj.study.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.common.Command;
import com.team.prj.study.service.StudyService;
import com.team.prj.study.studyImpl.StudyServiceImpl;
import com.team.prj.study.vo.StudyVO;

public class StudyList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 목록가져오기
	StudyService studyDao = new StudyServiceImpl();
	List<StudyVO> list = new ArrayList<>();
		list = studyDao.studySelectList();
		request.setAttribute("list", list);
		return "study/StudyList";
	}

}
