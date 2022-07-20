package com.team.prj.manager.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.common.Command;

public class XwordInsertForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//금지어 입력 폼 호출
		return "manager/XwordInsertForm";
	}

}
