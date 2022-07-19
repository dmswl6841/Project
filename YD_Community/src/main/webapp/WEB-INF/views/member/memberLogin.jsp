<%@page import="com.team.prj.member.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	MemberVO member = (MemberVO) session.getAttribute("member");
%>
<div align="center">
	<h1>${message }</h1>
	<h2>${member}</h2>
		<input type="button" value="홈" onclick="location.href='main.do'">
		<input type="button" value="로그아웃" onclick="location.href='memberLogout.do'">
</div>
</body>
</html> 