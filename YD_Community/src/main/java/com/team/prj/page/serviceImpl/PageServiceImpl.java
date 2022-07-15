package com.team.prj.page.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.DataSource;
import com.team.prj.log.vo.LogVO;
import com.team.prj.page.service.PageService;
import com.team.prj.scrap.vo.ScrapVO;
import com.team.prj.study.vo.StudyVO;


public class PageServiceImpl implements PageService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	
	@Override
	public List<LogVO> myLogList() {
		//최근 활동
		List<LogVO> list = new ArrayList<LogVO>();
		LogVO vo;
		String sql = "select a.board_no, a.member_no, a.category, a.logdate, b.boar_title,c.writer from log a\r\n"
				+ "left outer join board b on a.board_no = b.board_no"
				+ "left outer join comments c on a.comment_no = c.comment_no"
				+ "where a.member_no = ?";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new LogVO();
			//	vo.set
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return list;
	}
	
	
	
	@Override
	public List<BoardVO> myBoardList() {
		//내가 쓴 게시글 목록
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo;
		//세션 아이디로 member_no를 보내고 세션아이디랑 내 아이디가 같으면 출력하는 식으로
		String sql = "select * from board where = ";

		//String sql = "SELECT * FROM BOARD WHERE MEMBER_NO = ? ";  아니면 이렇게
		
		
		 // order by regdate desc
		
		try {
			conn = dao.getConnection();
			psmt  = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new BoardVO();
				//vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardDate(rs.getString("board_date"));
				vo.setBoardHit(rs.getInt("board_hit"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
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


	@Override
	public List<ScrapVO> myScrapList() {
		List<ScrapVO> list = new ArrayList<>();
		ScrapVO vo;
		String sql = "SELECT B.BOARD_NO , B.BOARD_TITLE ,S.SCRAPDATE FROM SCRAP S RIGHT OUTER JOIN BOARD B ON S.BOARD_NO = B.BOARD_NO WHERE S.MEMBER_NO = ? ";
		return list;
	}

	@Override
	public List<StudyVO> myStudyList() {
		//찜한 스터디 리스트
		return null;
	}

}