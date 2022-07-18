package com.team.prj.test.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team.prj.common.Command;
import com.team.prj.member.vo.MemberVO;


public class MemberLogin implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		MemberService memberService = new MemberServiceImpl();
		String id = request.getParameter("memberId");
		String password = request.getParameter("memberPassword");
		// 로그인 처리
		//System.out.println("id: "+id);
		HttpSession session = request.getSession();  //서버가 만들어 놓은 세션을 가져온다.
		MemberVO member = new MemberVO();
		member.setMemberId(id);
		member.setMemberPw(password);
		member = memberService.memberLogin(member);
	
		if(member != null) {
			session.setAttribute("member", member);
			request.setAttribute("message", "login Success!");
			
		}else {
			request.setAttribute("message", "아이디 또는 비번 틀렷삼");
		}
//		if(member.getMemberName() != null) {
//			session.setAttribute("member", vo.getMemberNo());   
//			session.setAttribute("id", vo.getMemberId());   
//			session.setAttribute("author", vo.getMemberAuthor());
//			session.setAttribute("name", vo.getMemberName());
//			request.setAttribute("message",vo.getMemberName() + "님 환영합니다.");
//		}else {
//			request.setAttribute("message", "아이디 또는 패스워드가 일치하지 않습니다.");
//		}
		return "test/memberLogin";
		//return "main.do";
	}
	


}
