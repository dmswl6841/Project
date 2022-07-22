package com.team.prj;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.board.service.BoardService;
import com.team.prj.board.serviceImpl.BoardServiceImpl;
import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;

public class MainCommand implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//단순히 시작페이지를 보여주는 메소드
		BoardService boardDao = new BoardServiceImpl();
		List<BoardVO> totalboardlist= new ArrayList<>();
		totalboardlist = boardDao.TboardSelectList();
		request.setAttribute("totalboardlist", totalboardlist);
		return "main/main";
	}

}
