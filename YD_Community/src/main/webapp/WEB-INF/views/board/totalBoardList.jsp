<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<style>
body {
	align-self: center;
	color: white;
}

.scrolltable {
	display: block;
	border: none;
	background-color: #f0fefa;
	border-radius: 15px;
	overflow: auto;
}

.scrolltable ::-webkit-scrollbar {
  width: 10px;
  background-color: white;
}

.scrolltable::-webkit-scrollbar-thumb {
  background-color:#424242; /*스크롤바의 색상*/
  border-radius: 50px;
}

.scrolltable::-webkit-scrollbar-track {
  background-color:#f0fefa /*스크롤바 트랙 색상*/
}


.scrolltable:hover {
	display: block;
	overflow: auto;
	border: none;
	background-color: #e2fef6;
	border-radius: 15px;
}

tr>td>a {
	text-decoration-line: none;
}

div>a {
	text-decoration-line: none;
}

table {
	width: 380px;
	height: 200px;
	border: 1px solid #000;
	border-spacing: 0;
	background-color: 008d62;
}

tr:hover {
	background-color: 00714e;
}

th {
	border: 1px solid #000;
	background: #ace;
}

td {
	border: 1px solid #000;
	text-align: center;
}

.box1 {
	float: center;
	margin-left: 20%;
	margin-right: -20%;
}

.box2 {
	margin-left: 20%;
	margin-right: -20%;
}
 
.box3 {
	clear: both;
	float: center;
	margin-left: -20%;
	margin-right: 20%;
}


.box4 {
	margin-left: -20%;
	margin-right: 20%;
}
  article {
  width: auto;
  height: auto; /* only for demonstration, should be removed */
}

</style>

<head>
<!-- 게시판 모아보는 커뮤니티 메인 페이지입니다. -->
<meta charset="UTF-8">
<title>커뮤니티</title>
<script src="js/jquery-3.6.0.min.js"></script>
</head>

<body>
	<div>
		<h1>YD 커뮤니티</h1>
	</div>
	<div class="box1" >
		<div>
			<a href="hotBoardList.do">🏆 인기글</a>
		</div>
		<div>
			<table border="1" class="scrolltable">
				<tbody>
					<c:choose>
						<c:when test="${not empty totalboardlist }">
							<c:forEach items="${totalboardlist }" var="b">
								<c:if test="${b.boardHot =='HOT' }">
									<tr>
										<td><button
												style="color: #FFFFFF; background-color: #FE2E2E" disabled>HOT</button></td>
										<td>${b.boardCategory }</td>
										<td>#${b.boardNo }</td>
										<td><a href="boardView.do?board_no=${b.boardNo}">${b.boardTitle }</a></td>
										<td>👍 ${b.boardRecommend }</td>
										<td>👀 ${b.boardHit }</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6" align="center">게시글이 존재하지 않습니다</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<br>
		</div>
	</div>





	<div class="box2" id="li">
		<div>
			<a href="noticeBoardList.do">📢 공지사항</a>
		</div>
		<div>
			<table border="1" class="scrolltable">
				<tbody>
					<c:choose>
						<c:when test="${not empty totalboardlist }">
							<c:forEach items="${totalboardlist }" var="b">
								<c:if test="${b.boardCategory =='공지' }">
									<tr>
										<td width="70">#${b.boardNo }</td>
										<td width="250"><a
											href="boardView.do?board_no=${b.boardNo}">${b.boardTitle }</a></td>
										<td width="70">👀 ${b.boardHit }</td>
									</tr>
								</c:if>

							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6" align="center">게시글이 존재하지 않습니다</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<br>
		</div>
	</div>




	<div class="box3" id="li">
		<div>
			<a href="freeBoardList.do">🎡 자유게시판</a>
		</div>
		<div>
			<table border="1" class="scrolltable">
				<tbody>
					<c:choose>
						<c:when test="${not empty totalboardlist }">
							<c:forEach items="${totalboardlist }" var="b">
								<c:if test="${b.boardCategory =='자유' }">
									<tr>
										<td width="70">#${b.boardNo }</td>
										<td><a href="boardView.do?board_no=${b.boardNo}">${b.boardTitle }</a></td>
										<td width="70">👍 ${b.boardRecommend }</td>
										<td width="70">👀 ${b.boardHit }</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="6" align="center">게시글이 존재하지 않습니다</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<br>
		</div>
	</div>




	<div align="center" class="box4" id="li">
		<div>
			<a href="qnaBoardList.do">💡 QnA게시판</a>
		</div>
		<div>
			<table border="1" class="scrolltable">
				<tbody>
					<c:choose>
						<c:when test="${not empty totalboardlist }">
							<c:forEach items="${totalboardlist }">
								<c:if test="${b.boardCategory =='QnA' }">
									<tr>
										<td width="70">#${b.boardNo }</td>
										<td><a href="boardView.do?board_no=${b.boardNo}">${b.boardTitle }</a></td>
										<td width="70">👍 ${b.boardRecommend }</td>
										<td width="70">👀 ${b.boardHit }</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="8" align="center">게시글이 존재하지 않습니다</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
			<br>
		</div>
	</div>



	<script type="text/javascript">
		function noticeSearch() {
			let categorykey = $("#categorykey").val();
			let categoryval = $("#categoryval").val();
			let searchkey = $("#searchkey").val();
			let searchval = $("#searchval").val();
			$.ajax({
				url : "ajaxboardSearche.do",
				type : "post",
				data : {categorykey : categorykey, categoryval : categoryval, searchkey : searchkey, searchval : searchval},
				dataType : "json",
				success : function(result){
					if(result.length > 0) {
						jsonHtmlConvert(result);
					}else {
						alert("검색한 결과가 없습니다.");
					}
				},
				error : function() {
					
				}
			})
		}
		
		function jsonHtmlConvert(data) {
			$('tbody').remove();
			var tbody = $("<tbody />");
			$.each(data, function(index, item){
				var row = $("<tr />").append(
							$("<td />").text(b.boardNo),
							$("<td />").text(b.boardWriter),
							$("<td />").text(b.boardTitle),
							$("<td />").text(b.boardDate),
							$("<td />").text(b.boardAttech),
							$("<td />").text(b.boardScrap),	
							$("<td />").text(b.boardRecommend),
							$("<td />").text(b.boardHit),
						);
				tbody.append(row);
			});
			$('table').append(tbody);
		}
	</script>


</body>
</html>