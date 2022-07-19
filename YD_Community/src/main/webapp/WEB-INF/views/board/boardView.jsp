<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
<script src="js/jquery-3.6.0.min.js"></script>
</head>

<body>
	<div align="center">
		<div>
			<h1>게시글 보기</h1>
		</div>


		<div>
			<form name="writeFrm">
			<input type="hidden" name="board_no" value="${board_no}">
				<table border="1">
					<tbody>
						<c:choose>
							<c:when test="${not empty boardviewlist}">
								<c:forEach items="${boardviewlist }" var="b">
									<tr>
										<td>${b.boardWriter }</td>
										<td>${b.boardScrap }</td>
										<td>${b.boardRecommend }</td>
										<td>${b.boardHit }</td>
									</tr>
									<tr>
										<td>${b.boardNo }</td>
										<td>${b.boardCategory }</td>
									</tr>
									<tr>
										<td colspan="3">${b.boardTitle }</td>
										<td>${b.boardDate }</td>
										<td>${b.boardAttech }</td>
									</tr>
									<tr>
										<td colspan="4">${b.boardSubject }</td>
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


				<div align="center">
					<button type="button">수정</button>
					<button type="button" onclick="deletePost()">삭제</button>
				</div>

			</form>
		</div>
		<br>
	</div>





	<script type="text/javascript">
		function deletePost() {
			var result = confirm("게시글을 삭제하시겠습니까?");
			if (result) {
				var form = document.writeFrm;
				from.method = "post";
				form.action = "boardDelete.do";
				form.submit();
			}
			//location.href = "boardDelete.do?board_no=${b.boardNo }";
		}
	</script>



</body>
</html>