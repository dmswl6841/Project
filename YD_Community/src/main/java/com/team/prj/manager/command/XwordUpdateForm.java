package com.team.prj.manager.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.common.Command;
import com.team.prj.manager.service.ManagerService;
import com.team.prj.manager.service.ManagerServiceImpl;
import com.team.prj.xword.vo.XwordVO;

public class XwordUpdateForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		ManagerService managerDao = new ManagerServiceImpl();
		String key = request.getParameter("key");
		String val = request.getParameter("val");
		
		return "manager/XwordUpdateForm";
	}

}
