package com.team.prj.board.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVO {
	private int boardNo;
	private String boardTitle; //게시글 제목
	private String boardSubject; //게시글 내용
	private String boardAttech; //게시글 첨부파일
	private String boardAttechDir; //첨부파일 저장위치
	private String boardWriter; //게시글 작성자
	private String boardDate; //게시글 작성일자
	private int boardScrap; //게시글 스크랩
	private int boardHit; //게시글 조회수
	private String boardCategory; //게시글 말머리
	private int memberNo; //member fk
	
}
