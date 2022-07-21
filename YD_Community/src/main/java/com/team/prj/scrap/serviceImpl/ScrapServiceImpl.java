package com.team.prj.scrap.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.DataSource;
import com.team.prj.scrap.service.ScrapService;
import com.team.prj.scrap.vo.ScrapVO;

public class ScrapServiceImpl implements ScrapService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public int scrapUpdate(BoardVO vo) {
		// 추천 버튼 누르면 해당 게시글 스크랩수 1 추가 (글1개조회 참고)
		int y = 0;
		String sql = "UPDATE BOARD SET BOARD_SCRAP = BOARD_SCRAP+1 WHERE BOARD_NO = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getBoardNo());
			y = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return y;
	}

	
	
	
	
	@Override
	public int scrapInsert(ScrapVO vo) {
		// 중복 방지용 member_no&board_no 키 추가
		int n = 0;
		String sql = "INSERT INTO SCRAP VALUES(SCRAP_SEQ.NEXTVAL,?,?, SYSDATE)";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			//psmt.setInt(1, vo.getScrapNo());
			psmt.setInt(1, vo.getMemberNo());
			psmt.setInt(2, vo.getBoardNo());
			//psmt.setString(3, vo.getScrapDate());
			n = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return n;
	}


	
	
	
	
/////////////////////////////////////////////////////////////////////////////
	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
