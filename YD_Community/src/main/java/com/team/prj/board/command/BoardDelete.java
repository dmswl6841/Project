package com.team.prj.board.command;

import java.util.ArrayList;
import java.util.List;

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
		int board_no =Integer.parseInt(request.getParameter("board_no"));
		BoardService boardDao = new BoardServiceImpl();		
		int n = 0;
		
		BoardVO vo = new BoardVO();
	    vo.setBoardNo(board_no);
	    n= boardDao.boardDelete(vo);
		
		if (n == 1) { //삭제성공
			request.setAttribute("message", "게시글 삭제에 성공했습니다.");
		} else {
			request.setAttribute("message", "게시글 삭제를 실패했습니다.");
		}
		
		return "board/boardDelete";
	}

}
