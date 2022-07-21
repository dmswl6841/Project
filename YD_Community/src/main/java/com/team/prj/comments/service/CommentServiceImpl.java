package com.team.prj.comments.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team.prj.comments.vo.CommentsVO;
import com.team.prj.common.DataSource;

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
				vo.setMemberNo(rs.getInt(6));
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
		String sql = "insert into comments (comment_no, board_no, comment_writer, comment_content, member_no) values (comments_seq.nextval, ?, ?, ?,?)";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getBoardNo());
			psmt.setString(2, vo.getCommentWriter());
			psmt.setString(3, vo.getCommentContent());
			psmt.setInt(4, vo.getMemberNo());
			psmt.executeUpdate();

			sql = "select * from comments where board_no=" + vo.getBoardNo();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new CommentsVO();
				vo.setCommentNo(rs.getInt("comment_no"));
				vo.setBoardNo(rs.getInt(2));
				vo.setCommentWriter(rs.getString(3));
				vo.setCommentContent(rs.getString(4));
				vo.setCommentDate(rs.getString(5));
				vo.setMemberNo(rs.getInt(6));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}

	@Override
	public int commentUpdate(int no) {

		return 0;
	}

	@Override
	public int commentDelete(int no) {
		int cnt = 0;

		String sql = "delete comments where comment_no=?";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, no);
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

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
