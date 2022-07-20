package com.team.prj.manager.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.prj.common.Command;
import com.team.prj.manager.service.ManagerService;
import com.team.prj.manager.service.ManagerServiceImpl;
import com.team.prj.xword.vo.XwordVO;

public class XwordInsert implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//금지어 등록
		ManagerService managerDao = new ManagerServiceImpl();
		String key = request.getParameter("key");
		int n =managerDao.XwordInsert(key);
		System.out.println(n);
		
		String jsonList = "0";
		if(n != 0) {
			jsonList="1";
		}
		System.out.println("ajax:"+jsonList);
		return "ajax:"+jsonList;
	}
}
