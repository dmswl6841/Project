package com.team.prj.manager.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.common.Command;
import com.team.prj.manager.service.ManagerService;
import com.team.prj.manager.service.ManagerServiceImpl;
import com.team.prj.member.vo.MemberVO;
import com.team.prj.xword.vo.XwordVO;

public class XwordDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//회원 삭제
		ManagerService managerDao = new ManagerServiceImpl();
		XwordVO vo = new XwordVO();
		vo.setXwordNo(Integer.valueOf(request.getParameter("xwordNo")));
		int n = managerDao.XwordDelete(vo);
		String jsonList = "0";
		if(n != 0) {
			jsonList = "1";
		}
		return "ajax:"+jsonList;
	}

}