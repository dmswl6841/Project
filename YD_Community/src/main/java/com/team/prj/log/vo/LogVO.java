package com.team.prj.log.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogVO {
	private int logNo;
	private int memberNo; //member테이블의 no, 외래키
	private int boardNo; //board테이블의 no, 외래키
	private int commentNo; //comment테이블의 no, 외래키
	private String category; //말머리
	private String logDate; //활동날짜
}
