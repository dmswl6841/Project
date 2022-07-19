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
	///////////////////게시판 공통기능 (글 입력, 글 1개 보기, 글 1개 삭제, 글 검색)////////////////////
	@Override
	public int boardInsert(BoardVO vo) {
		//글입력
		int n =0;
		String sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL,?,?,?,?,?,sysdate,0,0,?,?,'',0)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			//board_no 컬럼 생략됨 대신 notice_seq.nextval가 게시글 고유번호를 1씩 증가로 설정해줄거임
			psmt.setString(1, vo.getBoardTitle());
			psmt.setString(2, vo.getBoardSubject());
			psmt.setString(3, vo.getBoardAttech());
			psmt.setString(4, vo.getBoardAttechDir());
			psmt.setString(5, vo.getBoardWriter());
			psmt.setString(6, vo.getBoardCategory());
			psmt.setInt(7, vo.getMemberNo());

			n = psmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}
	

	
	

	@Override
	public List<BoardVO> boardViewList(int board_no) {
		// 글 1개조회
		List<BoardVO> boardviewlist = new ArrayList<>();
		BoardVO vo = new BoardVO();
		String sql = "SELECT * FROM BOARD WHERE BOARD_NO = ?";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, board_no);
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardScrap(rs.getInt("board_scrap"));
				vo.setBoardRecommend(rs.getInt("board_recommend"));
				vo.setBoardHit(rs.getInt("board_hit"));
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardCategory(rs.getString("board_category"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardDate(rs.getString("board_date"));
				vo.setBoardAttech(rs.getString("board_attech"));
				vo.setBoardSubject(rs.getString("board_subject"));
				boardviewlist.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boardviewlist;
	}

	
	
	
	@Override
	public int boardDelete(BoardVO vo) {
		//글삭제
		int n = 0;
		String sql = "DELETE FROM BOARD WHERE BOARD_NO = ?";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getBoardNo());
			 // 쿼리 실행후 결과를 리턴
//			rs = psmt.executeQuery();
//			
//			if(rs.next()) {
//			BoardVO vo = new BoardVO();
//			vo.setBoardNo(rs.getInt("board_no"));
//			vo.setBoardTitle(rs.getString("board_title"));
//			vo.setBoardSubject(rs.getString("board_subject"));
//			vo.setBoardAttech(rs.getString("board_attech"));
//			vo.setBoardWriter(rs.getString("board_writer"));
//			vo.setBoardDate(rs.getString("board_date"));
//			vo.setBoardScrap(rs.getInt("board_scrap"));
//			vo.setBoardHit(rs.getInt("board_hit"));
//			vo.setBoardCategory(rs.getString("board_category"));	
//			vo.setBoardHot(rs.getString("board_hot"));
//			vo.setBoardRecommend(rs.getInt("board_recommend"));
			n = psmt.executeUpdate(); 
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	

	
	
	
	@Override
	public int boardUpdate(BoardVO vo) {
		//글 수정
		return 0;
	}
	


	@Override
	public List<BoardVO> boardSearchList(String categorykey, String categoryval, String searchkey, String searchval) {
		// 글 검색기능                  //카테고리 선택박스 (전체, 공지, 자유, qna, 인기)  세부분류 선택박스 (글쓴이, 제목 포함단어...)             
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
				vo.setBoardHot(rs.getString("board_hot"));
				vo.setBoardRecommend(rs.getInt("board_recommend"));
				vo.setBoardDate(rs.getString("board_attech"));
				boardlist.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return boardlist;

	}
	
	
	
	///////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////Total 게시판 메인//////////////////////////////
	
	
	
	

	@Override
	public List<BoardVO> TboardSelectList() {
		//전체조회
		List<BoardVO> totalboardlist = new ArrayList<>();
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
				vo.setBoardDate(rs.getString("board_date"));
				vo.setBoardScrap(rs.getInt("board_scrap"));
				vo.setBoardHit(rs.getInt("board_hit"));
				vo.setBoardCategory(rs.getString("board_category"));
				vo.setMemberNo(rs.getInt("member_no"));
				vo.setBoardRecommend(rs.getInt("board_recommend"));
				totalboardlist.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return totalboardlist;
	}
	
	
	
	
	///////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////Notice 공지게시판//////////////////////////////
	
	@Override
	public List<BoardVO> NboardSelectList() {
		//전체조회
				List<BoardVO> noticeboardlist = new ArrayList<>();
				BoardVO vo;
				String sql = "SELECT * FROM BOARD WHERE board_category ='공지' ORDER BY BOARD_NO DESC";
				
				try {
					conn = dao.getConnection();
					psmt = conn.prepareStatement(sql);
					rs = psmt.executeQuery();
					
					while(rs.next()) {
						vo = new BoardVO();
						vo.setBoardNo(rs.getInt("board_no"));
						vo.setBoardWriter(rs.getString("board_writer"));
						vo.setBoardTitle(rs.getString("board_title"));
						vo.setBoardDate(rs.getString("board_date"));
						vo.setBoardAttech(rs.getString("board_attech"));
						vo.setBoardScrap(rs.getInt("board_scrap"));
						vo.setBoardRecommend(rs.getInt("board_recommend"));
						vo.setBoardHit(rs.getInt("board_hit"));
						noticeboardlist.add(vo);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					close();
				}
				return noticeboardlist;
	}
	
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////Free 자유게시판//////////////////////////////
	@Override
	public List<BoardVO> FboardSelectList() {
		//전체조회
				List<BoardVO> freeboardlist = new ArrayList<>();
				BoardVO vo;
				String sql = "SELECT * FROM BOARD WHERE board_category = '자유' ORDER BY BOARD_NO DESC";
				
				try {
					conn = dao.getConnection();
					psmt = conn.prepareStatement(sql);
					rs = psmt.executeQuery();
					
					while(rs.next()) {
						vo = new BoardVO();
						vo.setBoardNo(rs.getInt("board_no"));
						vo.setBoardWriter(rs.getString("board_writer"));
						vo.setBoardTitle(rs.getString("board_title"));
						vo.setBoardDate(rs.getString("board_date"));
						vo.setBoardAttech(rs.getString("board_attech"));
						vo.setBoardScrap(rs.getInt("board_scrap"));
						vo.setBoardRecommend(rs.getInt("board_recommend"));
						vo.setBoardHit(rs.getInt("board_hit"));
						freeboardlist.add(vo);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					close();
				}
				return freeboardlist;
	}
	
	
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////Qna 질문게시판///////////////////////////////
	@Override
	public List<BoardVO> QboardSelectList() {
		//전체조회
		List<BoardVO> qnaboardlist = new ArrayList<>();
		BoardVO vo;
		String sql = "SELECT * FROM BOARD WHERE board_category ='QnA' ORDER BY BOARD_NO DESC";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new BoardVO();
				vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardDate(rs.getString("board_date"));
				vo.setBoardAttech(rs.getString("board_attech"));
				vo.setBoardScrap(rs.getInt("board_scrap"));
				vo.setBoardRecommend(rs.getInt("board_recommend"));
				vo.setBoardHit(rs.getInt("board_hit"));
				qnaboardlist.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return qnaboardlist;
	}
	
	
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////
	/////////Hot 주간 인기글 게시판 crud (인기글 게시판으로 올라가면 수정 불가해짐)///////////
	@Override
	public List<BoardVO> HboardSelectList() {
		//전체조회
		List<BoardVO> hotboardlist = new ArrayList<>();
		BoardVO vo;
		String sql = "SELECT * FROM BOARD WHERE board_category ='HOT' ORDER BY BOARD_NO DESC";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new BoardVO();
				vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardDate(rs.getString("board_date"));
				vo.setBoardAttech(rs.getString("board_attech"));
				vo.setBoardScrap(rs.getInt("board_scrap"));
				vo.setBoardRecommend(rs.getInt("board_recommend"));
				vo.setBoardHit(rs.getInt("board_hit"));

				hotboardlist.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return hotboardlist;
	}

	
	

	@Override
	public String getDate() {
		// 게시글 작성일자 받아오기
		String sql = "select now()";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "";
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
