<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>


<!-- 게시판 모아보는 커뮤니티 메인 페이지입니다. -->

<head>
<meta charset="UTF-8">
<title>커뮤니티</title>
<script src="js/jquery-3.6.0.min.js"></script>
</head>

<body>
	<div align="center">
		<div>주간 인기글</div>
		<div>
			<table border="1">
				<tbody>
					<c:choose>
						<c:when test="${not empty list }">	
							<c:forEach items="${list }" var="b">
						<tr>
							<td>${b.boardCategory }</td>
							<td><a href="views/board/HotBoardList">${b.boardTitle }</a></td>
							<td>${b.commentNo }</td>
							<td>${b.boardHit }</td>
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
			</table><br>
		</div>
	</div>
	
	
	
	<div align="left">
		<div>자유 게시판</div>
		<div>
			<table border="1">
				<tbody>
					<c:choose>
						<c:when test="${not empty list }">	
							<c:forEach items="${list }" var="b">
						<tr>
							<td>${b.boardCategory }</td>
							<td><a href="views/board/FreeBoardList">${b.boardTitle }</a></td>
							<td>${b.commentNo }</td>
							<td>${b.boardHit }</td>
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
			</table><br>
		</div>
	</div>
	
	
	
	
	<div align="right">
		<div>QnA 게시판</div>
		<div>
			<table border="1">
				<tbody>
					<c:choose>
						<c:when test="${not empty list }">	
							<c:forEach items="${list }" var="b">
						<tr>
							<td>${b.boardCategory }</td>
							<td><a href="views/board/QnaBoardList">${b.boardTitle }</a></td>
							<td>${b.commentNo }</td>
							<td>${b.boardHit }</td>
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
			</table><br>
		</div>
	</div>
	
	
	
</body>
</html>