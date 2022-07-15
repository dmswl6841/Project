package com.team.prj.board.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team.prj.board.service.BoardService;
import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.DataSource;

import co.micol.prj.notice.vo.NoticeVO;

public class BoardServiceImpl implements BoardService {

	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	
	
	///////////////////게시판 공통기능 (글 입력, 글 1개 보기, 글 검색)////////////////////
	@Override
	public int boardInsert(BoardVO vo) {
		//글입력
		return 0;
	}
	@Override
	public BoardVO boardSelect(BoardVO vo) {
		//글 1개 클릭하여 조회
		return null;
	}
	@Override
	public List<BoardVO> boardSearchList(String key, String val) {
		//글 목록에서 검색
		return null;
	}
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////Total 커뮤니티 전체 메인 페이지//////////////////////////
	@Override
	public List<BoardVO> TboardSelectList() {
		//전체조회
		List<BoardVO> Totallist = new ArrayList<>();
		BoardVO vo;
		String sql = "SELECT * FROM BOARD ORDER BY BOARD_ID DESC";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return Totallist;
	}
	
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////Free 자유게시판//////////////////////////////
	@Override
	public List<BoardVO> FboardSelectList() {
		//전체조회
		return null;
	}
	@Override
	public int FboardDelete(BoardVO vo) {
		//글삭제
		return 0;
	}
	@Override
	public int FboardUpdate(BoardVO vo) {
		//글수정
		return 0;
	}
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////Qna 질문게시판///////////////////////////////
	@Override
	public List<BoardVO> QboardSelectList() {
		//전체조회
		return null;
	}
	@Override
	public int QboardDelete(BoardVO vo) {
		//글삭제
		return 0;
	}
	@Override
	public int QboardUpdate(BoardVO vo) {
		//글수정
		return 0;
	}
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////
	/////////Hot 주간 인기글 게시판 crud (인기글 게시판으로 올라가면 수정 불가해짐)///////////
	@Override
	public List<BoardVO> HboardSelectList() {
		//전체조회
		return null;
	}
	@Override
	public int HboardDelete(BoardVO vo) {
		//글삭제
		return 0;
	}
	
}
