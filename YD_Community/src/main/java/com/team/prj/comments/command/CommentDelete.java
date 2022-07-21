package com.team.prj.comments.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.comments.service.CommentService;
import com.team.prj.comments.service.CommentServiceImpl;
import com.team.prj.comments.vo.CommentsVO;
import com.team.prj.common.Command;

public class CommentDelete implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		CommentService dao = new CommentServiceImpl();
		String bNo = request.getParameter("boardNo");
		String cNo = request.getParameter("commentNo");
		int n = dao.commentDelete(Integer.parseInt(cNo));
		String msg = "";
		if (n > 0) {
			msg = bNo;
		}
		return "ajax:" + msg;
	}

}
