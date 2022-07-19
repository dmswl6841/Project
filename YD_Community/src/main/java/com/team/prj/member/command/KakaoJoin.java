package com.team.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.common.Command;
import com.team.prj.member.service.MemberService;
import com.team.prj.member.service.MemberServiceImpl;
import com.team.prj.member.vo.MemberVO;

public class KakaoJoin implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		MemberService dao = new MemberServiceImpl();
		MemberVO vo = new MemberVO();
		vo.setMemberId(request.getParameter("memberId"));
		vo.setMemberPw(request.getParameter("memberPw"));
		vo.setMemberNick(request.getParameter("memberNickname"));
		vo.setMemberEmail(request.getParameter("memberEmail"));
		vo.setMemberAuthor("USER");
		//vo.setMemberImg(request.getParameter("memberImg"));
		vo.setMemberGit(request.getParameter("memberGit"));

		int n = dao.memberInsert(vo);

		if (n != 0) {
			request.setAttribute("message", "회원가입이 정상적으로 처리되었습니다.");
			return "ajax:ok";
		} else {
			request.setAttribute("message", "회원가입 실패!");
			return "ajax:not ok";
		}

	}

}
