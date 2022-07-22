<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main.jsp</title>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>


<style>
body {
	align-self: center;
	color: white;
}

.scrolltable {
	display: block;
	border: none;
	background-color: #f0fefa;
	border-radius: 15px;
	overflow: auto;
}

.scrolltable ::-webkit-scrollbar {
  width: 10px;
  background-color: white;
}

.scrolltable::-webkit-scrollbar-thumb {
  background-color:#424242; /*스크롤바의 색상*/
  border-radius: 50px;
}

.scrolltable::-webkit-scrollbar-track {
  background-color:#f0fefa /*스크롤바 트랙 색상*/
}


.scrolltable:hover {
	display: block;
	overflow: auto;
	border: none;
	background-color: #e2fef6;
	border-radius: 15px;
}


}
tr>td>a {
	text-decoration-line: none;
}

div>a {
	text-decoration-line: none;
}

table {
	width: 380px;
	height: 200px;
	border: 1px solid #000;
	border-spacing: 0;
	background-color: 008d62;
}

tr:hover {
	background-color: 00714e;
}

th {
	border: 1px solid #000;
	background: #ace;
}

td {
	border: 1px solid #000;
	text-align: center;
}

.box1 {
	float: center;
}

 
.box2 {
	clear: both;
	float: center;
	margin-left: -20%;
	margin-right: 20%;
}

.box3 {
	margin-left: 20%;
	margin-right: -20%;
}
  
</style>
</head>


<body>

	<div class="box1">
		<div>
			<a href="hotBoardList.do">🏆 인기글</a>
		</div>
		<div>
			<table border="1" class="scrolltable" id="li">
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



	<div class="box2">
		<div>
			<a href="noticeBoardList.do">📢 공지사항</a>
		</div>
		<div>
			<table border="1" class="scrolltable" id="li">
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



	<div class="box3">
		<div>
			<a href="qnaBoardList.do">💡 QnA게시판</a>
		</div>
		<div>
			<table border="1" class="scrolltable" id="li">
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