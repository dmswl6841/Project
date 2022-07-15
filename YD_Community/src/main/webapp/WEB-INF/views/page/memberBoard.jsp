<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내가 작성한 게시글 목록</title>
<script src="js/jquery-3.6.0.min.js"></script>
</head>
<body>
<%= session.getId() %>
	<div align="left">
		<div><h1>나의 게시글</h1></div>

		<div>
			<table >
			<thead>
				<tr>
						<th width="70">번호</th>
						<th width="130">작성자</th>
						<th width="200">제목</th>
						<th width="130">작성일자</th>
				</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty list }">
							<c:forEach tems="${list }" var="b">
								<tr>
									<td>${b.boardId }</td>
									<td>${b.boardWriter }</td>
									<td>${b.boardTitle }</td>
									<td>${b.boardDate }</td>
									</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6" align="center">
									게시글이 존재하지 않습니다
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div><br>
		<div>
			<c:if test="{session.}"></c:if>
		</div>
</body>
</html>