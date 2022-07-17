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
		String sql = "SELECT STUDY_NO, STUDY.STUDY_TITLE, "
				+ "STUDY_SYSTEM, STUDY_PERIOD, STUDY_LANGUAGE, STUDY_WRITER, STUDY_DATE "
				+ "FROM STUDY ORDER BY STUDY_NO DESC";
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
				vo.setStudyLanguage(rs.getString("STUDY_LANGUAGE"));//스터디언어;
				vo.setStudyWriter(rs.getString("STUDY_WRITER"));//스터디작성자;
				vo.setStudyDate(rs.getDate("STUDY_DATE"));//작성일자
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

		

		return null;
	}

	@Override
	public int studyInsert(StudyVO vo) {
		// 글 등록
		int n = 0;
		String sql = "INSERT INTO STUDY VALUES(STUDY_SEQ.NEXTVAL,?,?,?,?,?,?,sysdate)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			
			
            //psmt.setInt(1, vo.getStudyNo());
			//vo.setStudyNo(rs.getInt("STUDY_NO"));// 1글 순서번호
			vo.setStudyWriter(rs.getString("STUDY_WRITER"));//2글작성자
			vo.setStudyTitle(rs.getString("STUDY_TITLE")); // 3글 제목
			vo.setStudySubject(rs.getString("STUDY_SUBJECT")); //4글 내용
			vo.setStudyDate(rs.getDate("STUDY_DATE"));//5작성일자
			vo.setStudyLanguage(rs.getString("STUDY_LANGUAGE"));//6스터디언어;
			vo.setStudyPeriod(rs.getString("STUDY_PERIOD"));// 7스터디기간
			vo.setStudySystem(rs.getString("STUDY_SYSTEM"));// 8스터디방식
			vo.setStudyMember(rs.getString("STUDY_MEMBER"));//9스터디멤버
			//vo.setMemberNo(rs.getInt("MEMBER_NO"));//포링키
			vo.setStudyAttech(rs.getString("STUDY_ATTECH"));//11첨부파일
			vo.setStudyAttechDir(rs.getString("STUDY_ATTECHDIR"));//12첨부파일위치

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
				int n = 0;
				String sql = "DELETE FROM STUDY WHERE STUDY_NO = ?";
				try {
					conn = dao.getConnection();
					psmt = conn.prepareStatement(sql);
					
					
		            //psmt.setInt(1, vo.getStudyNo());
					vo.setStudyNo(rs.getInt("STUDY_NO"));// 1글 순서번호
					vo.setStudyTitle(rs.getString("STUDY_TITLE")); // 2글 제목
					vo.setStudySubject(rs.getString("STUDY_SUBJECT")); // 3글 내용
					vo.setStudySystem(rs.getString("STUDY_SYSTEM"));// 4글작성자
					vo.setStudyPeriod(rs.getString("STUDY_PERIOD"));// 5스터디기간
					vo.setStudyLanguage(rs.getString("STUDY_LANGUAGE"));//6스터디언어;
					vo.setStudyDate(rs.getDate("STUDY_DATE"));//7작성일자
					vo.setMemberNo(rs.getInt("MEMBER_NO"));//포링키
					vo.setStudyAttech(rs.getString("STUDY_ATTECH"));//첨부파일
					vo.setStudyAttechDir(rs.getString("STUDY_ATTECHDIR"));//첨부파일

					n = psmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					close();
				}
				return n;
			}

	@Override
	public int studyUpdate(StudyVO vo) {
		// 글 수정
		int n = 0;
		String sql = "UPDATE STUDY SET STUDY_TITLE=?, STUDY_SUBJECT=?, "
				+ "STUDY_SYSTEM=?, STUDY_PERIOD=? , "
				+ "STUDY_LANGUAGE=? , STUDY_ATTECH=? ,STUDY_ATTECHDIR, WHERE NOTICE_ID=?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			//vo.setStudyNo(rs.getInt("STUDY_NO"));// 1글 순서번호
			vo.setStudyTitle(rs.getString("STUDY_TITLE")); // 2글 제목
			vo.setStudySubject(rs.getString("STUDY_SUBJECT")); // 3글 내용
			vo.setStudySystem(rs.getString("STUDY_SYSTEM"));// 4글작성자
			vo.setStudyPeriod(rs.getString("STUDY_PERIOD"));// 5스터디기간
			vo.setStudyLanguage(rs.getString("STUDY_LANGUAGE"));//6스터디언어;
			//vo.setStudyDate(rs.getDate("STUDY_DATE"));//7작성일자
			//vo.setMemberNo(rs.getInt("MEMBER_NO"));//포링키
			vo.setStudyAttech(rs.getString("STUDY_ATTECH"));//첨부파일
			vo.setStudyAttechDir(rs.getString("STUDY_ATTECHDIR"));//첨부파일
			//내용관련메소드 다호출
			n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public List<StudyVO> studySearchList(String key, String val) {
		// 목록검색
		//작성자,제목만 검색가능하게
		List<StudyVO> list= new ArrayList<>();
		StudyVO vo;
		String sql = "select * from study  where study_writer= ?"; 
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, key);
			psmt.setString(2, val);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new StudyVO();
				vo.setStudyWriter(rs.getString("STUDY_WRITER"));// 글작성자
				
				list.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
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
