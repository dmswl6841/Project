package com.team.prj.study.command;





import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.common.Command;
import com.team.prj.study.service.StudyService;
import com.team.prj.study.studyImpl.StudyServiceImpl;
import com.team.prj.study.vo.StudyVO;

public class StudyDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
       // 게시글 삭제
		StudyService studyDao = new StudyServiceImpl();		
		StudyVO vo = new StudyVO();
		vo.setStudyNo(Integer.parseInt(request.getParameter("studyNo")));
		int n = studyDao.studyDelete(vo);
		String jsonList = "0";
		
		if(n != 0) {
			jsonList = "1";
		}
                                                               
		return "ajax:"+jsonList;
	}

}
