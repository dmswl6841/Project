package com.team.prj.board.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.board.service.BoardService;
import com.team.prj.board.serviceImpl.BoardServiceImpl;
import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;

public class BoardView implements Command{

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 글 1개보기
		int board_no = Integer.parseInt(request.getParameter("board_no"));
		BoardService boardDao = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		vo = boardDao.boardView(board_no);
		request.setAttribute("vo", vo);
		return "board/boardView";

	}

}
