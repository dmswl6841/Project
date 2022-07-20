package com.team.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team.prj.common.Command;
import com.team.prj.member.service.MemberService;
import com.team.prj.member.service.MemberServiceImpl;
import com.team.prj.member.vo.MemberVO;

public class MemberUpdateSubmit implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		MemberService dao = new MemberServiceImpl();

		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String git = request.getParameter("git");

		MemberVO vo = (MemberVO) session.getAttribute("member");

		vo.setMemberNick(nickname);
		vo.setMemberEmail(email);
		vo.setMemberGit(git);

		int cnt = dao.memberUpdate(vo);

		request.setAttribute("message", cnt + "건 수정 완료");

		return "myPage.do";
	}

}
