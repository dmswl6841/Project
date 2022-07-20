<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 관리</title>
</head>
<body>
	<div align="center">
		<div>게시판 목록</div>
	</div>
	<div>
	<form id="frm">
		<select id="key" name="key">
			<option value="board_category">게시판</option>
			<option value="board_title">제목</option>
			<option value="board_writer">작성자</option>
		</select>&nbsp;
		<input type="text" id="val" name="val">&nbsp;
		<input type="button" value="검색" onclick="boardSearch()">
	</form>
	</div>
	<div align="center">
	<table border="1">
		<thead>
			<tr>
				<th><input type="checkbox"></th>
				<th>게시판</th>
				<th>게시글 제목</th>
				<th>작성자</th>
				<th>작성일자</th>
				<th>글 삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty list }">
					<c:forEach items="${list }" var="b">
						<tr>
							<td><input id="check" name="check" type="checkbox"></td>
							<td id="boardCategory" name="boardCategory">${b.boardCategory }</td>
							<td id="boardTitle" name="boardTitle">${b.boardTitle }</td>
							<td id="boardWriter" name="boardWriter">${b.boardWriter }</td>
							<td id="boardDate">${b.boardDate }</td>
							<td><input type="button" value="삭제" onclick="boardDelete(this)"></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6" align="center">
							금지어가 존재하지 않습니다
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<input type="button" value="선택삭제" onclick="deleteValue()">
	</div>
	<script type="text/javascript">
		function boardSearch() { //게시판 검색
			let key = $("#key").val();
			let val = $("#val").val();
			$.ajax({
				url : "managerBoardSearch.do",
				type : "post",
				data : {key : key, val : val},
				dataType : "json",
				success : function(result){
					if(result.length > 0) {
						jsonHtmlConvert(result);
					}else {
						alert("검색한 결과가 없습니다.");
					}
				},
				error : function(error){
					alert("ERROR!")
				}
			});
		}
		
		function jsonHtmlConvert(data) { //새로 불러오기
			$('tbody').remove();
			var tbody = $("<tbody />");
			$.each(data, function(index, item){
				var row = $("<tr />").append(
							$("<td />").append($("<input>").attr('type','checkbox')),
							$("<td />").text(item.boardCategory),
							$("<td />").text(item.boardTitle),
							$("<td />").text(item.boardWriter),
							$("<td />").text(item.boardDate),
							$("<td />").append($("<button onclick=boardDelete(this) />").text("삭제"))
						);
				tbody.append(row);
			});
			$('table').append(tbody);
		}
		
		function errorCallback(err){ //에러
			console.log('error : '+err.message);
		}
		
		function boardDelete(id){ //게시판 삭제
			const xhr = new XMLHttpRequest();
			const url = "managerBoardDelete.d?boardNo="+id;
			console.log(url)
			xhr.onload = function(){
				if(xhr.status >=200 && xhr.status <300){
					if(xhr.response ==1){
						alert("삭제되었습니다.");
						$(row).remove();
					}else{
						alert("삭제할 수 없습니다.");
					}
					
				}else {
					errorCallback(new Error(xhr.stautsText));
				}
			};
			xhr.open('GET',url);
			console.log(xhr.open('GET',url));
			xhr.send(); 
		}
	</script>
</body>
</html>