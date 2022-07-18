package com.team.prj.study.vo;







import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudyVO {
	private int StudyNo;
	public String StudyTitle; //제목1
	private String StudySubject; //내용2
	private String StudyWriter; //작성자3
	private String StudyDate; //작성일자4
	private String StudySystem; //진행방식6
	private String StudyPeriod; //진행기간7
	private String StudyMember; //인원 수8
	private String StudyLanguage; //사용기술9
	private String StudyAttech;//첨부파일10
	private String StudyAttechDir;//첨부파일 위치11
	private int memberNo; //member fk
	
		

}
