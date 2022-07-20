<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<body>
	<h1>${member.memberNick }</h1>
	<c:set var="TextValue" value="${member.memberId }" />
	<c:set var="length" value="${fn:length(TextValue)}" />
	<c:set var="string2"
		value="${fn:substring(TextValue, length -3, length)}" />

	<form action="memberUpdateSubmit.do" name="frm" id="frm" method="post">

		<!-- 일반회원 수정: 이메일, 닉넴, 깃  -->
		<c:if test="${string2 ne 'kId' }">
			<label for="id">회원 아이디 못바꿈</label>
			<input type="text" name="id" id="id" value="${member.memberId}"
				readonly>
	
			<label for="nickname">회원 닉네임</label>
			<input type="text" name="nickname" id="nickname"
				value="${member.memberNick}">
			<br>

			<label for="email">회원 이메일</label>
			<input type="text" name="email" id="email"
				value="${member.memberEmail}">
			<br>
			<label for="git">Git 주소</label>
			<input type="text" name="git" id="git" value="${member.memberGit}">
			<br>
			<!-- 
			<input type="submit" value="수정">	
			 -->
		</c:if>
		<!-- 카카오 회원 수정: 닉넴, 깃만 -->
		<c:if test="${string2 eq 'kId' }">
			
			<!-- 닉네임 -->
			<label for="nickname">회원 닉네임</label>
			<input type="text" name="nickname" id="nickname"
				value="${member.memberNick}">
			<br>

			<!-- 이메일 -->
			<label for="email">카카오회원은이멜못바꿈</label>
			<input type="text" name="email" id="email"
				value="${member.memberEmail}" readonly>
			<br>

			<!-- 깃 -->
			<label for="git">Git 주소</label>
			<input type="text" name="git" id="git" value="${member.memberGit}">
			<br>
			<br>
		</c:if>
		<input type="submit" value="수정">
	</form>

</body>

</html>