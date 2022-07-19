<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>

<style>
.dropbtn {
  background-color: #04AA6D;
  color: white;
  padding: 16px;
  font-size: 16px;
  border: none;
}
 
.dropdown {
  position: relative;
  display: inline-block;
}
 
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}
 
.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}
 
.dropdown-content a:hover {background-color: #ddd;}
 
.dropdown:hover .dropdown-content {display: block;}
 
.dropdown:hover .dropbtn {background-color: #3e8e41;}
</style>

<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>

	<div align="center">
		<div><h1>찜한 스터디</h1></div>
		
			
	<div class="dropdown">
  <button class="dropbtn">최근활동</button>
  <div class="dropdown-content">
   <a href="memberRecent.do">자유게시판</a>
    <a href="memberStudyRecent.do">스터디</a>
  </div>
  </div>
  
  <div class="dropdown">
   <button class="dropbtn">나의 게시글</button>
  <div class="dropdown-content">
   <a href="memberBoard.do">자유게시판</a>
   <a href="memberStudyBoard.do">스터디</a>
  </div>
  </div>
  
  <div class="dropdown">
   <button class="dropbtn">북마크</button>
  <div class="dropdown-content">
   <a href="memberScrap.do">스크랩</a>
     <a href="memberStudy.do">찜한 스터디</a>
  </div>
  </div>

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