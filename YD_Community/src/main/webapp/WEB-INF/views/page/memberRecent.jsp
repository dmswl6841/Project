<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나의 최근 활동</title>
<script src="js/jquery-3.6.0.min.js"></script>
</head>
<body>

	<div align="center"></div>
	<div><h1>나의 최근 활동</h1></div>
	
	<nav>
			<ul style="list-style: none;">
			<li><a href="memberRecent.do">최근활동</a></li>
			<li><a href="memberStudyRecent.do">최근활동/스터디</a></li>
			<li><a href="memberBoard.do">자유게시판 게시글</a></li>
			<li><a href="memberStudyBoard.do">스터디 게시글</a></li>
			<li><a href="memberScrap.do">스크랩</a></li>
			<li><a href="memberStudy.do">찜한 스터디</a></li>
		</ul>
	</nav>
	
	
	<div>
	<table border="1">
		<thead>
			<tr>
			<th width="400">목록</th>
		
			
			</tr>
		</thead>
		<tbody>
			<c:choose>
						<c:when test="${not empty list }">
							<c:forEach items="${list }" var="r">
								<tr>
									<td align="center">#${r.boardDate }  ${r.boardTitle }</td>
									
									
									</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6" align="center">
									최근 활동이 없습니다!
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
		</tbody>

	</table>	
	</div>
</body>
</html>