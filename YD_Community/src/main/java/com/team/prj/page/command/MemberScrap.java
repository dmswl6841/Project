package com.team.prj.page.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team.prj.common.Command;
import com.team.prj.member.vo.MemberVO;
import com.team.prj.page.serviceImpl.PageServiceImpl;
import com.team.prj.scrap.vo.ScrapVO;

public class MemberScrap implements Command {

	@Override
	public String exec(HttpServletRequest request, HttpServletResponse response) {
		//내가 스크랩한 글
		PageServiceImpl pageService = new PageServiceImpl();
		HttpSession session = request.getSession();
		//MemberVO member = (MemberVO) session.getAttribute("member");
		
		//int memberNo = member.getMemberNo();
		
		int memberNo = Integer.parseInt(request.getParameter("no")); // 희수가 남에거 보는거때문에 추가
		
		//int memberNo = (int) request.getAttribute("memberNo");
		List<ScrapVO> list = pageService.myScrapList(memberNo);
		request.setAttribute("list", list);
		
		
		
		return "page/memberScrap";
	}

}
