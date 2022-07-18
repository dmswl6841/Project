package com.team.prj.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.board.service.BoardService;
import com.team.prj.board.serviceImpl.BoardServiceImpl;
import com.team.prj.common.Command;

public class BoardDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 삭제
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		
		BoardService boardDao = new BoardServiceImpl();
		boardDao.boardDelete(board_no);
		
		return "board/freeBoardList";
	}

}
