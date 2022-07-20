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
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown-content a:hover {
	background-color: #ddd;
}

.dropdown:hover .dropdown-content {
	display: block;
}

.dropdown:hover .dropbtn {
	background-color: #3e8e41;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js"></script>
</head>

<body>
	<div align="center">
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

			<div class="dropdown">
				<button class="dropbtn">최근활동</button>
				<div class="dropdown-content">
					<a href="memberRecent.do?no=${boardWriter }">자유게시판</a> <a href="memberStudyRecent.do?no=${boardWriter }">스터디</a>
				</div>
			</div>

			<div class="dropdown">
				<button class="dropbtn">나의 게시글</button>
				<div class="dropdown-content">
					<a href="memberBoard.do?no=${boardWriter }">자유게시판</a> <a href="memberStudyBoard.do?no=${boardWriter }">스터디</a>
				</div>
			</div>

			<div class="dropdown">
				<button class="dropbtn">북마크</button>
				<div class="dropdown-content">
					<a href="memberScrap.do??no=${boardWriter }">스크랩</a> <a href="memberStudy.do?no=${boardWriter }">찜한
						스터디</a>
				</div>
			</div>


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
										<td align="center">#${r.boardDate } ${r.boardTitle }</td>


									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="6" align="center">최근 활동이 없습니다!</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>

				</table>
			</div>
		</div>
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