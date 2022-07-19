package com.team.prj.manager.service;

import java.util.List;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.comments.vo.CommentsVO;
import com.team.prj.member.vo.MemberVO;
import com.team.prj.xword.vo.XwordVO;

public interface ManagerService {
	//회원관리
	List<MemberVO> memberSelectList(); //회원 전체 조회
	List<MemberVO> memberSearchList(String key, String val); //회원 목록 검색
	int memberUpdateAuthor(MemberVO vo); //회원 가입 승인
	int memberDelete(MemberVO vo); //회원 삭제(탈퇴)
	
	//게시글 관리
	List<BoardVO> boardSelectList(); //게시글 전체 조회
	List<BoardVO> boardSearchList(String key, String val); //게시글 목록 검색
	
	//댓글 관리
	List<CommentsVO> commentsSelectList(); //댓글 전체 조회
	List<CommentsVO> commentsSearchList(String key, String val); //댓글 목록 검색
	
	
	//금지어 관리
	List<XwordVO> XwordSelectList(); //금지어 전체 조회
	List<XwordVO> XwordSearchList(String key, String val); //금지어 목록 검색
	
	int XwordInsert(XwordVO vo); // 금지어 입력
	int XwordDelete(XwordVO vo); // 금지어 삭제
	int XwordUpdate(XwordVO vo); // 금지어 수정
	
}
