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
				<select id="categorykey" name="categorykey">
					<option value="board_category" id="categoryval" name="categoryval"
						selected>자유</option>
					<option value="board_category" id="categoryval" name="categoryval">QnA</option>
					<option value="board_category" id="categoryval" name="categoryval">공지</option>
					<option value="board_category" id="categoryval" name="categoryval">HOT</option>
				</select>
			</form>

			<form id="frm">
				<select id="searchkey" name="searchkey">
					<option value="board_title">제목</option>
					<option value="board_subject">내용</option>
					<option value="board_writer">작성자</option>
				</select> &nbsp; <input type="text" id="searchval" name="searchval">&nbsp;&nbsp;
				<input type="button" value="검색" onclick="boardSearch()">
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
				<tbody>
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