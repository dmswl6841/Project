package com.team.prj.scrap.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScrapVO {
	private int scrapNo;
	private int memberNo; //member테이블 no
	private int boardNo; //board테이블 no
	private int studyNo; //study테이블 no
	private String studyDate; //스크랩 날짜
}
