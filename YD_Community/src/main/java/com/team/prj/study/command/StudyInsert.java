package com.team.prj.study.command;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import com.team.prj.common.Command;
import com.team.prj.study.service.StudyService;
import com.team.prj.study.studyImpl.StudyServiceImpl;
import com.team.prj.study.vo.StudyVO;

public class StudyInsert implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 등록(파일업로드 포함)
		StudyService studyDao = new StudyServiceImpl();
		StudyVO vo = new StudyVO();
		
		String savePath = "C:\\Temp\\"; //파일저장위치 "fileSave"
		int n = 0;
		int upLoadSize = 1024*1024*1024; //최대파일 100MB
		
		try {
			MultipartRequest multi = new MultipartRequest(request, savePath, upLoadSize,"utf-8"
					, new DefaultFileRenamePolicy());
			String orignalFileName = multi.getOriginalFileName("file");
			String saveFileName = multi.getFilesystemName("file");
			vo.setStudyWriter(multi.getParameter("StudyWriter"));//작성자
			vo.setStudyTitle(multi.getParameter("StudyTitle"));//제목
			vo.setStudyDate(Date.valueOf(multi.getParameter("StudyDate")));//작성일자
			vo.setStudyStartDate(Date.valueOf(multi.getParameter("StudyStartDate")));//스터디시작날짜
			vo.setStudySubject(multi.getParameter(multi.getParameter("StudySubject")));//글 내용
			vo.setStudySystem(multi.getParameter(multi.getParameter("StudySystem")));//진행방식
			vo.setStudyPeriod(multi.getParameter(multi.getParameter("StudyPeriod")));//진행기간
			vo.setStudyMember(multi.getParameter(multi.getParameter("StudyMember")));//진행인원
			vo.setStudyLanguage(multi.getParameter(multi.getParameter("StudyLanguage")));
		
			if(orignalFileName != null) {
				vo.setStudyAttech(orignalFileName);
				saveFileName = savePath + saveFileName;//파일경로를 추가한다.
				vo.setStudyAttechDir(saveFileName);
			}
			n= studyDao.studyInsert(vo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String returnPage = null;
		if(n !=0) {
			returnPage = "studyList.do";
		}else {
			request.setAttribute("message", "게시글 등록이 실패했습니다");
			returnPage = "study/studyError";
		}
		return returnPage;
	}


}
