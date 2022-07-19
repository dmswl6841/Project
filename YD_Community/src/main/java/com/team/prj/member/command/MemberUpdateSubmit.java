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
		//String img = request.getParameter("img");
		String id = request.getParameter("id");
		String nickname = request.getParameter("nickname");
		String email = request.getParameter("email");
		String git = request.getParameter("git");
		
		MemberVO vo = new MemberVO();
		//vo.setMemberImg(img);
		vo.setMemberId(id);
		vo.setMemberNick(nickname);
		vo.setMemberEmail(email);
		vo.setMemberGit(git);
		
		session.setAttribute("member", vo);
		int cnt = new MemberServiceImpl().memberUpdate(vo);
		
		return "main.do";
	}

}
