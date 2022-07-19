package com.team.prj.manager.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.common.Command;
import com.team.prj.manager.service.ManagerService;
import com.team.prj.manager.service.ManagerServiceImpl;

public class MemberUpdateAuthor implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//회원 가입 승인
		System.out.println("확인용");
		ManagerService managerDao = new ManagerServiceImpl();
		String key = request.getParameter("key");
		String val = request.getParameter("val");
		
		System.out.println(key);
		System.out.println(val);
		int n = managerDao.memberUpdateAuthor(key, val);
		String jsonList = "0";
		System.out.println("n : "+ n);
		if(n != 0) {
			if(key.equals("ADMIN")) {
				jsonList = "2";
			}else {
				jsonList = "1";
			}
		}
		System.out.println("ajax:"+jsonList);
		return "ajax:"+jsonList;
	}
}