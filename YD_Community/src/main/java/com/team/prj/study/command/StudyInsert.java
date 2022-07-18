package com.team.prj.study.command;

import java.io.IOException;


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
		// 게시글등록(파일업로드포함)
		StudyService studyDao = new StudyServiceImpl();
		StudyVO vo = new StudyVO();
		String savePath = "C:\\Temp\\"; //파일 저장위치  "fileSave"
		int upLoadSize = 1024*1024*1024;  //최대파일 사이즈 100MB 
		
		int n =0;
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request, savePath, upLoadSize,"utf-8", 
					new DefaultFileRenamePolicy());
			System.out.println("**********"+multi.getParameter("studyWriter"));
			String orignalFileName = multi.getOriginalFileName("file");
			String saveFileName = multi.getFilesystemName("file");
			vo.setStudyTitle(multi.getParameter("studyTitle"));
			vo.setStudySubject(multi.getParameter("studySubject"));
			vo.setStudyWriter(multi.getParameter("studyWriter"));
			vo.setStudyLanguage(multi.getParameter("studyLanguage"));
			vo.setStudySystem(multi.getParameter("studySystem"));
			vo.setMemberNo(Integer.parseInt(multi.getParameter("memberNo")));
			vo.setStudyPeriod(multi.getParameter("studyPeriod"));
			//vo.setStudyDate((multi.getParameter("STUDY_DATE")));
			if(orignalFileName != null) {
				vo.setStudyAttech(orignalFileName);
				saveFileName = savePath + saveFileName; //파일경로를 추가한다.
				vo.setStudyAttechDir(saveFileName);
			}
			n = studyDao.studyInsert(vo);			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String returnPage = null;
		if(n != 0) {
			returnPage = "StudyList.do";
		}else {
			request.setAttribute("message", "게시글 등록이 실패했습니다.");
			returnPage = "study/studyError";
		}
		return returnPage;
	}

		
		
		
	

}
