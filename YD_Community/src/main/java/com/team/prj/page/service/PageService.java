package com.team.prj.page.service;

import java.util.List;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.comments.vo.CommentsVO;
import com.team.prj.scrap.vo.ScrapVO;
import com.team.prj.study.vo.StudyVO;

public interface PageService {
	//List<LogVO> myLogList(int memberNo); 	//최근 활동 (로그 저장식으로 하려면 이거 써야함)
	//List<BoardVO> myLogList(int memberNo); //최근 활동 (7일 내의 작성글)
//	List<StudyVO> myStudyLogList(int memberNo); //최근 활동 (7일 내의 스터디글)

	List<BoardVO> myBoardList(int memberNo); //내가 작성한 게시글 
	List<StudyVO> myStudyBoardList(int memberNo); //내가 작성한 스터디 게시글 
	
	
	List<ScrapVO> myScrapList(int memberNo); // 스크랩한 게시글
	
	List<CommentsVO> myCommentsList(int memberNo); //내가 쓴 댓글
	
}

