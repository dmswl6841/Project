package com.team.prj.study.studyImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team.prj.common.DataSource;
import com.team.prj.study.service.StudyService;
import com.team.prj.study.vo.StudyVO;



public class StudyServiceImpl implements StudyService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public List<StudyVO> studySelectList() {
		// 전체목록
		List<StudyVO> list = new ArrayList<>();
		StudyVO vo;
		String sql = "SELECT * FROM STUDY ORDER BY STUDY_NO DESC";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				vo = new StudyVO();
				vo.setStudyNo(rs.getInt("STUDY_NO"));// 글 순서번호
				vo.setStudyTitle(rs.getString("STUDY_TITLE")); // 글 제목
				vo.setStudySubject(rs.getString("STUDY_SUBJECT")); // 글 내용
				vo.setStudyWriter(rs.getString("STUDY_WRITER"));// 글작성자
				vo.setStudyStartDate(rs.getDate("STUDY_STARTDATE"));// 스터디 시작기간
				vo.setStudyMember(rs.getString("STUDY_MEMBER"));// 스터디 인원수
				vo.setStudyLanguage(rs.getString("STUDY_LANGUAGE"));// 스터디 언어
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
	public StudyVO studySelect(StudyVO vo) {
		// 글 상세보기

		String sql = "select * from study where study_writer";

		return null;
	}

	@Override
	public int studyInsert(StudyVO vo) {
		// 글 등록
		int n = 0;
		String sql = "INSERT INTO STUDY VALUES(STUDY_SEQ.NEXTVAL,?,?,?,0,0,?,?,?,?)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, vo.getStudyWriter());
			psmt.setString(2, vo.getStudyTitle());
			psmt.setString(3, vo.getStudySubject());
			psmt.setDate(4, vo.getStudyDate());
			psmt.setDate(5, vo.getStudyStartDate());
			psmt.setString(6, vo.getStudyAttech());
			psmt.setString(7, vo.getStudyAttechDir());
			psmt.setString(8, vo.getStudyLanguage());
			psmt.setString(9, vo.getStudyMember());

			n = psmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int studyDelete(StudyVO vo) {
		// 글 삭제 
		int n =0;
	    return 0;
	}

	@Override
	public int studyUpdate(StudyVO vo) {
		// 글 수정
		int n =0;
		return 0;
	}

	@Override
	public List<StudyVO> studySearchList(String key, String val) {
		// 목록검색
		List<StudyVO> list= new ArrayList<>();
		StudyVO vo;
		String sql = "select * from study where ? like %?%";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, key);
			psmt.setString(2, val);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new StudyVO();
				vo.setStudyNo(rs.getInt("STUDY_NO"));// 글 순서번호
				vo.setStudyTitle(rs.getString("STUDY_TITLE")); // 글 제목
				vo.setStudySubject(rs.getString("STUDY_SUBJECT")); // 글 내용
				vo.setStudyWriter(rs.getString("STUDY_WRITER"));// 글작성자
				vo.setStudyStartDate(rs.getDate("STUDY_STARTDATE"));// 스터디 시작기간
				vo.setStudyMember(rs.getString("STUDY_MEMBER"));// 스터디 인원수
				vo.setStudyLanguage(rs.getString("STUDY_LANGUAGE"));// 스터디 언어
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
