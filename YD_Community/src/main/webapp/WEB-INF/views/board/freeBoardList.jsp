<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
<script src="js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div align="center">

		<!-- 자유게시판 -->
		<div>
			<h1>자유게시판</h1>
		</div>
		<br>


		<!-- 공통 검색기능 -->
		<div>
			<form id="frm">
				<select id="key" name="key">
					<option value="board_title">제목</option>
					<option value="board_subject">내용</option>
					<option value="board_writer">작성자</option>
				</select> &nbsp; 
				<input type="text" id="val" name="val">&nbsp;&nbsp;
				<input type="button" value="검색" onclick=boardSearch()>
			</form>
		</div>
		<br>
		<!-- 공통 검색기능 -->


		<div>
			<table border="1">
				<thead>
					<tr>
						<th width="70">No.</th>
						<th width="130">작성자</th>
						<th width="200">제목</th>
						<th width="130">작성일자</th>
						<th width="180">첨부파일</th>
						<th width="70">스크랩수</th>
						<th width="70">추천수</th>
						<th width="70">조회수</th>
					</tr>
				</thead>
				<tbody id="tb">
					<c:choose>
						<c:when test="${not empty freeboardlist }">
							<c:forEach items="${freeboardlist }" var="b">
								<tr>
									<td>${b.boardNo }</td>
									<td><a href="myPage.do?no=${b.memberNo}">${b.boardWriter }</a></td>
									<td><a href="boardView.do?board_no=${b.boardNo}">${b.boardTitle }</a></td>
									<td>${b.boardDate }</td>
									<td>${b.boardAttech }</td>
									<td>${b.boardScrap }</td>
									<td>${b.boardRecommend }</td>
									<td>${b.boardHit }</td>
								</tr>
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
		</div>
		<br>

		<!-- 글쓰기 버튼 -->
		<div>
			<button type="button" onclick="location.href='boardForm.do'">글쓰기</button>
		</div>
	</div>




	<script type="text/javascript">
		function boardSearch() {
			let key = $("#key").val();
			let val = $("#val").val();
			$.ajax({
				url : "ajaxBoardSearche.do",
				type : "post",
				data : {key : key, val : val},
				dataType : "Json",
				success : function(result){
					if(result.length > 0) {
						jsonHtmlConvert(result);
					} else {
						alert("검색한 결과가 없습니다.");
					} console.log(result);
				},
				error : function() {	
				}
			})
		}
		
		function jsonHtmlConvert(data) {
			$("#tb").remove();
			var tbody = $("<tbody id />");
			$.each(data, function(index, item){
				var row = $("<tr />").append(
							$("<td />").text(item.boardNo),
							$("<td />").text(item.boardWriter),
							$("<td />").text(item.boardTitle),
							$("<td />").text(item.boardDate),
							$("<td />").text(item.boardAttech),
							$("<td />").text(item.boardScrap),	
							$("<td />").text(item.boardRecommend),
							$("<td />").text(item.boardHit),
						);
				tbody.append(row);
			});
			$('table').append(tbody);
		}
	</script>

</body>
</html>