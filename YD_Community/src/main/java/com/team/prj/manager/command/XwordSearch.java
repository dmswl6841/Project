package com.team.prj.manager.command;

import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team.prj.common.Command;
import com.team.prj.manager.service.ManagerService;
import com.team.prj.manager.service.ManagerServiceImpl;
import com.team.prj.xword.vo.XwordVO;

public class XwordSearch implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		ManagerService managerDao = new ManagerServiceImpl();
		List<XwordVO> list = new ArrayList<XwordVO>();
		ObjectMapper mapper = new ObjectMapper();
		String key = request.getParameter("key");
		
		list = managerDao.XwordSearch(key);
		String jsonList = null;
		try {
			jsonList = mapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "ajax:" + jsonList;
	}

}
