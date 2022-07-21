package com.team.prj.manager.service;

import java.util.List;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.comments.vo.CommentsVO;
import com.team.prj.member.vo.MemberVO;
import com.team.prj.xword.vo.XwordVO;

public interface ManagerService {
	//회원관리
	List<MemberVO> managerMemberSelectAll(); //회원 전체 조회
	List<MemberVO> managerMemberSearch(String key, String val); //회원 목록 검색
	int managerMemberDelete(MemberVO vo); //회원 삭제(탈퇴)
	int memberUpdateAuthor(String key, String val); //회원 가입 승인
	
	//게시글 관리
	List<BoardVO> managerBoardSelectAll(); //게시글 전체 조회
	List<BoardVO> managerBoardSearch(String key, String val); //게시글 목록 검색
	int managerBoardDelete(BoardVO vo); //게시글 삭제
	
	//댓글 관리
	List<CommentsVO> managerCommentsSelectAll(); //댓글 전체 조회
	List<CommentsVO> managerCommentsSearch(String key, String val); //댓글 목록 검색
	int managerCommentsDelete(CommentsVO vo);
	
	//금지어 관리
	List<XwordVO> XwordSelectAll(); //금지어 전체 조회
	List<XwordVO> XwordSearch(String key); //금지어 목록 검색
	
	int XwordInsert(String key); // 금지어 입력
	int XwordDelete(XwordVO vo); // 금지어 삭제
	int XwordUpdate(String key, String val); // 금지어 수정
	XwordVO XwordSelectOne(String key); //금지어 단건 검색
	
}
