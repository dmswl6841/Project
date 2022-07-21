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
//		ManagerService managerDao = new ManagerServiceImpl();
//		String key = request.getParameter("key");
//		System.out.println(key);
//		
//		int n =managerDao.Xwordone(key, val);
//		
//		String jsonList = "0";
//		if(n != 0) {
//			jsonList="1";
//		}
//		System.out.println("ajax:"+jsonList);
//		return "ajax:"+jsonList;
//		
		
		
		
		ManagerService managerDao = new ManagerServiceImpl();
		XwordVO vo = new XwordVO();
		String key = request.getParameter("key");
		System.out.println(key);
		if(key.length()>0) {
			return "manager/XwordUpdate"; 
		}
//		vo = managerDao.XwordSelectOne(key);
		request.setAttribute("vo", vo);
		
		return "ajax:" + key;
	}
}
