package com.team.prj.manager.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.common.Command;
import com.team.prj.manager.service.ManagerService;
import com.team.prj.manager.service.ManagerServiceImpl;
import com.team.prj.xword.vo.XwordVO;

public class XwordList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//금지어 목록
		ManagerService managerDao = new ManagerServiceImpl();
		List<XwordVO> list = new ArrayList<>();
		list = managerDao.XwordSelectAll();
		request.setAttribute("list", list);
		return "manager/XwordList";
	}

}
