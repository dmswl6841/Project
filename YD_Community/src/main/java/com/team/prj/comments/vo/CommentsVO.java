package com.team.prj.comments.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentsVO {
	private int commentNo;
	private int commentBoard; //board테이블의 no, 외래키
	private String commentWriter; //댓글 작성자
	private String commentContent; //댓글 내용
	private String commentDate; //댓글 작성일자
}
