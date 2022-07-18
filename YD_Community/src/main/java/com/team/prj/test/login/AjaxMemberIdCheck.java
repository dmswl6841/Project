package com.team.prj.test.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.common.Command;

public class AjaxMemberIdCheck implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// ajax를 이용한 아이디 중복체크
		MemberService memberDao = new MemberServiceImpl();
		String id = request.getParameter("id");
		boolean b = memberDao.isMemberIdCheck(id); //true 사용가능, 
		String result = "Used";
		if(!b) {
			result = "Un Used";
		}
		return "ajax:"+result;  //결과가 ajax호출이라는 걸뷰리졸브에게 알려주기 위해
	}

}
