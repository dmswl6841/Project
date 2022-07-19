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
	<div align="center">
		<div><h1>나의 게시글</h1></div>


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
						<th width="130">작성자</th>
						<th width="300">제목</th>
						<th width="130">작성일자</th>
				</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty list }">
							<c:forEach items="${list }" var="x">
								<tr>
									<td align="center">${x.studyWriter }</td>
									<td><a href="StudyView.do?study_no=${x.studyNo}">${x.studyTitle }</a></td>
									
									
									<td align="center">${b.studyDate }</td>
										
									
									
									</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6" align="center">
									작성한 게시글이 존재하지 않습니다!
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div><br>
		
</body>
</html>