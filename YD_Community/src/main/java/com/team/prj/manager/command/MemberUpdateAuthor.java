package com.team.prj.manager.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.prj.common.Command;
import com.team.prj.manager.service.ManagerService;
import com.team.prj.manager.service.ManagerServiceImpl;
import com.team.prj.member.vo.MemberVO;

public class MemberUpdateAuthor implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//회원 가입 승인
		System.out.println("확인용");
		ManagerService managerDao = new ManagerServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberNo(Integer.valueOf(request.getParameter("memberNo")));
		int n = managerDao.memberUpdateAuthor(vo);
		String jsonList = "0";
		System.out.println(n);
		if(n != 0) {
			jsonList = "1";
		}
		System.out.println("ajax:"+jsonList);
		return "ajax:"+jsonList;
	}
}
