<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
<!DOCTYPE html>
<html>
<head>
<style>

.w-btn-green2 {
    background-color: #519d9e;
    color: #9dc8c8;
}

.w-btn-green2-outline {
    border: 3px solid #519d9e;
    color: black;
}

.w-btn-green2-outline:hover {
    background-color: #519d9e;
    color: #9dc8c8;
}

</style>


<meta charset="UTF-8">
<title>내가 작성한 게시글 목록</title>
<script src="js/jquery-3.6.0.min.js"></script>
</head>

<body>
	<div align="center">
	${memberNo }
	<br>
	${boardWriter }
		<div>
			<div>
				<c:if test="${memberNo eq boardWriter }">
					<input type="button" id="modify"
						onclick="location.href='memberUpdate.do'" value="내 정보 수정">
					<input type="button" id="joinout" onclick="joinoutCheck()"
						value="탈퇴">
				</c:if>
			</div>
		</div>
		<br>
		<h1>${boardWriter }</h1>
		<div>
			<div>
				<h1>${memberNick }님의 최근 활동</h1>
			</div>
	
	

<button class="w-btn-outline w-btn-green2-outline" type="button"

	onclick="location.href='memberBoard.do?no=${boardWriter}'">
       게시글
    </button>
    
    <button class="w-btn-outline w-btn-green2-outline" type="button"
   onclick="location.href='memberComments.do?no=${boardWriter}'">
    댓글
    </button>
    
    <button class="w-btn-outline w-btn-green2-outline" type="button"
    onclick="location.href='memberStudyBoard.do?no=${boardWriter}'">
       스터디
    </button>
    
    
    <button class="w-btn-outline w-btn-green2-outline" type="button"
     onclick="location.href='memberScrap.do?no=${boardWriter}'">
       스크랩
    </button>

  	<div align="center">

		<div>
			<table align="center" border="1">
			<thead>
				<tr>  
						<th width="50">No.</th>
						<th width="100">카테고리</th>
						<th width="250">제목</th>
						<th width="100">작성자</th>
						<th width="150">작성일자</th>
				</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty list }">
							<c:forEach items="${list }" var="b">
								<tr>
									<td align="center">${b.boardNo }</td>
									<td align="center">${b.boardCategory }</td>
									<td><a href="boardView.do?board_no=${b.boardNo}">${b.boardTitle }</a></td>
									<td align="center">${b.boardWriter }</td>
									<td align="center">${b.boardDate }</td>
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
	</div>
	<script>
		function joinoutCheck() {
			let q = confirm("정말 탈퇴하시겠습니까? 이 행위는 되돌릴 수 없습니다.");
			if (q) {
				location.href = "memberJoinout.do";
			}
		}
	</script>
	
</body>

</html>