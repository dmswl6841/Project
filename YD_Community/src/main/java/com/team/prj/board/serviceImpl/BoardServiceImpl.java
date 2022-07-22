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
import com.team.prj.paging.Criteria;


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
	public BoardVO boardView(int board_no) {
		// 글 1개조회
		String sql1 = "UPDATE BOARD SET BOARD_HIT = BOARD_HIT+1 WHERE BOARD_NO = ?";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql1);
			psmt.setInt(1, board_no);
			psmt.executeUpdate();
			psmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 

		
		
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
				vo.setMemberNo(rs.getInt("member_no"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return vo;
	}

	
	
	
	
	
	@Override
	public int boardUpdate(BoardVO vo) {
		//글 수정
		int n =0;
		String sql = "UPDATE BOARD SET BOARD_TITLE = ?, BOARD_SUBJECT = ?, BOARD_ATTECH = ?, BOARD_ATTECH_DIR = ?, "
				+ "BOARD_WRITER = ?, BOARD_CATEGORY = ?, MEMBER_NO = ?, BOARD_HIT = BOARD_HIT-1"
				+ "WHERE BOARD_NO = ?";
		//BOARD_SCRAP= ?, BOARD_HIT= ?, 
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, vo.getBoardTitle());
			psmt.setString(2, vo.getBoardSubject());
			psmt.setString(3, vo.getBoardAttech());
			psmt.setString(4, vo.getBoardAttechDir());
			psmt.setString(5, vo.getBoardWriter());
			psmt.setString(6, vo.getBoardCategory());
			psmt.setInt(7, vo.getMemberNo());
			//psmt.setInt(8, vo.getBoardHit());
			psmt.setInt(8, vo.getBoardNo());
			n = psmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
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
			n = psmt.executeUpdate(); 
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return n;
	}
	
	
	
	
	
	@Override
	public List<BoardVO> boardSearchList(String key, String val) {
		// 글 검색기능                  //카테고리 선택박스 (전체, 공지, 자유, qna, 인기)  세부분류 선택박스 (글쓴이, 제목 포함단어...)             
		List<BoardVO> searchboardlist = new ArrayList<>();
		BoardVO vo;
		String sql = "SELECT * FROM BOARD WHERE "
				+ key + " LIKE '%" + val + "%'";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(); 
			
			while(rs.next()) {
				vo = new BoardVO();	//검색해서 존재하면 vo인스턴스를 초기화
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardDate(rs.getString("board_date"));
				vo.setBoardAttech(rs.getString("board_attech"));
				vo.setBoardScrap(rs.getInt("board_scrap"));
				vo.setBoardRecommend(rs.getInt("board_recommend"));
				vo.setBoardHit(rs.getInt("board_hit"));
				vo.setBoardCategory(rs.getString("board_category"));
				searchboardlist.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return searchboardlist;

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
				vo.setBoardHot(rs.getString("board_Hot"));
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
	public List<BoardVO> NboardSelectList(Criteria cri) {
		//전체조회
		List<BoardVO> noticeBoardList = new ArrayList<>();
		BoardVO vo;

		String sql = "SELECT"
				+ "    *"
				+ "FROM"
				+ "    ("
				+ "    SELECT"
				+ "        rownum rn,"
				+ "        tb1.*"
				+ "    FROM"
				+ "        (SELECT"
				+ "            b.*"
				+ "        FROM"
				+ "            board b"
				+ "        WHERE"
				+ "            b.board_category = '공지'"
				+ "          and"
				+ "             b." + cri.getSearchType() + " like ?"
				+ "        ORDER BY "
				+ "            b.BOARD_NO DESC"
				+ "        ) tb1"
				+ "    WHERE"
				+ "        rownum <= ?"
				+ "    )"
				+ "WHERE"
				+ "    rn > ?";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, "%" + cri.getKeyword() + "%");
			psmt.setInt(2, cri.getPageNum() * cri.getAmount());
			psmt.setInt(3, (cri.getPageNum() - 1) * cri.getAmount());
			System.out.println(cri);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new BoardVO();
				vo.setBoardCategory(rs.getString("board_category"));
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardDate(rs.getString("board_date"));
				vo.setBoardAttech(rs.getString("board_attech"));
				vo.setBoardScrap(rs.getInt("board_scrap"));
				vo.setBoardRecommend(rs.getInt("board_recommend"));
				vo.setBoardHit(rs.getInt("board_hit"));
				vo.setMemberNo(rs.getInt("member_no"));
				noticeBoardList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return noticeBoardList;
	}
		
		
//				List<BoardVO> noticeboardlist = new ArrayList<>();
//				BoardVO vo;
//				String sql = "SELECT * FROM BOARD WHERE board_category ='공지' ORDER BY BOARD_NO DESC";
//				
//				try {
//					conn = dao.getConnection();
//					psmt = conn.prepareStatement(sql);
//					rs = psmt.executeQuery();
//					
//					while(rs.next()) {
//						vo = new BoardVO();
//						vo.setBoardNo(rs.getInt("board_no"));
//						vo.setBoardWriter(rs.getString("board_writer"));
//						vo.setBoardTitle(rs.getString("board_title"));
//						vo.setBoardDate(rs.getString("board_date"));
//						vo.setBoardAttech(rs.getString("board_attech"));
//						vo.setBoardScrap(rs.getInt("board_scrap"));
//						vo.setBoardRecommend(rs.getInt("board_recommend"));
//						vo.setBoardHit(rs.getInt("board_hit"));
//						vo.setMemberNo(rs.getInt("member_no"));
//						vo.setBoardCategory(rs.getString("board_category"));
//						noticeboardlist.add(vo);
//					}
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}finally {
//					close();
//				}
//				return noticeboardlist;
//	}
	
	 
	
	
	
	/////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////Free 자유게시판//////////////////////////////
	@Override
	public List<BoardVO> FboardSelectList(Criteria cri) {
		//전체조회
		List<BoardVO> freeboardlist = new ArrayList<>();
		BoardVO vo;

		
		String sql = "SELECT"
				+ "    *"
				+ "FROM"
				+ "    ("
				+ "    SELECT"
				+ "        rownum rn,"
				+ "        tb1.*"
				+ "    FROM"
				+ "        (SELECT"
				+ "            b.*"
				+ "        FROM"
				+ "            board b"
				+ "        WHERE"
				+ "            b.board_category = '자유'"
				+ "          and"
				+ "             b." + cri.getSearchType() + " like ?"
				+ "        ORDER BY "
				+ "            b.board_date DESC"
				+ "        ) tb1"
				+ "    WHERE"
				+ "        rownum <= ?"
				+ "    )"
				+ "WHERE"
				+ "    rn > ?";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, "%" + cri.getKeyword() + "%");
			psmt.setInt(2, cri.getPageNum() * cri.getAmount());
			psmt.setInt(3, (cri.getPageNum() - 1) * cri.getAmount());
			System.out.println(cri);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new BoardVO();
				vo.setBoardCategory(rs.getString("board_category"));
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardDate(rs.getString("board_date"));
				vo.setBoardAttech(rs.getString("board_attech"));
				vo.setBoardScrap(rs.getInt("board_scrap"));
				vo.setBoardRecommend(rs.getInt("board_recommend"));
				vo.setBoardHit(rs.getInt("board_hit"));
				vo.setMemberNo(rs.getInt("member_no"));
				vo.setBoardCategory(rs.getString("board_category"));
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
	public List<BoardVO> QboardSelectList(Criteria cri) {
		//전체조회
		List<BoardVO> qnaBoardList = new ArrayList<>();
		BoardVO vo;

		
		String sql = "SELECT"
				+ "    *"
				+ "FROM"
				+ "    ("
				+ "    SELECT"
				+ "        rownum rn,"
				+ "        tb1.*"
				+ "    FROM"
				+ "        (SELECT"
				+ "            b.*"
				+ "        FROM"
				+ "            board b"
				+ "        WHERE"
				+ "            b.board_category = 'QnA'"
				+ "          and"
				+ "             b." + cri.getSearchType() + " like ?"
				+ "        ORDER BY "
				+ "            b.BOARD_NO DESC"
				+ "        ) tb1"
				+ "    WHERE"
				+ "        rownum <= ?"
				+ "    )"
				+ "WHERE"
				+ "    rn > ?";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, "%" + cri.getKeyword() + "%");
			psmt.setInt(2, cri.getPageNum() * cri.getAmount());
			psmt.setInt(3, (cri.getPageNum() - 1) * cri.getAmount());
			System.out.println(cri);
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
				vo.setMemberNo(rs.getInt("member_no"));
				vo.setBoardCategory(rs.getString("board_category"));
				qnaBoardList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return qnaBoardList;
	}
		
		
//		List<BoardVO> qnaboardlist = new ArrayList<>();
//		BoardVO vo;
//		String sql = "SELECT * FROM BOARD WHERE board_category ='QnA' ORDER BY BOARD_NO DESC";
//		
//		try {
//			conn = dao.getConnection();
//			psmt = conn.prepareStatement(sql);
//			rs = psmt.executeQuery();
//			
//			while(rs.next()) {
//				vo = new BoardVO();
//				vo.setBoardNo(rs.getInt("board_no"));
//				vo.setBoardWriter(rs.getString("board_writer"));
//				vo.setBoardTitle(rs.getString("board_title"));
//				vo.setBoardDate(rs.getString("board_date"));
//				vo.setBoardAttech(rs.getString("board_attech"));
//				vo.setBoardScrap(rs.getInt("board_scrap"));
//				vo.setBoardRecommend(rs.getInt("board_recommend"));
//				vo.setBoardHit(rs.getInt("board_hit"));
//				vo.setMemberNo(rs.getInt("member_no"));
//				vo.setBoardCategory(rs.getString("board_category"));
//				qnaboardlist.add(vo);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close();
//		}
//		return qnaboardlist;
//	}
	
	
	
	
	
	
	/////////////////////////////////////////////////////////////////////////////
	/////////Hot 인기글 게시판 crud (인기글 게시판으로 올라가면 수정 불가해짐)///////////
	@Override
	public List<BoardVO> HboardSelectList() {
		//전체조회
		List<BoardVO> hotboardlist = new ArrayList<>();
		BoardVO vo;

		String sql = "SELECT * FROM BOARD WHERE BOARD_HOT ='HOT' ORDER BY BOARD_NO DESC";
		
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
				vo.setMemberNo(rs.getInt("member_no"));
				vo.setBoardCategory(rs.getString("board_category"));
				
				vo.setBoardHot(rs.getString("board_hot"));
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
	public int HboardUpdate(BoardVO vo) {
		// 추천수 일정 이상 글 hot태그 추가로 달아주기
		int n = 0;
		String sql = "UPDATE BOARD SET BOARD_HOT = 'HOT' WHERE BOARD_RECOMMEND >= 20";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			n = psmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		} 	
		return n;
	}


	
	

	@Override
	public int fTotalBoardCount(Criteria cri) {
		//전체조회
		int totalCount = 0;
		String sql = "select count(*) total_count from board where board_category = '자유'";
				
				
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);

			//키워드가 있을 경우
			if(!cri.getKeyword().equals("")) {
				System.out.println("enter impl keyword");
				sql = "select count(*) total_count from board where board_category = '자유' and "
					+ cri.getSearchType() + " like '%" + cri.getKeyword() + "%'";
				
				psmt = conn.prepareStatement(sql);
			}

			rs = psmt.executeQuery();
			System.out.println();
			while(rs.next()) {
				totalCount = rs.getInt("total_count");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return totalCount;
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
