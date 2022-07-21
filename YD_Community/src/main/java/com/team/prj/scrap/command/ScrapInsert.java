package com.team.prj.scrap.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.board.service.BoardService;
import com.team.prj.board.serviceImpl.BoardServiceImpl;
import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;
import com.team.prj.scrap.service.ScrapService;
import com.team.prj.scrap.serviceImpl.ScrapServiceImpl;
import com.team.prj.scrap.vo.ScrapVO;

public class ScrapInsert implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {

		// 1. 먼저 DB에 스크랩 누른사람 정보저장 (중복방지부터 체크한다)

		ScrapService scrapDao = new ScrapServiceImpl();
		int n = 0;
		ScrapVO vo = new ScrapVO();

		vo.setBoardNo(Integer.parseInt(request.getParameter("board_no")));
		vo.setMemberNo(Integer.parseInt(request.getParameter("member_no")));
		n = scrapDao.scrapInsert(vo);

		if (n != 0) { // 2. 1에서 중복 스크랩 아닌 사용자일 때 게시글에 스크랩수 카운트
			int y = 0; // 카운트업뎃

			int board_no = (Integer.parseInt(request.getParameter("board_no")));
			System.out.println(board_no);
			BoardVO vo2 = new BoardVO();
			vo2.setBoardNo(board_no);
			y = scrapDao.scrapUpdate(vo2);

			if (y != 0) {
				request.setAttribute("message", "스크랩성공.");

				// 글 1개보기
				board_no = Integer.parseInt(request.getParameter("board_no"));
				BoardService boardDao = new BoardServiceImpl();
				vo2 = boardDao.boardView(board_no);
				request.setAttribute("vo", vo2);
			}

		} else {
			System.out.println("중복 스크랩 불가!!");

			// 글 1개보기
			int board_no = Integer.parseInt(request.getParameter("board_no"));
			BoardService boardDao = new BoardServiceImpl();
			BoardVO vo2 = new BoardVO();
			vo2.setBoardNo(board_no);
			vo2 = boardDao.boardView(board_no);
			request.setAttribute("vo", vo2);
		}

		return "board/boardView";
	}

}
