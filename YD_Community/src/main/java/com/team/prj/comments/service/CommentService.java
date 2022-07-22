package com.team.prj.comments.service;

import java.util.List;

import com.team.prj.comments.vo.CommentsVO;

public interface CommentService {
	
	// 조회
	List<CommentsVO> commentList(int no);
	List<CommentsVO> commentListStudy(int no);
	
	// 1건 조회
	CommentsVO commentSelectOne(int no);
	
	// 등록
	CommentsVO commentInsert(CommentsVO vo);
	CommentsVO commentInsertStudy(CommentsVO vo);
	// 수정
	int commentUpdate(CommentsVO vo);
	// 삭제
	int commentDelete(int no);
	
	// 댓글 수
	int commentCount(int no);
	int commentCountStudy(int no);
	
}