package com.team.prj.board.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team.prj.board.service.BoardService;
import com.team.prj.board.serviceImpl.BoardServiceImpl;
import com.team.prj.board.vo.BoardVO;
import com.team.prj.comments.service.CommentService;
import com.team.prj.comments.service.CommentServiceImpl;
import com.team.prj.comments.vo.CommentsVO;
import com.team.prj.common.Command;
import com.team.prj.member.vo.MemberVO;

public class BoardView implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 글 1개보기
		HttpSession session = request.getSession();
		int memberNo = 0;
		if (session.getAttribute("member") != null) {
			MemberVO member = (MemberVO) session.getAttribute("member");
			memberNo = member.getMemberNo();
		}

		int board_no = Integer.parseInt(request.getParameter("board_no"));
		BoardService boardDao = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		vo = boardDao.boardView(board_no);

		CommentService dao = new CommentServiceImpl();
		// 댓글 가져오기
		List<CommentsVO> list = dao.commentList(board_no);
		// 댓글 수 
		int count = dao.commentCount(board_no);

		request.setAttribute("commentList", list);
		request.setAttribute("vo", vo);
		request.setAttribute("memberNo", memberNo);
		request.setAttribute("count", count);
		return "board/boardView";

	}

}