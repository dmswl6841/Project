package com.team.prj.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.board.service.BoardService;
import com.team.prj.board.serviceImpl.BoardServiceImpl;
import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;

public class BoardUpdate implements Command{

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 글 쓰기
		BoardService boardDao = new BoardServiceImpl();
		BoardVO vo = new BoardVO();
		vo.setBoardTitle(request.getParameter("boardTitle"));
		vo.setBoardSubject(request.getParameter("boardSubject"));
		vo.setBoardAttech(request.getParameter("boardAttech"));
		vo.setBoardAttechDir(request.getParameter("boardAttechDir"));
		vo.setBoardWriter(request.getParameter("boardWriter"));
		vo.setBoardDate(request.getParameter("boardDate")); 
		vo.setBoardCategory(request.getParameter("boardCategory"));
		vo.setBoardScrap(Integer.parseInt(request.getParameter("boardScrap")));   
		vo.setBoardHit(Integer.parseInt(request.getParameter("boardHit")));     
		vo.setBoardRecommend(Integer.parseInt(request.getParameter("boardRecommend")));
		vo.setMemberNo(Integer.parseInt(request.getParameter("MemberNo")));    

		int n = boardDao.boardInsert(vo);
		
		if(n != 0) {
			request.setAttribute("message", "글이 정상적으로 등록되었습니다.");
		}else {
			request.setAttribute("message", "글 등록에 실패했습니다.");
		}
		return "board/freeBoardForm";
	}

}
