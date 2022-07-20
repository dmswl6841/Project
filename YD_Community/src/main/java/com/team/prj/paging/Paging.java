package com.team.prj.paging;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Paging {
	private int page =1;
	private int totalCount;
	private int beginPage;
	private int endPage;
	private int displayRow =10;
	private int displayPage = 10;
	boolean prev;
	boolean next;
	

	private void paging() {
		endPage = ((int)Math.ceil(page/(double)displayPage))*displayPage;
		System.out.println("endPage : " + endPage);
		
			beginPage = endPage - (displayPage - 1);
			System.out.println("beginPage : " + beginPage);
			
			int totalPage = (int)Math.ceil(totalCount/(double)displayRow);
				
				if(totalPage<endPage) {
					endPage = totalPage;
					next = false;
				} else {
					next = true;
				}
		prev = (beginPage==1)?false:true;
		System.out.println("endPage : " + endPage);
		System.out.println("totalPage : " + totalPage);
	}
		
}
