package com.team.prj.manager.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.common.Command;
import com.team.prj.manager.service.ManagerService;
import com.team.prj.manager.service.ManagerServiceImpl;
import com.team.prj.xword.vo.XwordVO;

public class XwordUpdateForm implements Command {
	//금지어 수정 폼
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		return "manager/XwordUpdateForm";
	}
}
