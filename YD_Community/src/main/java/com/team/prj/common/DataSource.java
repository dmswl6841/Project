package com.team.prj.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

	private static DataSource dataSource = new DataSource();	//인스턴스 생성
	private Connection conn;
	
	private DataSource() {	//private 생성자 
		
	}
	
	public static DataSource getInstance() {	//인스턴스를 리턴한다
		return dataSource;
	} 
	//---------------------------------------여기까지가 싱글톤의 기본
	
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.21:1521","team","1234");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} return conn;
	}
}
