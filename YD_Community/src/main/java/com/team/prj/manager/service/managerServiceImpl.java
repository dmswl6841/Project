package com.team.prj.manager.service;

import java.lang.reflect.Member;
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

public class managerServiceImpl implements ManagerService {

	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	// 회원 전체 조회
	@Override
	public List<MemberVO> memberSelectList() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO vo;
		String sql = "select * from member";

		try {

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

		return list;
	}

	// 회원 목록 검색
	@Override
	public List<MemberVO> memberSearchList(String key, String val) {
		// TODO Auto-generated method stub
		return null;
	}

	// 게시글 전체 조회
	@Override
	public List<BoardVO> boardSelectList() {
		// TODO Auto-generated method stub
		return null;
	}

	// 게시글 목록 검색
	@Override
	public List<BoardVO> boardSearchList(String key, String val) {
		// TODO Auto-generated method stub
		return null;
	}

	// 댓글 전체 조회
	@Override
	public List<CommentsVO> commentsSelectList() {
		// TODO Auto-generated method stub
		return null;
	}

	// 댓글 목록 검색
	@Override
	public List<CommentsVO> commentsSearchList(String key, String val) {
		// TODO Auto-generated method stub
		return null;
	}

	// 금지어 전체 조회
	@Override
	public List<XwordVO> XwordSelectList() {
		// TODO Auto-generated method stub
		return null;
	}

	// 금지어 목록 검색
	@Override
	public List<XwordVO> XwordSearchList() {
		// TODO Auto-generated method stub
		return null;
	}

	// 금지어 입력
	@Override
	public int XwordInsert(XwordVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 금지어 삭제
	@Override
	public int XwordDelete(XwordVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	// 금지어 수정
	@Override
	public int XwordUpdate(XwordVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	private void close() {
		try {
			if (rs != null) rs.close();
			if (psmt !=null) psmt.close();
			if (conn !=null) conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
