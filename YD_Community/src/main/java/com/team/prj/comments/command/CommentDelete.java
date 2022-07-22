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
		String msg = null;
		String cNo = request.getParameter("commentNo");
		dao.commentDelete(Integer.parseInt(cNo));
		
		if (request.getParameter("boardNo") != null) {
			msg = request.getParameter("boardNo");
		} else if (request.getParameter("studyNo") != null) {
			msg = request.getParameter("studyNo");
		}

		return "ajax:" + msg;
	}

}