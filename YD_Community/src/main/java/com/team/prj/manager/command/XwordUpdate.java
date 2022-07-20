package com.team.prj.manager.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.common.Command;
import com.team.prj.manager.service.ManagerService;
import com.team.prj.manager.service.ManagerServiceImpl;

public class XwordUpdate implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//금지어 수정
		ManagerService managerDao = new ManagerServiceImpl();
		
		String key = request.getParameter("key");
		int n =managerDao.XwordInsert(key);
		
		String jsonList = "0";
		if(n != 0) {
			jsonList="1";
		}
		System.out.println("ajax:"+jsonList);
		return "ajax:"+jsonList;
	}

}
