package com.team.prj.calendar.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalendarVO {
	private int calNo;
	private String calTitle; //일정 이름
	private String calStart; //일정 시작날짜+시간
	private String calEnd; //일정 종료날짜+시간
}
