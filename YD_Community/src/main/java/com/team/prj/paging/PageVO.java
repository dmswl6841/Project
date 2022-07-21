package com.team.prj.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageVO {
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int total;
	//현재 페이지 번호, 한 페이지에 표출할 데이터 개수
	private Criteria cri;
	
	public PageVO(Criteria _criteria, int _total) {
		this.cri = _criteria;
		this.total = _total;
		this.endPage = (int) (Math.ceil(_criteria.getPageNum() / 10.0)) * 10;
		this.startPage = this.endPage - 9;
		int realEnd = (int) (Math.ceil((_total * 1.0) / _criteria.getAmount()));
		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}

	

}





	

