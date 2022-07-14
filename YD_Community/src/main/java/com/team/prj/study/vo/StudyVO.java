package com.team.prj.study.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyVO {
	private int StudyNo;
	private String StudyTitle; //제목
	private String StudySubject; //내용
	private String StudyAttech; //첨부파일
	private String StudyAttechDir; //첨부파일 위치
	private String StudyWriter; //작성자
	private String StudyDate; //작성일자
	private String StudyStartDate; //모집시작일자
	private String StudySystem; //진행방식
	private String StudyPeriod; //진행기간
	private String StudyMember; //인원 수
	private String StudyLanguage; //사용기술
	private int StudyScrap; //스터디 스크랩
}
