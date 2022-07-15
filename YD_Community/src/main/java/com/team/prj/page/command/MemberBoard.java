package com.team.prj.page.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;
import com.team.prj.page.service.PageService;
import com.team.prj.page.serviceImpl.PageServiceImpl;

public class MemberBoard implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 내 게시글 목록
		
		PageService pageDao = new PageServiceImpl();
		List<BoardVO> list = new ArrayList<BoardVO>();
		list = pageDao.myBoardList();
		request.setAttribute("list", list);
		return "page/memberBoard";
	}

}
