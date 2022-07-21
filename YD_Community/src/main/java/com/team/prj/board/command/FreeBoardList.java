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


public class FreeBoardList implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		BoardService boardDao = new BoardServiceImpl();
		// 게시글 목록 가져오기
					int pageNum = 1;
					System.out.println("pageNum" + request.getParameter("pageNum"));
					if(request.getParameter("pageNum") != null) {
						System.out.println("enter if code");
						System.out.println("if pageNum : " + request.getParameter("pageNum"));
						pageNum = Integer.valueOf(request.getParameter("pageNum"));
					}
//					int pageNum = 1;
					
					String searchKeyword = "";
					if(request.getParameter("searchKeyword") != null) {
						System.out.println("enter keyword");
						searchKeyword = request.getParameter("searchKeyword");
					}
					System.out.println(searchKeyword);
							
//					String searchKeyword = "테스트";
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
						
					
					List<BoardVO> freeBoardList = boardDao.FboardSelectList(cri);
					System.out.println(freeBoardList);
					int total = boardDao.fTotalBoardCount(cri);
					System.out.println(total);
					PageVO page = new PageVO(cri, total);

					request.setAttribute("freeBoardList", freeBoardList);
					request.setAttribute("page", page);
					return "board/freeBoardList";
					
					
				}

			}