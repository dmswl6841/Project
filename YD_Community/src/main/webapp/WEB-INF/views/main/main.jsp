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
<h1>메인화면</h1>
<%
	MemberVO member = (MemberVO) session.getAttribute("member"); 
%>

<h2>${member}</h2>
</body>
</html>