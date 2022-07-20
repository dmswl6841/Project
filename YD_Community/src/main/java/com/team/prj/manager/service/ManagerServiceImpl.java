package com.team.prj.manager.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.comments.vo.CommentsVO;
import com.team.prj.common.DataSource;
import com.team.prj.member.vo.MemberVO;
import com.team.prj.xword.vo.XwordVO;

public class ManagerServiceImpl implements ManagerService {

	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	// 회원 전체 조회
	@Override
	public List<MemberVO> managerMemberSelectAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO vo;
		String sql = "select * from member";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				vo = new MemberVO();
				vo.setMemberNo(rs.getInt("member_no"));
				vo.setMemberId(rs.getString("member_id"));
				vo.setMemberNick(rs.getString("member_nick"));
				vo.setMemberWarning(rs.getInt("member_warning"));
				vo.setMemberAuthor(rs.getString("member_author"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// 회원 목록 검색
	@Override
	public List<MemberVO> managerMemberSearch(String key, String val) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO vo = new MemberVO();
		String sql = "select * from member where " + key + " like '%" + val + "%'";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				vo = new MemberVO();
				vo.setMemberNo(rs.getInt("member_no"));
				vo.setMemberId(rs.getString("member_id"));
				vo.setMemberNick(rs.getString("member_nick"));
				vo.setMemberWarning(rs.getInt("member_warning"));
				vo.setMemberAuthor(rs.getString("member_author"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public int managerMemberDelete(MemberVO vo) {
		int r = 0;
		String sql = "delete from member where member_no=?";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getMemberNo());
			r = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
	}

	@Override
	public int memberUpdateAuthor(String key, String val) {
		int r = 0;
		String sql = "update member set member_author = ? where member_no =?";
		
		if (key.equals("ADMIN")) {
			key = "ADMIN";
		} else if (key.equals("GUEST")){
			key = "USER";
		}
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, key);
			psmt.setString(2, val);
			
			r = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
	}

	// 게시글 전체 조회
	@Override
	public List<BoardVO> managerBoardSelectAll() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo;
		String sql = "select * from board order by board_no desc";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardCategory(rs.getString("board_category"));
				vo.setBoardTitle(rs.getString("borad_title"));
				vo.setBoardDate(rs.getString("board_date"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// 게시글 목록 검색
	@Override
	public List<BoardVO> managerBoardSearch(String key, String val) {
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo;
		String sql = "select * from board  where " + key + " like '%" + val + "%'";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, key);
			psmt.setString(2, val);
			rs = psmt.executeQuery();

			while (rs.next()) {
				vo = new BoardVO();
				vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardCategory(rs.getString("board_category"));
				vo.setBoardTitle(rs.getString("borad_title"));
				vo.setBoardDate(rs.getString("board_date"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	//게시글 삭제
	@Override
	public int managerBoardDelete(BoardVO vo) {
		int r = 0;
		String sql = "delete from board where board_no=?";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getBoardNo());
			r = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
	}
	
	// 댓글 전체 조회
	@Override
	public List<CommentsVO> managerCommentsSelectAll() {
		List<CommentsVO> list = new ArrayList<CommentsVO>();
		CommentsVO vo;
		String sql = "select * from Comments";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				vo = new CommentsVO();
				vo.setCommentNo(rs.getInt("comment_no"));
				vo.setCommentWriter(rs.getString("comment_writer"));
				vo.setCommentContent(rs.getString("commnet_content"));
				vo.setCommentDate(rs.getString("comment_date"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// 댓글 목록 검색
	@Override
	public List<CommentsVO> managerCommentsSearch(String key, String val) {
		List<CommentsVO> list = new ArrayList<CommentsVO>();
		CommentsVO vo;
		String sql = "select * from Comments where " + key + " like '%" + val + "%'";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, key);
			psmt.setString(2, val);
			rs = psmt.executeQuery();

			while (rs.next()) {
				vo = new CommentsVO();
				vo.setCommentNo(rs.getInt("comment_no"));
				vo.setCommentWriter(rs.getString("comment_writer"));
				vo.setCommentContent(rs.getString("commnet_content"));
				vo.setCommentDate(rs.getString("comment_date"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	
	//댓글 삭제
	@Override
	public int managerCommentsDelete(CommentsVO vo) {
		int r = 0;
		String sql = "delete from comments where comment_no=?";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getCommentNo());
			r = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
	}

	// 금지어 전체 조회
	@Override
	public List<XwordVO> XwordSelectAll() {
		List<XwordVO> list = new ArrayList<XwordVO>();
		XwordVO vo;
		String sql = "select * from Xword";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				vo = new XwordVO();
				vo.setXwordNo(rs.getInt("xword_no"));
				vo.setXword(rs.getString("xword"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// 금지어 목록 검색
	@Override
	public List<XwordVO> XwordSearch(String key, String val) {
		List<XwordVO> list = new ArrayList<XwordVO>();
		XwordVO vo;
		String sql = "select * from Xword where " + key + " like '%" + val + "%'";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, key);
			psmt.setString(2, val);
			rs = psmt.executeQuery();

			while (rs.next()) {
				vo = new XwordVO();
				vo.setXwordNo(rs.getInt("xword_no"));
				vo.setXword(rs.getString("xword"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// 금지어 입력
	@Override
	public int XwordInsert(XwordVO vo) {
		int r = 0;
		String sql = "insert into xword (xword) values (?)";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getXword());

			r = psmt.executeUpdate();

			if (rs.next()) {
				vo = new XwordVO();
				vo.setXword(rs.getString("xword"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
	}

	// 금지어 삭제
	@Override
	public int XwordDelete(XwordVO vo) {
		int r = 0;
		String sql = "delete from xword where xword_no=?";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getXwordNo());

			r = psmt.executeUpdate();

			if (rs.next()) {
				vo = new XwordVO();
				vo.setXword(rs.getString("xword"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
	}

	// 금지어 수정
	@Override
	public int XwordUpdate(XwordVO vo) {
		int r = 0;
		String sql = "update xword set (xword) values (?)";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getXword());

			r = psmt.executeUpdate();

			if (rs.next()) {
				vo = new XwordVO();
				vo.setXword(rs.getString("xword"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return r;
	}

	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
