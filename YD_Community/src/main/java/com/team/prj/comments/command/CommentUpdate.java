package com.team.prj.comments.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.comments.service.CommentServiceImpl;
import com.team.prj.comments.vo.CommentsVO;
import com.team.prj.common.Command;

public class CommentUpdate implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		int cNo= Integer.parseInt(request.getParameter("cNo"));
		int bNo= Integer.parseInt(request.getParameter("bNo"));
		String con = request.getParameter("con");
		
		CommentsVO vo = new CommentsVO();
		vo.setCommentNo(cNo);
		vo.setBoardNo(bNo);
		vo.setCommentContent(con);
		new CommentServiceImpl().commentUpdate(vo);
		
		return "ajax:" + bNo;
		//return "boardView.do?board_no=" + bNo // 안되서 아작스로 바꿈 ㅜ
	}

}