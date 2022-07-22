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

public class NoticeBoardList implements Command {

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
			System.out.println("enter keyword");
			searchKeyword = request.getParameter("searchKeyword");
		}
		System.out.println(searchKeyword);
				
		String searchType = "board_title";
		if(request.getParameter("searchType") != null) {
			searchType = request.getParameter("searchType");
			System.out.println("searchType" + searchType);
		}
		
		int amount = 5;
        Criteria cri = new Criteria();
        cri.setPageNum(pageNum);
        cri.setAmount(amount);
		cri.setKeyword(searchKeyword);
		cri.setSearchType(searchType);
			
		
		List<BoardVO> noticeBoardList = boardDao.NboardSelectList(cri);
		int total = boardDao.NoticeBoardCount(cri);
		PageVO page = new PageVO(cri, total);

		request.setAttribute("noticeBoardList", noticeBoardList);
		request.setAttribute("page", page);
		return "board/noticeBoardList";
		
		
//		List<BoardVO> noticeboardlist = new ArrayList<>();
//		noticeboardlist = boardDao.NboardSelectList();
//		request.setAttribute("noticeboardlist", noticeboardlist);
//		return "board/noticeBoardList";
	}

}
