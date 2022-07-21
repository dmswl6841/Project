package com.team.prj.manager.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;
import com.team.prj.manager.service.ManagerService;
import com.team.prj.manager.service.ManagerServiceImpl;
import com.team.prj.member.vo.MemberVO;
import com.team.prj.xword.vo.XwordVO;

public class ManagerMain implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//관리자 메인 화면
		ManagerService managerDao = new ManagerServiceImpl();
		List<MemberVO> mlist = new ArrayList<>();
		mlist = managerDao.managerGuestList();
		request.setAttribute("mlist", mlist);
		
		List<BoardVO> blist = new ArrayList<>();
		blist = managerDao.managerBoardSelectAll();
		request.setAttribute("blist", blist);
		
		List<XwordVO> xList = new ArrayList<>();
		xList = managerDao.XwordSelectAll();
		request.setAttribute("xList", xList);
		return "manager/managerMain";
	}

}
