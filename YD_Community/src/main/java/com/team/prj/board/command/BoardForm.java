package com.team.prj.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.common.Command;

public class BoardForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//게시글 작성폼 호출
		return "board/boardForm";
	}

}
