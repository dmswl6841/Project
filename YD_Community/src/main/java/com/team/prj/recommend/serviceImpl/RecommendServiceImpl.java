package com.team.prj.recommend.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.DataSource;
import com.team.prj.recommend.service.RecommendService;
import com.team.prj.recommend.vo.RecommendVO;

public class RecommendServiceImpl implements RecommendService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	
	




	@Override
	public int recommendUpdate(BoardVO vo) {
		// 추천 버튼 누르면 해당 게시글 추천수 1 추가 (글1개조회 참고) 
		int y =0;
		String sql = "UPDATE BOARD SET BOARD_RECOMMEND = BOARD_RECOMMEND+1 WHERE BOARD_NO = ?";
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
	public int recommendInsert(RecommendVO vo) {
		// 중복 방지용 member_no&board_no 키 추가
		int n = 0;
		String sql = "INSERT INTO RECOMMEND VALUES(RECOMMEND_SEQ.NEXTVAL,?,?)";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			//psmt.setInt(1, vo.getRecommendNo());
			psmt.setInt(1, vo.getMemberNo());
			psmt.setInt(2, vo.getBoardNo());
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
