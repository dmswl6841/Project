package com.team.prj.member.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team.prj.common.DataSource;
import com.team.prj.member.vo.MemberVO;


public class MemberServiceImpl implements MemberService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	@Override
	public List<MemberVO> memberSelectList() {
		// 전체멤버 목록
		List<MemberVO> list = new ArrayList<MemberVO>();  //결과담을 객체
		MemberVO vo;
		String sql = "SELECT * FROM MEMBER";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new MemberVO();
				vo.setMemberId(rs.getString("member_id"));
	//			vo.setMemberPassword(rs.getString("member_password"));
				vo.setMemberNick(rs.getString("member_nick"));
				vo.setMemberAuthor(rs.getString("member_author"));
				list.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}

	@Override
	public MemberVO memberSelectOne(MemberVO vo) {
		//한명 조회
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			rs = psmt.executeQuery();
			if(rs.next()) {
				vo.setMemberId(rs.getString("member_id"));
				//vo.setMemberPw(rs.getString("member_pw"));
				vo.setMemberNick(rs.getString("member_nick"));
				vo.setMemberEmail(rs.getString("member_email"));
				//vo.setMemberImg(rs.getString("member_img"));
				vo.setMemberAuthor(rs.getString("member_author"));
				vo.setMemberGit(rs.getString("member_git"));
				vo.setMemberNo(rs.getInt("member_no"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return vo;
	}

	@Override
	public int memberInsert(MemberVO vo) {
		// 회원 추가
		int n = 0;
		String sql = "insert into member (member_no, member_id, member_pw, member_nick, member_email, member_author, member_git) values ((select max(member_no)+1 from member),?,?,?,?,?,?)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, vo.getMemberId());
			psmt.setString(2, vo.getMemberPw());
			psmt.setString(3, vo.getMemberNick());
			psmt.setString(4, vo.getMemberEmail());
			// psmt.setString(5, vo.getMemberImg());
			psmt.setString(5, vo.getMemberAuthor());
			psmt.setString(6, vo.getMemberGit());

			n = psmt.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	@Override
	public int memberUpdate(MemberVO vo) {
		// 회원정보 변경
		int n = 0;
		String sql = "update member set member_nick=?, member_email=?, member_git=?  where member_id=?";
		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			//psmt.setString(1, vo.getMemberImg());
			psmt.setString(1, vo.getMemberNick());
			psmt.setString(2, vo.getMemberEmail());
			psmt.setString(3, vo.getMemberGit());
			psmt.setString(4, vo.getMemberId());
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	@Override
	public int memberDelete(MemberVO vo) {
		// 회원정보 삭제
		int n = 0;
		String sql = "delete member where member_id=?";
		conn = dao.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			n = psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return n;
	}

	@Override
	public boolean isMemberId(String id) {
		String sql = "SELECT MEMBER_ID FROM MEMBER WHERE MEMBER_ID=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (!rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}
	
	@Override
	public boolean isMemberNickname(String nickname) {
		String sql = "SELECT MEMBER_NICK FROM MEMBER WHERE MEMBER_NICK=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, nickname);
			rs = psmt.executeQuery();
			if (!rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}

	@Override
	public MemberVO memberLogin(MemberVO vo) {
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_PW=?";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getMemberId());
			psmt.setString(2, vo.getMemberPw());
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo = new MemberVO();
				vo.setMemberId(rs.getString("member_id"));
				vo.setMemberPw(rs.getString("member_pw"));
				vo.setMemberNick(rs.getString("member_nick"));
				vo.setMemberEmail(rs.getString("member_email"));
				//vo.setMemberImg(rs.getString("member_img"));
				vo.setMemberAuthor(rs.getString("member_author"));
				vo.setMemberGit(rs.getString("member_git"));
				vo.setMemberNo(rs.getInt("member_no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
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
