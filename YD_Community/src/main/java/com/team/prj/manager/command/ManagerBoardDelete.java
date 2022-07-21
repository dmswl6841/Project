package com.team.prj.manager.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;
import com.team.prj.manager.service.ManagerService;
import com.team.prj.manager.service.ManagerServiceImpl;

public class ManagerBoardDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//게시글 삭제
		ManagerService managerDao = new ManagerServiceImpl();
		BoardVO vo = new BoardVO();
		vo.setBoardNo(Integer.valueOf(request.getParameter("key")));
		int n = managerDao.managerBoardDelete(vo);
		String jsonList = "0";
		if(n != 0) {
			jsonList = "1";
		}
		System.out.println(n);
		return "ajax:"+ jsonList;
	}
}
