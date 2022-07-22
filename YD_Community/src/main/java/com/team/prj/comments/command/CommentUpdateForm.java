package com.team.prj.comments.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.comments.service.CommentServiceImpl;
import com.team.prj.comments.vo.CommentsVO;
import com.team.prj.common.Command;

public class CommentUpdateForm implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {

		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		CommentsVO vo = new CommentServiceImpl().commentSelectOne(commentNo);
		request.setAttribute("vo", vo);
		
		return "comments/commentUpdateForm";
	}

}