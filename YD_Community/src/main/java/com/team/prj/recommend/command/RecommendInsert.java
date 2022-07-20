package com.team.prj.recommend.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;
import com.team.prj.recommend.service.RecommendService;
import com.team.prj.recommend.serviceImpl.RecommendServiceImpl;
import com.team.prj.recommend.vo.RecommendVO;

public class RecommendInsert implements Command{

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter writer;
		//DB에 추천누른사람 정보저장 (중복방지)
		
		RecommendService recommendDao = new RecommendServiceImpl();
		int n = 0; 
		RecommendVO vo = new RecommendVO();
		vo.setBoardNo(Integer.parseInt(request.getParameter("board_no")));
		vo.setMemberNo(Integer.parseInt(request.getParameter("member_no")));
		//vo.setRecommendNo(Integer.parseInt(request.getParameter("recommendNo")));
		n = recommendDao.recommendInsert(vo);
		
		if (n !=0) {
			
			int y = 0; //추천 카운트업뎃
			BoardVO vo2 = new BoardVO();
			
			int board_no =(Integer.parseInt(request.getParameter("board_no")));
			System.out.println(board_no);
			vo2.setBoardNo(board_no);
			y = recommendDao.recommendUpdate(vo2);
			
			if(y !=0) {
				request.setAttribute("message", "추천성공.");
				
				
	
//				try {
//					writer = response.getWriter();
//					writer.println("<script>alert('추천되었습니다!'); location.href='"+ "boardView.do" +"';</script>"); 
//					writer.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
				
			}
			
		} else {
			System.out.println("중복 추천 불가!!");
			
//			try {
//				writer = response.getWriter();
//				writer.println("<script>alert('이미 추천하셨습니다.'); location.href='"+ "boardView.do" +"';</script>"); 
//				writer.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			
			
		}
		
		return "freeBoardList.do";
	}

}
