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
	
	
	/////////////////////////////////////////////////////////////////////////////
	///////////////////게시판 공통기능 (글 입력, 글 1개 보기, 글 검색)////////////////////
	@Override
	public int boardInsert(BoardVO vo) {
		//글입력
		int n =0;
		String sql = "INSERT INTO BOARD VALUES(board_seq.nextval,?,?,?,?)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			//board_no 컬럼 생략됨 대신 notice_seq.nextval가 게시글 고유번호를 1씩 증가로 설정해줄거임
			psmt.setString(1, vo.getBoardTitle());
			psmt.setString(2, vo.getBoardSubject());
			psmt.setString(3, vo.getBoardAttech());
			psmt.setString(4, vo.getBoardAttechDir());
			psmt.setString(5, vo.getBoardWriter());
			psmt.setString(6, vo.getBoardDate());
			psmt.setString(7, vo.getBoardCategory());
			n = psmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}
	
	@Override
	public BoardVO boardSelect(BoardVO vo) {
		//글 1개 클릭하여 조회
		String sql = "SELECT * FROM BOARD WHERE BOARD_NO = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getBoardNo());
			rs = psmt.executeQuery();
				if(rs.next()) {
					vo.setBoardNo(rs.getInt("board_no"));
					vo.setBoardTitle(rs.getString("board_title"));
					vo.setBoardSubject(rs.getString("board_subject"));
					vo.setBoardAttech(rs.getString("board_attech"));
					vo.setBoardWriter(rs.getString("board_writer"));
					vo.setBoardDate(rs.getString("board_date"));
					vo.setBoardScrap(rs.getInt("board_scrap"));
					vo.setBoardHit(rs.getInt("board_hit"));
					vo.setBoardCategory(rs.getString("board_category"));	
				}   
				
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}
	


	@Override
	public List<BoardVO> boardSearchList(String categorykey, String categoryval, String searchkey, String searchval) {
		// 글 검색기능                  //카테고리 선택박스 (전체, 자유, qna, 인기)  세부분류 선택박스 (글쓴이, 제목 포함단어...)             
		List<BoardVO> boardlist = new ArrayList<>();
		BoardVO vo;
		String sql = "SELECT * FROM BOARD WHERE ? LIKE %?% AND ? LIKE %?% ORDER BY BOARD_NO DESC";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, categorykey);
			psmt.setString(2, categoryval);
			psmt.setString(3, searchkey);
			psmt.setString(4, searchval);
			rs = psmt.executeQuery(); 
			
			while(rs.next()) {
				vo = new BoardVO();	//검색해서 존재하면 vo인스턴스를 초기화
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardDate(rs.getString("board_date"));
				vo.setBoardScrap(rs.getInt("board_scrap"));
				vo.setBoardHit(rs.getInt("board_hit"));
				vo.setBoardCategory(rs.getString("board_category"));
				vo.setMemberNo(rs.getInt("member_no"));
				boardlist.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return boardlist;

	}
	
	
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////Total 커뮤니티 전체 메인 페이지//////////////////////////
	@Override
	public List<BoardVO> TboardSelectList() {
		//전체조회
		List<BoardVO> boardlist = new ArrayList<>();
		BoardVO vo;

		String sql = "SELECT * FROM BOARD ORDER BY BOARD_ID DESC";
		
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
				boardlist.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return boardlist;
	}
	
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////Free 자유게시판//////////////////////////////
	@Override
	public List<BoardVO> FboardSelectList() {
		//전체조회
				List<BoardVO> boardlist = new ArrayList<>();
				BoardVO vo;
				String sql = "SELECT * FROM BOARD WHERE board_category like '자유' ORDER BY BOARD_NO DESC";
				
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
						boardlist.add(vo);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					close();
				}
				return boardlist;
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
		String sql = "SELECT * FROM BOARD WHERE board_category like'QnA' ORDER BY BOARD_NO DESC";
		
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
