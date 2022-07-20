package com.team.prj.manager.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.common.Command;
import com.team.prj.manager.service.ManagerService;
import com.team.prj.manager.service.ManagerServiceImpl;
import com.team.prj.member.vo.MemberVO;

public class ManagerMemberList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//회원 목록 가져오기
		ManagerService managerDao = new ManagerServiceImpl();
		List<MemberVO> list = new ArrayList<>();
		list = managerDao.managerMemberSelectAll();
		request.setAttribute("list", list);
		return "manager/managerMemberList";
	}
}