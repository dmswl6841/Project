package com.team.prj.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.common.Command;

public class MyPage implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// my Page 출력
		return "member/myPage";
	}

}
