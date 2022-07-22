package com.team.prj.manager.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.common.Command;
import com.team.prj.manager.service.ManagerService;
import com.team.prj.manager.service.ManagerServiceImpl;
import com.team.prj.xword.vo.XwordVO;

public class XwordSelectOne implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		ManagerService managerDao = new ManagerServiceImpl();
		XwordVO vo = new XwordVO();
		vo.setXwordNo(Integer.valueOf(request.getParameter("key")));
		String key = request.getParameter("key");
		System.out.println(key);
		vo = managerDao.XwordSelectOne(key);
		System.out.println("vo : " +vo);
		request.setAttribute("vo", vo);
		return "XwordUpdateForm.do";
	}
}
