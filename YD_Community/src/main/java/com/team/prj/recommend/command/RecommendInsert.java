package com.team.prj.recommend.command;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.board.service.BoardService;
import com.team.prj.board.serviceImpl.BoardServiceImpl;
import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;
import com.team.prj.recommend.service.RecommendService;
import com.team.prj.recommend.serviceImpl.RecommendServiceImpl;
import com.team.prj.recommend.vo.RecommendVO;

public class RecommendInsert implements Command{

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		
		//1. 먼저 DB에 추천누른사람 정보저장 (중복방지부터 체크한다)
		
		RecommendService recommendDao = new RecommendServiceImpl();
		int n = 0; 
		RecommendVO vo = new RecommendVO();
		
		vo.setBoardNo(Integer.parseInt(request.getParameter("board_no")));
		vo.setMemberNo(Integer.parseInt(request.getParameter("member_no")));
		//vo.setRecommendNo(Integer.parseInt(request.getParameter("recommendNo")));
		n = recommendDao.recommendInsert(vo);
		
		if (n !=0) { //2. 1에서 중복 추천이 아닌 사용자일 때 게시글에 추천수 카운트
			
			int y = 0; //추천 카운트업뎃
			
			int board_no =(Integer.parseInt(request.getParameter("board_no")));
			System.out.println(board_no);
			BoardVO vo2 = new BoardVO();
			vo2.setBoardNo(board_no);
			y = recommendDao.recommendUpdate(vo2);
			
			if(y !=0) {
				request.setAttribute("message", "추천성공.");
				
				// 글 1개보기
				board_no = Integer.parseInt(request.getParameter("board_no"));
				BoardService boardDao = new BoardServiceImpl();
				vo2 = boardDao.boardView(board_no);
				request.setAttribute("vo", vo2);
				
			}
			
		} else {
			System.out.println("중복 추천 불가!!");	
			
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
