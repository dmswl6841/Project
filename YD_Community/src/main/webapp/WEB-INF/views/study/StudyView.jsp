<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스터디게시글 보기</title>
<script src="js/jquery-3.6.0.min.js"></script>
</head>

<body>
	<div align="center">
	<div><h1>스터디게시글 보기</h1></div>
	
	
		<div>
			<table border="1">
				<tbody>
					<c:choose>
						<c:when test="${not empty studyviewlist}">
							<c:forEach items="${studyviewlist }" var="b">
								<tr>
									<td>${b.studyTitle }</td>
									<td>${b.studySystem }</td>
									<td>${b.studyPeriod }</td>
									<td>${b.studyLanguage }</td>
								</tr>	
								<tr>
									<td>${b.studyDate }</td>
									<td>${b.studyWriter }</td>
								</tr>
								<tr>
									<td colspan="3">${b.studySystem }</td>
									<td>${b.studyAttech }</td>
							
								</tr>
								
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="8" align="center">
									게시글이 존재하지 않습니다
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div><br>
	
	
	
	
	</div>
</body>
</html>