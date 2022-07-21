package com.team.prj.scrap.service;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.recommend.vo.RecommendVO;
import com.team.prj.scrap.vo.ScrapVO;

public interface ScrapService {
	
	//추천 버튼 누르면 해당 게시글 추천수 1 추가 (글1개조회 참고) 
	int scrapUpdate(BoardVO vo); //board_scrap카운트 수정
	
	//중복 방지용 member_no&board_no 키 추가
	int scrapInsert(ScrapVO vo);

}
