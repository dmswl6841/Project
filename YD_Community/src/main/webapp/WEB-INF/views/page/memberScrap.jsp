<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="js/jquery-3.6.0.min.js"></script>
</head>
<body>
<div align="center"></div>
	<div><h1>스크랩 목록</h1></div>
	
		
	<nav>
		<ul>
			<li><a href="memberRecent.do">최근활동</a></li>
			<li><a href="memberBoard.do">게시글</a></li>
			<li><a href="memberScrap.do">스크랩</a></li>
			<li><a href="memberStudy.do">찜한 스터디</a></li>
		</ul>
	</nav>
	
	<div>
	<table>
		<thead>
			<tr>
			<th width="130">스크랩 번호</th>
			<th width="130">게시글 번호</th>
			<th width="200">게시글 제목</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
						<c:when test="${not empty list }">
							<c:forEach items="${list }" var="c">
								<tr>
									<td>${c.scrapNo }</td>
									<td>${c.boardNo }</td>
									<td><a href="boardView.do?board_no=${c.boardNo}">${c.boardTitle }</a></td>
									
									
									</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6" align="center">
									스크랩 글이 존재하지 않습니다!
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
		</tbody>

	</table>	
	</div>
</body>
</html>