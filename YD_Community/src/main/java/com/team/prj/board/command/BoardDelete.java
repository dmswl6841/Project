package com.team.prj.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.board.service.BoardService;
import com.team.prj.board.serviceImpl.BoardServiceImpl;
import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;

public class BoardDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 삭제
		
		//보드넘버값을 얻어온다
		String board_no = request.getParameter("board_no");
		
		BoardVO vo = new BoardVO();
		BoardVO vo = new BoardVO();
		
		BoardService boardDao = new BoardServiceImpl();
		int n = boardDao.boardDelete(board_no);
		
		if (n == 1) { //삭제성공
			request.setAttribute("message", "게시글 삭제에 성공했습니다.");
		} else {
			request.setAttribute("message", "게시글 삭제를 실패했습니다.");
		}
		
		return "board/boardView";
	}

}
