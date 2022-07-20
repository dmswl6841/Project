package com.team.prj.study.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.prj.common.Command;
import com.team.prj.study.service.StudyService;
import com.team.prj.study.studyImpl.StudyServiceImpl;
import com.team.prj.study.vo.StudyVO;

public class AjaxNoticeSearche implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글검색
		
		StudyService studyDao  = new StudyServiceImpl();
		List<StudyVO> searchstudylist = new ArrayList<StudyVO>();
		ObjectMapper mapper = new ObjectMapper();
		String key = request.getParameter("key");
		String val = request.getParameter("val");
		
		searchstudylist = studyDao.studySearchList(key, val);
		String jsonList=null;
		try {
			jsonList = mapper.writeValueAsString(searchstudylist);
		}catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		return "ajax:"+jsonList;
	}

}
