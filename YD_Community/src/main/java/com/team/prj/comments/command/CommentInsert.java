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
		vo.setBoardNo(Integer.valueOf(request.getParameter("boardNo")));
		vo.setCommentWriter(request.getParameter("commentWriter"));
		vo.setCommentContent(request.getParameter("commentContent"));

		ObjectMapper mapper = new ObjectMapper();
		String jsonList = null;
		try {
			jsonList = mapper.writeValueAsString(dao.commentInsert(vo));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ajax:" + jsonList;
	}

}
