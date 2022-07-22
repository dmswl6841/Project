<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
label {
width: 200px
}
.container{
	margin: 20px
}
h1{
	margin: 40px

}
.box {
	margin: 5px;
	width: 200px
}
</style>
</head>

<body>
<div align="center" class="container">
	<h1>${member.memberNick }</h1>
	<c:set var="TextValue" value="${member.memberId }" />
	<c:set var="length" value="${fn:length(TextValue)}" />
	<c:set var="string2"
		value="${fn:substring(TextValue, length -3, length)}" />

	<form action="memberUpdateSubmit.do" name="frm" id="frm" method="post">

		<!-- 일반회원 수정: 이메일, 닉넴, 깃  -->
		<c:if test="${string2 ne 'kId' }">

		
			<dl>
			<dt><label for="id">회원 아이디</label>
			<dd><input type="text" name="id" id="id" value="${member.memberId}"
				readonly></dd>
	        </dl>
	        
	        
	        <dl>
	        <dt><label for="nickname">회원 닉네임</label>
			<dd><input type="text" name="nickname" id="nickname" value="${member.memberNick}"></dd>
			</dl>
			

			<br>
			
			
             <dl>
			<dt><label for="email">회원 이메일</label>
			<dd><input type="text" name="email" id="email" value="${member.memberEmail}">	</dd>
			</dl>
			
			<dl>
			<dt>
			<label for="git">Git 주소</label>

			<dd><input type="text" name="git" id="git" value="${member.memberGit}"></dd>
			</dl>

	
			<br>
			<!-- 
			<input type="submit" value="수정">	
			 -->
		</c:if>
		<!-- 카카오 회원 수정: 닉넴, 깃만 -->
		<c:if test="${string2 eq 'kId' }">
			
			<!-- 닉네임 -->
			<label for="nickname">회원 닉네임</label>
			<input class="box" type="text" name="nickname" id="nickname"
				value="${member.memberNick}">
			<br>

			<!-- 이메일 -->
			<label for="email">카카오회원은이멜못바꿈</label>
			<input class="box" type="text" name="email" id="email"
				value="${member.memberEmail}" readonly>
			<br>

			<!-- 깃 -->
			<label for="git">Git 주소</label>
			<input class="box" type="text" name="git" id="git" value="${member.memberGit}">
			<br>
			<br>
			
		</c:if>
		<input type="submit" class="btn btn-success" value="수정">
	</form>
</div>
</body>

</html>