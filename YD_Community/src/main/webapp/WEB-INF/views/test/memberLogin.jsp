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
	<a href="main.do">홈 가기</a><br>
	<a href="memberLogout.do">로그아웃</a>
</div>
</body>
</html> 