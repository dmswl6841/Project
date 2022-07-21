package com.team.prj.manager.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.common.Command;

public class ManagerMain implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//관리자 메인 화면
		return "manager/managerMain";
	}

}
