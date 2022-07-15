package com.team.prj.board.service;

import java.util.List;

import com.team.prj.board.vo.BoardVO;

public interface BoardService {
	
		//게시판 공통기능 (글 입력, 글 1개 보기, 글 검색)
		int boardInsert(BoardVO vo); //글입력
		BoardVO boardSelect(BoardVO vo);	//글 1개 클릭하여 조회
		List<BoardVO> boardSearchList(String key,String val); //글 목록에서 검색
		
	    //Total 커뮤니티 전체 메인 페이지 crud
		List<BoardVO> TboardSelectList(); //전체조회
	
		//Free 자유게시판 crud
		List<BoardVO> FboardSelectList(); //전체조회
		int FboardDelete(BoardVO vo); //글삭제
		int FboardUpdate(BoardVO vo); //글수정
		
		//Qna 질문게시판 crud
		List<BoardVO> QboardSelectList(); //전체조회
		int QboardDelete(BoardVO vo); //글삭제
		int QboardUpdate(BoardVO vo); //글수정
		
		//Hot 주간 인기글 게시판 crud (인기글 게시판으로 올라가면 수정은 불가능해짐)

		List<BoardVO> HboardSelectList(); //전체조회
		int HboardDelete(BoardVO vo); //글삭제

}
