<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

	<div align="center">
		<div><h1>찜한 스터디</h1></div>
		
			
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
			<table >
			<thead>
				<tr>  
						<th width="100"></th>
						<th width="250"></th>
						
				</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty list }">
							<c:forEach items="${list }" var="s">
								<tr>
									<td align="center">${s.scrapDate }</td>
									<td><a href="StudyView.do?study_no=${s.svo.studyNo }">#${s.svo.studyNo }번 스터디를 찜하셨습니다!</a></td>
								
									
								 													
									</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6" align="center">
									스터디를 찜해보세요!
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>