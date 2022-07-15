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
		List<BoardVO> totallist = new ArrayList<>();
		BoardVO vo;
		String sql = "SELECT * FROM BOARD ORDER BY BOARD_NO DESC";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardDate(rs.getString("board_date"));
				vo.setBoardScrap(rs.getInt("board_scrap"));
				vo.setBoardHit(rs.getInt("board_hit"));
				vo.setBoardCategory(rs.getString("board_category"));
				vo.setMemberNo(rs.getInt("member_no"));
				totallist.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return totallist;
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
		List<BoardVO> qnalist = new ArrayList<>();
		BoardVO vo;
		String sql = "SELECT * FROM BOARD ORDER BY BOARD_NO DESC";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardDate(rs.getString("board_date"));
				vo.setBoardScrap(rs.getInt("board_scrap"));
				vo.setBoardHit(rs.getInt("board_hit"));
				vo.setBoardCategory(rs.getString("board_category"));
				vo.setMemberNo(rs.getInt("member_no"));
				qnalist.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return qnalist;
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
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////
	private void close() {
		try {
			if(rs != null) rs.close();
			if(psmt !=null)psmt.close();
			if(conn != null) conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
