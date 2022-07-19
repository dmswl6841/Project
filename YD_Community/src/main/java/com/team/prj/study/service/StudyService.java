package com.team.prj.study.service;

import java.util.List;

import com.team.prj.study.vo.StudyVO;

public interface StudyService {
//CRUD기본으로 구성
	
	List<StudyVO> studySelectList(); //전체조회
	
	int studyInsert(StudyVO vo); //글 입력
	int studyDelete(StudyVO vo); //글 삭제
	int studyUpdate(StudyVO vo); //글 수정
	StudyVO studyViewList(int study_no);//단건조회
	
	List<StudyVO> studySearchList(String key, String val); //글 목록에서 검색하기

	
}
