package com.team.prj.report.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportVO {
	private int reportNo;
	private Date reportDate;
	private String reportReason;
	private int BoardNo;
	private int memberNo;
}
