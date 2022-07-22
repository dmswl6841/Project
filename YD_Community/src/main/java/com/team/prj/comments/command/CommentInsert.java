package com.team.prj.comments.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.prj.comments.service.CommentService;
import com.team.prj.comments.service.CommentServiceImpl;
import com.team.prj.comments.vo.CommentsVO;
import com.team.prj.common.Command;

public class CommentInsert implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		CommentService dao = new CommentServiceImpl();
		CommentsVO vo = new CommentsVO();
		int no = 0;
		// board 댓글일때
		if (request.getParameter("bNo") != null) {
			vo.setBoardNo(Integer.valueOf(request.getParameter("bNo")));
			vo.setCommentWriter(request.getParameter("wr"));
			vo.setCommentContent(request.getParameter("con"));
			vo.setMemberNo(Integer.parseInt(request.getParameter("mNo")));
			vo = dao.commentInsert(vo);
			no = vo.getBoardNo();
		} else if (request.getParameter("sNo") != null) {
			// study 댓글일때
			vo.setStudyNo(Integer.valueOf(request.getParameter("sNo")));
			vo.setCommentWriter(request.getParameter("wr"));
			vo.setCommentContent(request.getParameter("con"));
			vo.setMemberNo(Integer.parseInt(request.getParameter("mNo")));
			vo = dao.commentInsertStudy(vo);
			no = Integer.parseInt(request.getParameter("sNo"));
		}
		return "ajax:" + no;
	}

}