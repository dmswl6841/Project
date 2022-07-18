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
									<td>${r.boardTitle }</td>
									
									
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