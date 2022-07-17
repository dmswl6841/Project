package com.team.prj.board.command;

import java.util.ArrayList;
import java.util.List;

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
		List<BoardVO> boardviewlist = new ArrayList<>();
		boardviewlist = boardDao.boardViewList(board_no);
		request.setAttribute("boardviewlist", boardviewlist);
		return "board/boardView";

	}

}
