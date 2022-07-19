package com.team.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.common.Command;
import com.team.prj.member.service.MemberService;
import com.team.prj.member.service.MemberServiceImpl;

public class AjaxMemberNicknameCheck implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		MemberService dao = new MemberServiceImpl();
		String nickname = request.getParameter("nickname");
		if (dao.isMemberNickname(nickname)) {
			return "ajax:able";
		}
		return "ajax:unable";
	}
}
