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

public class MemberDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//회원 탈퇴
		ManagerService managerDao = new ManagerServiceImpl();
		List<MemberVO> list = new ArrayList<MemberVO>();
		ObjectMapper mapper = new ObjectMapper(); 
		MemberVO vo = new MemberVO();
		vo.setMemberNo(Integer.valueOf(request.getParameter("id")));
		int n = managerDao.memberDelete(vo);
		String jsonList = "0";
		
		if(n != 0) {
			jsonList = "1";
		}
		return "ajax:"+jsonList;
		
	}

}
