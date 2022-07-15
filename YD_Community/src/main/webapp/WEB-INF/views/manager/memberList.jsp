<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
</head>
<body>
	<div align="center">
		<div>회원 목록</div>
	</div>
	<form id="frm">
		<select id="key" name="key">
			<option value="member_id">아이디</option>
			<option value="member_warning">경고횟수</option>
			<option value="member_jion">가입승인</option>
		</select>&nbsp;
		<input type="text" id="val" name="val">&nbsp;
		<input type="button" value="검색" onclick="memberSearch()">
	</form>
	<table border="1">
		<thead>
			<tr>
				<th><input type="checkbox"></th>
				<th>사용자 ID</th>
				<th>사용자 명</th>
				<th>경고 횟수</th>
				<th>회원정보</th>
				<th>가입승인</th>
				<th>탈퇴</th>
			</tr>
		</thead>
		<tbody>
			<!--<c:choose>
				<c:when test="${not empty list }">-->
					<c:forEach items="${list }" var="m">
						<tr>
							<td>${m.memberId }</td>
							<td>${m.memberNick }</td>
							<td>${m.memberWarning }</td>
							<td>${function memInfo() }</td>
							<td><input type="button" value="가입승인" onclick="memberjoin()"></td>
							<td><input type="button" value="탈퇴" onclick="memberDelete()"></td>
						</tr>
					</c:forEach>
				<!--</c:when>
			</c:choose>-->
		</tbody>
	</table>
</body>
</html>