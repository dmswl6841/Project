package com.team.prj.board.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.prj.board.service.BoardService;
import com.team.prj.board.serviceImpl.BoardServiceImpl;
import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;


public class AjaxBoardSearche implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		// 게시글 검색
		BoardService boardDao = new BoardServiceImpl();
		List<BoardVO> searchboardlist = new ArrayList<BoardVO>();
		ObjectMapper mapper = new ObjectMapper();  //jackson 라이브러리 사용(json)
		String key = request.getParameter("key");
		String val = request.getParameter("val");
		
		searchboardlist = boardDao.boardSearchList(key, val);
		String jsonList = null;
		try {
			jsonList = mapper.writeValueAsString(searchboardlist);  //객체가 json 타입의 스트링으로 변환
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}	
		
		return "ajax:"+jsonList;
	}

}
