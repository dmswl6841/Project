package com.team.prj.page.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.team.prj.board.vo.BoardVO;
import com.team.prj.common.DataSource;
import com.team.prj.log.vo.LogVO;
import com.team.prj.member.vo.MemberVO;
import com.team.prj.page.service.PageService;
import com.team.prj.scrap.vo.ScrapVO;
import com.team.prj.study.vo.StudyVO;


public class PageServiceImpl implements PageService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	
	@Override
	public List<BoardVO> myLogList(int memberNo) {
		//최근 활동
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo;
		//BoardVO boar3dVo = new BoardVO(); 
		//최근 일주일만 나오게 하려면
		String sql = "SELECT BOARD_DATE, BOARD_TITLE FROM BOARD WHERE BOARD_DATE >= TO_CHAR(SYSDATE-7,'YYMMDD') AND MEMBER_NO = ?";
		
		try {
			conn = dao.getConnection();
			psmt  = conn.prepareStatement(sql);
			psmt.setInt(1, memberNo);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new BoardVO();
				vo.setBoardDate(rs.getString("board_title"));
				vo.setBoardTitle(rs.getString("board_title"));
				list.add(vo);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
		
		
		
//	//로그에 저장해서 하려면 (원래 하려던 거)
//			String sql = "select a.log_date, a.category, b.board_title from log a left outer join board b on a.log_no= b.board_no where a.member_no = ?";
//				
//		try {
//			
//			
//			conn = dao.getConnection();
//			psmt = conn.prepareStatement(sql);
//			psmt.setInt(1, memberNo);
//			rs = psmt.executeQuery();
//			
//			while(rs.next()) {
//				BoardVO board = new BoardVO();
//				vo = new LogVO();
//				vo.setLogDate(rs.getString("log_date")); 
//				vo.setCategory(rs.getString("category")); 
//				board.setBoardTitle(rs.getString("board_title"));
//				vo.setBvo(board);
//				
//			
//				list.add(vo);
//				
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close();
//		}
//		
//		return list;
//	}
	
	
	
	
	@Override
	public List<BoardVO> myBoardList(int memberNo) {
		//내가 쓴 게시글 목록
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO vo;
		//세션 아이디로 member_no를 보내고 세션아이디랑 내 member_no가 같으면 출력하는 식으로
//		String id = session.getAttribute("id");	//세션 아이디 구하기 //controller!!
		//로그인 정보를 세션에 저장  -> 로그인에 추가해야함            
		//session().setAttribute("MemberVO", member);
		////request.getSession().setAttribute("MemberVO", member);
		
		//MemberVO member = new memberVO(); // vo 인스턴스 생성
		//member.setMemberNo (1); // bminfo.BM_ID 에 1 값저장
		//session.setAttribute("member", member);  // 세션에 bminfo 라는 변수로 vo  값 저장
		
		
		
		
		
		
//		String id = null;
		String sql = "SELECT BOARD_WRITER,BOARD_TITLE,BOARD_DATE FROM BOARD WHERE MEMBER_NO = ?";

		 // order by regdate desc
	
		 
		try {
			conn = dao.getConnection();
			psmt  = conn.prepareStatement(sql);
			psmt.setInt(1, memberNo);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				vo = new BoardVO();
				//vo.setBoardNo(rs.getInt("board_no"));
				vo.setBoardWriter(rs.getString("board_writer"));
				vo.setBoardTitle(rs.getString("board_title"));
				vo.setBoardDate(rs.getString("board_date"));
				list.add(vo);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return list;
	}




	@Override
	public List<ScrapVO> myScrapList(int memberNo) {
		// 내 스크랩 목록
		List<ScrapVO> list = new ArrayList<ScrapVO>();
		ScrapVO vo;
		//스크랩이랑 보드 조인
		String sql = " SELECT S.SCRAP_NO,B.BOARD_NO , B.BOARD_TITLE FROM SCRAP S left OUTER JOIN BOARD B ON S.board_no = B.board_no WHERE S.MEMBER_NO = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1,memberNo); //edit!!
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				BoardVO board = new BoardVO();
				vo = new ScrapVO();
				vo.setScrapNo(rs.getInt("scrap_no"));
			board.setBoardNo(rs.getInt("board_no"));
			board.setBoardTitle(rs.getString("board_title"));
		vo.setBvo(board);
		list.add(vo);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
		
	}

	@Override
	public List<ScrapVO> myStudyList(int memberNo) {
		//찜한 스터디 리스트
		List<ScrapVO> list = new ArrayList<ScrapVO>();
		ScrapVO vo;
		

// 스크랩이랑 스터디 조인하는 방식		
		String sql = "SELECT B.SCRAP_DATE , s.STUDY_NO , s.STUDY_TITLE FROM SCRAP B RIGHT OUTER JOIN  study s ON b.study_no = s.study_no WHERE s.MEMBER_NO = ?";
		
	// study 게시판이랑 조인
//		String sql = "select s.study_writer,s.study_title"
//				+ "from study s"
//				+ "left outer join member m on s.member_no =  m.member_no  where s.member_no = ?";		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, memberNo);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
			StudyVO study = new StudyVO();	
			vo = new ScrapVO();
			vo.setScrapDate(rs.getString("scrap_date"));
			study.setStudyNo(rs.getInt("study_no"));
			study.setStudyTitle(rs.getString("study_title"));
			vo.setSvo(study);
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
			if(rs != null) rs.close();
			if(psmt !=null)psmt.close();
			if(conn != null) conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}