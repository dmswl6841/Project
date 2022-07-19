package com.team.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team.prj.common.Command;
import com.team.prj.member.service.MemberService;
import com.team.prj.member.service.MemberServiceImpl;
import com.team.prj.member.vo.MemberVO;

public class MemberJoinout implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(); 

		MemberVO vo = (MemberVO) session.getAttribute("member");
		MemberService dao = new MemberServiceImpl();
		int cnt = dao.memberDelete(vo);
		if(cnt>0) {
			request.setAttribute("message", "회원탈퇴 성공");
			session.invalidate();
		}else {
			request.setAttribute("message", "회원탈퇴 실패");
		}
		
	return "member/memberJoinout";	
	}

}
