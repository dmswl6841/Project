package com.team.prj.board.service;

import java.util.List;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.paging.Criteria;


public interface BoardService {
	


	
		int fTotalBoardCount(Criteria cri);//페이징 카운트 조회
<<<<<<< HEAD
		int NoticeBoardCount(Criteria cri);
		int QboardBoardCount(Criteria cri);
=======
int QboardBoardCount(Criteria cri) ;
int NoticeBoardCount(Criteria cri);
>>>>>>> branch 'master' of https://github.com/dmswl6841/Project.git

		//게시판 공통기능 (글 입력, 글 1개 보기, 글 검색)
		int boardInsert(BoardVO vo); //글입력
		//BoardVO boardSelect(BoardVO vo);	
		
		//글 1개 클릭하여 조회
		//List<BoardVO> boardViewList(int board_no);
		BoardVO boardView(int board_no);
		
		
		int boardDelete(BoardVO vo); //글삭제
		int boardUpdate(BoardVO vo); //글수정
		List<BoardVO> boardSearchList(String key, String val); 
		//카테고리 선택박스 (전체, 공지, 자유, qna, 인기) 세부분류 선택박스 (글쓴이, 제목 포함단어...)     
	
		//total 메인
		List<BoardVO> TboardSelectList(); //전체조회

		

		//Free 자유게시판 crud
		List<BoardVO> FboardSelectList(Criteria cri); //전체조회  

		

		//Qna 질문게시판 crud
		List<BoardVO> QboardSelectList(Criteria cri); //전체조회
		
		//Notice 공지 게시판 crud
		List<BoardVO> NboardSelectList(Criteria cri); //전체조회
		
		
		//Hot 인기글 게시판 crud (인기글 게시판으로 올라가면 수정은 불가능해짐)
		List<BoardVO> HboardSelectList(); //전체조회
		
		//인기글 업뎃
		int HboardUpdate(BoardVO vo); //글수정
}
