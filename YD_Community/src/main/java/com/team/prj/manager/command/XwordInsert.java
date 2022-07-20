package com.team.prj.manager.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.common.Command;
import com.team.prj.manager.service.ManagerService;
import com.team.prj.manager.service.ManagerServiceImpl;
import com.team.prj.xword.vo.XwordVO;

public class XwordInsert implements Command {
	
	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//금지어 등록
		ManagerService mangerDao = new ManagerServiceImpl();
		XwordVO vo = new XwordVO();
		int n =0;
		
		try {
			n = mangerDao.XwordInsert(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String returnPage = null;
		if(n != 0) {
			
			returnPage = "XwordList.do";
		}else {
			request.setAttribute("message", "게시글 등록이 실패했습니다.");
//			returnPage = "manager/noticeError";
			System.out.println("금지어 추가 실패");
		}
		return returnPage;
	}
}
