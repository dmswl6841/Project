package com.team.prj.scrap.vo;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.study.vo.StudyVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScrapVO {
	private int scrapNo;
	private int memberNo; //member테이블 no
	private int boardNo; //board테이블 no
	private int studyNo; //study테이블 no
	private String scrapDate; //스크랩 날짜

	//마이페이지 조인용입니다
	BoardVO bvo = new BoardVO();
	StudyVO svo = new StudyVO();

}
