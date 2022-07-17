package com.team.prj.board.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.team.prj.board.service.BoardService;
import com.team.prj.board.serviceImpl.BoardServiceImpl;
import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;


public class FreeBoardInsert implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 등록(파일 업로드 포함)
		BoardService boardDao = new BoardServiceImpl();
		BoardVO vo = new BoardVO();

		String savePath = "C:\\Temp\\"; // 파일 저장위치 "fileSave"
		int upLoadSize = 1024 * 1024 * 1024; // 최대파일 사이즈 100MB

		int n = 0;

		try {
			MultipartRequest multi = new MultipartRequest(request, savePath, upLoadSize, "utf-8",
					new DefaultFileRenamePolicy());
			String orignalFileName = multi.getOriginalFileName("file");
			String saveFileName = multi.getFilesystemName("file");
			vo.setBoardCategory(multi.getParameter("boardCategory"));
			vo.setBoardTitle(multi.getParameter("boardTitle"));
			vo.setBoardWriter(multi.getParameter("boardWriter"));
			vo.setBoardDate(multi.getParameter("boardDate"));
			vo.setBoardSubject(multi.getParameter("boardSubject"));

			if (orignalFileName != null) {
				vo.setBoardAttech(orignalFileName);
				saveFileName = savePath + saveFileName; // 파일경로를 추가한다.
				vo.setBoardAttechDir(saveFileName);
			}
			n = boardDao.boardInsert(vo);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String returnPage = null;
		if (n != 0) {
			returnPage = "freeboardList.do";
		} else {
			request.setAttribute("message", "게시글 등록이 실패했습니다.");
			returnPage = "board/boardError";
		}
		return returnPage;

			
	}

}
