package com.team.prj.board.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.board.service.BoardService;
import com.team.prj.board.serviceImpl.BoardServiceImpl;
import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;

public class HotBoardList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 목록 가져오기
		BoardService boardDao = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		int n = 0;
		
		n = boardDao.HboardUpdate(vo);
		
		if (n != 0) {
			
			List<BoardVO> hotboardlist = new ArrayList<>();
			hotboardlist = boardDao.HboardSelectList();
			request.setAttribute("hotboardlist", hotboardlist);		
		}
		return "board/hotBoardList";
	}

}
