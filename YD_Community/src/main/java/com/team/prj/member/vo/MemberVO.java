package com.team.prj.member.vo;

import com.team.prj.study.vo.StudyVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {
	private int memberNo;
	private String memberId; //유저 Id
	private String memberPw; //유저 pw
	private String memberName; //유저이름
	private String memberEmail; //유저 email
	private String memberNick; //유저 닉네임
	private String memberGit; //유저 깃주소
	private int memberWarning; //유저 경고횟수
	private String memberAuthor; //유저 권한
	private String memberJoin; //유저 가입여부
	
	
	 
}
