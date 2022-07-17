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

		<div>
			<table >
			<thead>
				<tr>  
						<th width="130">스크랩 날짜</th>
						<th width="130">스터디 글번호</th>
						<th width="200">스터디 제목</th>
				</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty list }">
							<c:forEach items="${list }" var="s">
								<tr>
									<td>${s.scrapDate }</td>
									<td>${s.studyNo }</td>
									<td>${s.studyTitle }</td>
									
									
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