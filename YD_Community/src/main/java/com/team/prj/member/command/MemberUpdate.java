package com.team.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team.prj.common.Command;
import com.team.prj.member.service.MemberService;
import com.team.prj.member.service.MemberServiceImpl;
import com.team.prj.member.vo.MemberVO;

public class MemberUpdate implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberVO member = (MemberVO) session.getAttribute("member");
		//String id = request.getParameter("id");
		MemberService dao = new MemberServiceImpl();
		// 세션 리플레이스?
		request.setAttribute("member", dao.memberSelectOne(member));
		return "member/memberUpdate";
	}

}
