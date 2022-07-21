package com.team.prj.comments.service;

import java.util.List;

import com.team.prj.comments.vo.CommentsVO;

public interface CommentService {
	
	// 조회
	List<CommentsVO> commentList(int no);
	
	// 등록
	CommentsVO commentInsert(CommentsVO vo);
	// 수정
	int commentUpdate(int no);
	// 삭제
	int commentDelete(int no);
}
