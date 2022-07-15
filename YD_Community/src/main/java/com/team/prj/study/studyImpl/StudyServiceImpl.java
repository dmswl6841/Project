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
				vo.setStudyNo(rs.getInt("STUDY_NO"));//글 순서번호
				vo.setStudyTitle(rs.getString("STUDY_TITLE")); //글 제목
				vo.setStudySubject(rs.getString("STUDY_SUBJECT")); //글 내용
				vo.setStudyWriter(rs.getString("STUDY_WRITER"));
				vo.setStudyDate(rs.getDate("STUDY_DATE"));
				vo.setStudyStartDate(rs.getDate("STUDY_STARTDATE"));
				vo.setStudySystem(rs.getString("STUDY_SYSTEM"));
				vo.setStudyPeriod(rs.getString("STUDY_PERIOD"));
				vo.setStudyMember(rs.getString("STUDY_MEMBER"));
				vo.setStudyLanguage(rs.getString("STUDY_LANGUAGE"));
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int studyDelete(StudyVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int studyUpdate(StudyVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<StudyVO> studySearchList(String key, String val) {
		// TODO Auto-generated method stub
		return null;
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
	


