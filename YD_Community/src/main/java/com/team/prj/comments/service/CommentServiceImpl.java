package com.team.prj.comments.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team.prj.comments.vo.CommentsVO;
import com.team.prj.common.DataSource;
import com.team.prj.member.vo.MemberVO;

public class CommentServiceImpl implements CommentService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<CommentsVO> commentList(int no) {
		List<CommentsVO> list = new ArrayList<>();
		CommentsVO vo;
		String sql = "select * from comments where board_no=" + no;
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new CommentsVO();
				vo.setCommentNo(rs.getInt("comment_no"));
				vo.setBoardNo(rs.getInt(2));
				vo.setCommentWriter(rs.getString(3));
				vo.setCommentContent(rs.getString(4));
				vo.setCommentDate(rs.getString(5));
				vo.setMember_no(rs.getInt(6));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public CommentsVO commentInsert(CommentsVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentsVO commentUpdate(CommentsVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int commentDelete(CommentsVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void close() {
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}


}
