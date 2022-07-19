package com.team.prj.manager.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;
import com.team.prj.manager.service.ManagerService;
import com.team.prj.manager.service.ManagerServiceImpl;

public class ManagerBoardList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//게시판목록
		ManagerService managerDao = new ManagerServiceImpl();
		List<BoardVO> list = new ArrayList<>();
		list = managerDao.managerBoardSelectAll();
		request.setAttribute("list", list);
		return "manager/boardList";
	}

}
