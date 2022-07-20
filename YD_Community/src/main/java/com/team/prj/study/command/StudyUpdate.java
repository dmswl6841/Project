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

public class StudyUpdate implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 수정
		StudyService dao = new StudyServiceImpl();
		StudyVO vo = new StudyVO();

		String savePath = "C:\\Temp\\"; // 파일 저장위치 "fileSave"
		int upLoadSize = 1024 * 1024 * 1024; // 최대파일 사이즈 100MB
		int n = 0;

		try {
			MultipartRequest multi = new MultipartRequest(request, savePath, upLoadSize, "utf-8",
					new DefaultFileRenamePolicy());
			String originalFileName = multi.getOriginalFileName("file");
			String saveFileName = multi.getFilesystemName("file");

			// 글 수정
			vo.setStudyTitle(multi.getParameter("studyTitle"));
			vo.setStudySubject(multi.getParameter("studySubject"));
			vo.setStudySystem(multi.getParameter("studySystem"));
			vo.setStudyPeriod(multi.getParameter("studyPeriod"));
			vo.setStudyLanguage(multi.getParameter("studyLang"));
			vo.setStudyAttech(multi.getParameter("studyAttech"));
			//vo.setStudyAttechDir(multi.getParameter("studyAttechDir"));
			vo.setStudyNo(Integer.parseInt(multi.getParameter("studyNo")));

			if (originalFileName != null) {
				vo.setStudyAttech(originalFileName);
				saveFileName = savePath + saveFileName; // 파일경로를 추가한다.
				vo.setStudyAttechDir(saveFileName);
			}
			n = dao.studyUpdate(vo);
			System.out.println("스터디 " + n + "건 수정");
		} catch (IOException e) {
			e.printStackTrace();
		}

		String returnPage = null;

		if (n != 0) {
			request.setAttribute("message", "게시글 수정에 성공했습니다.");
			returnPage = "StudyList.do";
		} else {
			request.setAttribute("message", "게시글 수정을 실패했습니다.");
			returnPage = "StudyList.do";
		}
		return returnPage;
	}
}
