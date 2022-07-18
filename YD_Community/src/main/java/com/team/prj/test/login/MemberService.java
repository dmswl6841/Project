package com.team.prj.test.login;

import java.util.List;

import com.team.prj.member.vo.MemberVO;


public interface MemberService {
	List<MemberVO> memberSelectList();  //멤버전체조회  R
	MemberVO memberSelectOne(MemberVO vo); //멤버조회  R
	int memberInsert(MemberVO vo); //멤버 삽입  C
	int memberUpdate(MemberVO vo); //멤버 수정  U
	int memberDelete(MemberVO vo); //멤버 삭제  D
	
	boolean isMemberIdCheck(String id); //아이디 중복체크
	MemberVO memberLogin(MemberVO vo); //로그인 처리  R
}
