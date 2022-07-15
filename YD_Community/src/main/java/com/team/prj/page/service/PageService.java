package com.team.prj.page.service;

import java.util.List;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.log.vo.LogVO;
import com.team.prj.scrap.vo.ScrapVO;
import com.team.prj.study.vo.StudyVO;

public interface PageService {
	List<LogVO> myLogList(); 	//최근 활동
	List<BoardVO> myBoardList(); //내가 작성한 게시글 
	List<ScrapVO> myScrapList(); // 스크랩한 게시글
	List<StudyVO> myStudyList(); //찜한 스터디
}

