<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main.jsp</title>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>

<style>
body {
	background-color: #f8f9fa;
}
</style>
</head>
<body>
<div align="center">
		<div>
			<a href="hotBoardList.do">🏆 인기글</a>
		</div>
		<div>
			<table border="1">
				<tbody>
					<c:choose>
						<c:when test="${not empty totalboardlist }">
							<c:forEach items="${totalboardlist }" var="b">
								<c:if test="${b.boardHot =='HOT' }">
									<tr>
										<td><button
												style="color: #FFFFFF; background-color: #FE2E2E" disabled>HOT</button></td>
										<td>${b.boardCategory }</td>
										<td>#${b.boardNo }</td>
										<td><a href="boardView.do?board_no=${b.boardNo}">${b.boardTitle }</a></td>
										<td>👍 ${b.boardRecommend }</td>
										<td>👀 ${b.boardHit }</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6" align="center">게시글이 존재하지 않습니다</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<br>
		</div>
	</div>
	
	<div align="left">
		<div>
			<a href="noticeBoardList.do">📢 공지사항</a>
		</div>
		<div>
			<table border="1">
				<tbody>
					<c:choose>
						<c:when test="${not empty totalboardlist }">
							<c:forEach items="${totalboardlist }" var="b">
								<c:if test="${b.boardCategory =='공지' }">
									<tr>
										<td>#${b.boardNo }</td>
										<td><a href="boardView.do?board_no=${b.boardNo}">${b.boardTitle }</a></td>
										<td>👀 ${b.boardHit }</td>
									</tr>
								</c:if>

							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6" align="center">게시글이 존재하지 않습니다</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<br>
		</div>
	</div>
	<div align="right">
		<div>
			<a href="qnaBoardList.do">💡 QnA게시판</a>
		</div>
		<div>
			<table border="1">
				<tbody>
					<c:choose>
						<c:when test="${not empty totalboardlist }">
							<c:forEach items="${totalboardlist }" var="b">
								<c:if test="${b.boardCategory =='QnA' }">
									<tr onclick="clickBoardView()">
										<td>#${b.boardNo }</td>
										<td><a href="boardView.do?board_no=${b.boardNo}">${b.boardTitle }</a></td>
										<td>👍 ${b.boardRecommend }</td>
										<td>👀 ${b.boardHit }</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="8" align="center">게시글이 존재하지 않습니다</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<br>
		</div>
	</div>
	
            
</body>
</html>