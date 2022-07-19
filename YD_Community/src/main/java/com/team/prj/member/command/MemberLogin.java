package com.team.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team.prj.common.Command;
import com.team.prj.member.service.MemberService;
import com.team.prj.member.service.MemberServiceImpl;
import com.team.prj.member.vo.MemberVO;

public class MemberLogin implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		// 세선 객체에 로그인 정보를 저장하여 이 요청을 응답하고 나서 연결이 끊겨도 로그인 상태는 유지되도록

		MemberService dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberId(request.getParameter("memberId"));
		vo.setMemberPw(request.getParameter("memberPw"));
		vo = dao.memberLogin(vo);

		if (vo != null) {
			session.setAttribute("member", vo);
			request.setAttribute("message", "환영합니다, " + vo.getMemberNick() + "님!");

		} else {
			request.setAttribute("message", "아이디 또는 패스워드가 일치하지 않습니다.");
		}
		return "member/memberLogin";
	}

}
