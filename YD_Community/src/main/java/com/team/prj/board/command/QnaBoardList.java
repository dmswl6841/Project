package com.team.prj.board.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team.prj.board.service.BoardService;
import com.team.prj.board.serviceImpl.BoardServiceImpl;
import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;
import com.team.prj.paging.Criteria;
import com.team.prj.paging.PageVO;

public class QnaBoardList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 목록 가져오기
		BoardService boardDao = new BoardServiceImpl();
		int pageNum = 1;
		if(request.getParameter("pageNum") != null) {
			pageNum = Integer.valueOf(request.getParameter("pageNum"));
		}
		
		String searchKeyword = "";
		if(request.getParameter("searchKeyword") != null) {
			searchKeyword = request.getParameter("searchKeyword");
		}
				
		String searchType = "board_title";
		if(request.getParameter("searchType") != null) {
			searchType = request.getParameter("searchType");
		}
		
		int amount = 5;
        Criteria cri = new Criteria();
        cri.setPageNum(pageNum);
        cri.setAmount(amount);
		cri.setKeyword(searchKeyword);
		cri.setSearchType(searchType);
			
		
		List<BoardVO> qnaBoardList = boardDao.QboardSelectList(cri);
		System.out.println(qnaBoardList);
		int total = boardDao.fTotalBoardCount(cri);
		System.out.println(total);
		PageVO page = new PageVO(cri, total);

		request.setAttribute("freeBoardList", qnaBoardList);
		request.setAttribute("page", page);
		return "board/qnaBoardList";
		
		
//		BoardService boardDao = new BoardServiceImpl();
//		List<BoardVO> qnaboardlist = new ArrayList<>();
//		qnaboardlist = boardDao.QboardSelectList();
//		request.setAttribute("qnaboardlist", qnaboardlist);
//		return "board/qnaBoardList";
		
	}

}
