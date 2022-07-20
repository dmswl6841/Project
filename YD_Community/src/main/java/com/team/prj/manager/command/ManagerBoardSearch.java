package com.team.prj.manager.command;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.Command;
import com.team.prj.manager.service.ManagerService;
import com.team.prj.manager.service.ManagerServiceImpl;
import com.team.prj.member.vo.MemberVO;

public class ManagerBoardSearch implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//게시글 검색
				ManagerService managerDao = new ManagerServiceImpl();
				List<BoardVO> list = new ArrayList<BoardVO>();
				ObjectMapper mapper = new ObjectMapper();
				String key = request.getParameter("key");
				String val = request.getParameter("val");
				
				list = managerDao.managerBoardSearch(key, val);
				String jsonList = null;
				try {
					jsonList = mapper.writeValueAsString(list);
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				}
				return "ajax:" + jsonList;
	}

}
