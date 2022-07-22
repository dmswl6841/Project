package com.team.prj.page.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.comments.vo.CommentsVO;
import com.team.prj.common.DataSource;
import com.team.prj.member.vo.MemberVO;
import com.team.prj.page.service.PageService;
import com.team.prj.scrap.vo.ScrapVO;
import com.team.prj.study.vo.StudyVO;


public class PageServiceImpl implements PageService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	//이거씀
	//최근 활동 (7일 내의 작성글)
//	@Override
//	public List<BoardVO> myLogList(int memberNo) {
//		//최근 활동 
//		List<BoardVO> list = new ArrayList<BoardVO>();
//		BoardVO vo;
//		//BoardVO boar3dVo = new BoardVO(); 
//		//최근 일주일만 나오게 하려면
//		String sql = "SELECT BOARD_DATE, BOARD_TITLE FROM BOARD WHERE BOARD_DATE >= TO_CHAR(SYSDATE-7,'YYMMDD') AND MEMBER_NO = ?";
//		try {
//			conn = dao.getConnection();
//			psmt  = conn.prepareStatement(sql);
//			psmt.setInt(1, memberNo);
//			rs = psmt.executeQuery();
//			
//			while(rs.next()) {
//				vo = new BoardVO();
//				vo.setBoardDate(rs.getString("board_Date"));
//				vo.setBoardTitle(rs.getString("board_title"));
//				list.add(vo);
//			}
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close();
//		}
//		return list;
//	}
//	
	
	//이거씀
	//최근 활동 (7일 내의 스터디글)
//	@Override
//	public List<StudyVO> myStudyLogList(int memberNo) {
//		List<StudyVO> list = new ArrayList<StudyVO>();
//		StudyVO vo;
//		//최근 일주일
//		String sql = "SELECT STUDY_DATE, STUDY_TITLE FROM STUDY WHERE STUDY_DATE >= TO_CHAR(SYSDATE-7,'YYMMDD') AND MEMBER_NO = ?";
//		
//		try {
//			conn = dao.getConnection();
//			psmt  = conn.prepareStatement(sql);
//			psmt.setInt(1, memberNo);
//			rs = psmt.executeQuery();
//			
//			while(rs.next()) {
//				vo = new StudyVO();
//				vo.setStudyDate(rs.getString("study_date"));
//				vo.setStudyTitle(rs.getString("study_title"));
//				list.add(vo);
//			}
//		} catch(SQLException e) {
//			e.printStackTrace();
//		} finally {
//			close();
//		}
//		
//		return list;
//	}
	
	
		
		
		
//	//로그에 저장해서 하려면 (원래 하려던 거)
//			String sql = "select a.log_date, a.category, b.board_title from log a left outer join board b on a.log_no= b.board_no where a.member_no = ?";
//				
//		try {
//			
//			
//			conn = dao.getConnection();
//			psmt = conn.prepareStatement(sql);
//			psmt.setInt(1, memberNo);
//			rs = psmt.executeQuery();
//			
//			while(rs.next()) {
//				BoardVO board = new BoardVO();
//				vo = new LogVO();
//				vo.setLogDate(rs.getString("log_date")); 
//				vo.setCategory(rs.getString("category")); 
//				board.setBoardTitle(rs.getString("board_title"));
//				vo.setBvo(board);
//				
//			
//				list.add(vo);
//				
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close();
//		}
//		
//		return list;
//	}
	
	
	
	//내가 쓴 게시글 목록
		@Override
		public List<BoardVO> myBoardList(int memberNo) {
			List<BoardVO> list = new ArrayList<BoardVO>();
			BoardVO vo;
			
			//String sql = "SELECT BOARD_NO, BOARD_WRITER,BOARD_TITLE,BOARD_DATE FROM BOARD WHERE MEMBER_NO = ? ORDER BY BOARD_NO DESC ";
			String sql = "SELECT BOARD_NO, BOARD_CATEGORY,BOARD_TITLE,BOARD_WRITER,BOARD_DATE FROM BOARD WHERE MEMBER_NO = ? ORDER BY BOARD_NO DESC ";
			try {
				conn = dao.getConnection();
				psmt  = conn.prepareStatement(sql);
				psmt.setInt(1, memberNo);
				rs = psmt.executeQuery();
				
				while(rs.next()) {
					vo = new BoardVO();
					vo.setBoardNo(rs.getInt("board_no"));
					vo.setBoardCategory(rs.getString("board_category"));
					vo.setBoardTitle(rs.getString("board_title"));
					vo.setBoardWriter(rs.getString("board_writer"));
					vo.setBoardDate(rs.getString("board_date"));
					list.add(vo);
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			
			return list;
		}


	//내가 작성한 스터디 게시글 
	@Override
	public List<StudyVO> myStudyBoardList(int memberNo) {
		List<StudyVO> list = new ArrayList<StudyVO>();
		StudyVO vo;
		//String sql = "SELECT STUDY_NO, STUDY_WRITER,STUDY_TITLE,STUDY_DATE FROM STUDY WHERE MEMBER_NO = ? ORDER BY STUDY_NO DESC";	
		String sql = "SELECT STUDY_NO, STUDY_LANGUAGE,STUDY_TITLE,STUDY_WRITER,STUDY_DATE FROM STUDY WHERE MEMBER_NO = ? ORDER BY STUDY_NO DESC";
		try {
			conn = dao.getConnection();
			psmt  = conn.prepareStatement(sql);
			psmt.setInt(1, memberNo);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new StudyVO();
				vo.setStudyNo(rs.getInt("study_no"));
				vo.setStudyLanguage(rs.getString("study_language"));
				vo.setStudyTitle(rs.getString("study_title"));
				vo.setStudyWriter(rs.getString("study_writer"));
				vo.setStudyDate(rs.getString("study_date"));
				list.add(vo);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return list;
	}
	
	
	


	@Override
	public List<ScrapVO> myScrapList(int memberNo) {
		// 내 스크랩 목록
		List<ScrapVO> list = new ArrayList<ScrapVO>();
		ScrapVO vo;
		//스크랩이랑 보드 조인
		//String sql = "SELECT S.SCRAP_DATE,B.BOARD_NO , B.BOARD_TITLE FROM SCRAP S left OUTER JOIN BOARD B ON S.board_no = B.board_no WHERE S.MEMBER_NO = ? ORDER BY S.SCRAP_DATE DESC";
		String sql ="SELECT s.scrap_no, b.board_category, B.BOARD_TITLE, s. scrap_date FROM SCRAP S left OUTER JOIN BOARD B ON S.board_no = B.board_no WHERE S.MEMBER_NO = ? ORDER BY S.SCRAP_DATE DESC";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,memberNo); //edit!!
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				BoardVO board = new BoardVO();
				vo = new ScrapVO();
				vo.setScrapNo(rs.getInt("scrap_no"));
				board.setBoardCategory(rs.getString("board_category"));
				board.setBoardTitle(rs.getString("board_title"));
			vo.setScrapDate(rs.getString("scrap_date"));
		//	board.setBoardNo(rs.getInt("board_no"));
		vo.setBvo(board);
		list.add(vo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
		
	}

	@Override
	public List<CommentsVO> myCommentsList(int memberNo) {
		// 내가 쓴 댓글 목록//
		List<CommentsVO> list = new ArrayList<CommentsVO>();
		CommentsVO vo;
		
		String sql = "select c.board_no,c.comment_content,c.comment_date from comments c left outer join member m on c.member_no = m.member_no where c.member_no = ? order by comment_date desc";
		
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, memberNo);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				MemberVO mbvo = new MemberVO();	
			vo = new CommentsVO();
			vo.setBoardNo(rs.getInt("board_no"));
			vo.setCommentContent(rs.getString("comment_content"));
			vo.setCommentDate(rs.getString("comment_date"));
			vo.setMbvo(mbvo);
			list.add(vo);
			
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return list;
	}

	

	
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