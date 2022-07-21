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
		// 전체목록(studyList.jsp파일생성)
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
				vo.setStudySystem(rs.getString("STUDY_SYSTEM"));// 스터디 방식
				vo.setStudyPeriod(rs.getString("STUDY_PERIOD"));// 스터디기간
				vo.setStudyLanguage(rs.getString("STUDY_LANGUAGE"));// 스터디언어;
				vo.setStudyWriter(rs.getString("STUDY_WRITER"));// 스터디작성자;
				vo.setStudyDate(rs.getString("STUDY_DATE"));// 작성일자
				vo.setMemberNo(rs.getInt("member_no"));
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
	public StudyVO studyViewList(int study_no) {
		// 단건조회
		StudyVO vo = new StudyVO();
		String sql = "SELECT * FROM STUDY WHERE STUDY_NO = ?";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, study_no);
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setStudyNo(rs.getInt("study_no"));
				vo.setStudyTitle(rs.getString("study_Title"));
				vo.setStudySubject(rs.getString("study_Subject"));
				vo.setStudyWriter(rs.getString("study_writer"));
				vo.setStudyLanguage(rs.getString("study_Language"));
				vo.setStudySystem(rs.getString("study_System"));
				vo.setStudyPeriod(rs.getString("study_Period"));
				vo.setStudyMember(rs.getString("study_Member"));
				vo.setMemberNo(rs.getInt("member_no"));
				vo.setStudyAttech(rs.getString("study_Attech"));
				vo.setStudyAttechDir(rs.getString("study_AttechDir"));
				vo.setStudyDate(rs.getString("study_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vo;
	}

	@Override
	public int studyInsert(StudyVO vo) {
		// 글 등록
		int n = 0;
		
		String sql = "INSERT INTO STUDY VALUES (STUDY_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,sysdate)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getStudyTitle());
			psmt.setString(2, vo.getStudySubject());
			psmt.setString(3, vo.getStudyWriter());
			psmt.setString(4, vo.getStudyLanguage());
			psmt.setString(5, vo.getStudySystem());
			psmt.setString(6, vo.getStudyPeriod());
			psmt.setString(7, vo.getStudyMember());
			psmt.setInt(8, vo.getMemberNo());
			psmt.setString(9, vo.getStudyAttech());
			psmt.setString(10, vo.getStudyAttechDir());
			// psmt.setString(11,vo.getStudyDate());

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
		int r = 0;	
		String sql = "DELETE FROM STUDY WHERE STUDY_NO = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,vo.getStudyNo());
			r = psmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return r;
	}

	@Override
	public int studyUpdate(StudyVO vo) {
		// 글 수정
		int n = 0;
		String sql = "update study set study_title=?, study_subject=?, study_system=?, study_period=?, study_language=?, study_attech=?, study_attechdir=? where study_no=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, vo.getStudyTitle());
			psmt.setString(2, vo.getStudySubject());
			psmt.setString(3, vo.getStudySystem());
			psmt.setString(4, vo.getStudyPeriod());
			psmt.setString(5, vo.getStudyLanguage());
			psmt.setString(6, vo.getStudyAttech());
			psmt.setString(7, vo.getStudyMember());
			psmt.setString(7, vo.getStudyAttechDir());
			psmt.setInt(8, vo.getStudyNo());

			// 내용관련메소드 다호출
			n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}
///////////////////////////////////////////////////////////////
	@Override
	public List<StudyVO> studySearchList(String key, String val) {
		// 목록검색
				// 작성자,제목만 검색가능하게
				List<StudyVO> searchstudylist = new ArrayList<>();
				StudyVO vo;
				String sql = "SELECT * FROM STUDY WHERE "
						+ key + " LIKE '%" + val + "%'";
				try {
					conn = dao.getConnection();//
					psmt = conn.prepareStatement(sql);//
					
					rs = psmt.executeQuery();//
					
					while (rs.next()) {
						vo = new StudyVO(); //검색해서 존재하면 vo인스턴스를 초기화
						vo.setStudyNo(rs.getInt("STUDY_NO"));// 1글 순서번호
						vo.setStudyTitle(rs.getString("STUDY_TITLE")); // 2글 제목
						vo.setStudySystem(rs.getString("STUDY_SYSTEM"));// 4글작성자
						vo.setStudyWriter(rs.getString("STUDY_WRITER"));//작성자
						vo.setStudyPeriod(rs.getString("STUDY_PERIOD"));// 5스터디기간
						vo.setStudyLanguage(rs.getString("STUDY_LANGUAGE"));// 6스터디언어;
						vo.setStudyDate(rs.getString("STUDY_DATE"));//7작성일자
						vo.setStudyAttech(rs.getString("STUDY_ATTECH"));// 첨부파일
						
						searchstudylist.add(vo);
					
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close();
				}
				return searchstudylist;
	}

/////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
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
