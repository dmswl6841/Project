<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 관리</title>
<script src="js/jquery-3.6.0.min.js"></script>

<style>
* {
    margin: 0;
    padding: 0;
}

table {
    border-collapse: collapse;
}

caption {
    display: none;
}

a {
    text-decoration: none;
    color: inherit;
}

.board_list_wrap {
    padding: 50px;
}

.board_list {
    width: 100%;
    border-top: 2px solid green;
    text-align: center;
}

.board_list tr {
    border-bottom: 1px solid #ccc;
}

.board_list th,
.board_list td {
    padding: 10px;
    font-size: 14px;
}

.board_list td {
    text-align: center;
}

.board_list .tit {
    text-align: left;
}

.board_list .tit:hover {
    text-decoration: underline;
}

.board_list_wrap .paging {
    margin-top: 20px;
    text-align: center;
    font-size: 0;
}
.board_list_wrap .paging a {
    display: inline-block;
    margin-left: 10px;
    padding: 5px 10px;
    border-radius: 100px;
    font-size: 12px;
}
.board_list_wrap .paging a:first-child {
    margin-left: 0;
}

.board_list_wrap .paging a.bt {
    border: 1px solid #eee;
    background: #eee;
}

.board_list_wrap .paging a.num {
    border: 1px solid green;
    font-weight: 600;
    color: green;
}

.board_list_wrap .paging a.num.on {
    background: green;
    color: #fff;
}

.board_title {
    margin-bottom: 30px;
}
.board_title strong {
    font-size: 3rem;
}
.board_title p {
    margin-top: 5px;
    font-size: 1.4rem;
}



</style>
</head>




<body>
	<div class="board_title" align="center">
      <strong>게 시 판 목 록</strong>
   </div>
	
	 <!-- 공통 검색기능 -->
	<div class="board_list_wrap" align="right">
	<form id="frm">
		<select id="key" name="key">
			<option value="board_category">게시판</option>
			<option value="board_title">제목</option>
			<option value="board_writer">작성자</option>
		</select>&nbsp;
		<input type="text" id="val" name="val">&nbsp;
		<input type="button" class="btn btn-success" value="검색" onclick="boardSearch()">
	</form>
	</div>
	 <!-- 공통 검색기능 -->
	 
	 
	<div class="board_list_wrap" align="center">
	<table class="board_list">
		<thead>
			<tr>
				<th width="50"><input type="checkbox"></th>
				<th width="70">게시판</th>
				<th width="50">게시글 제목</th>
				<th width="50">작성자</th>
				<th width="50">작성일자</th>
				<th width="50">글 삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty list }">
					<c:forEach items="${list }" var="b">
						<tr>
							<td><input id="check" name="check" type="checkbox"></td>
							<td id="boardNo" name="boardNo">${b.boardNo }</td>
							<td id="boardCategory" name="boardCategory">${b.boardCategory }</td>
							<td id="boardTitle" name="boardTitle">${b.boardTitle }</td>
							<td id="boardWriter" name="boardWriter">${b.boardWriter }</td>
							<td id="boardDate">${b.boardDate }</td>
							<td><input type="button" class="btn btn-outline-danger btn-sm" value="삭제" onclick="boardDelete(${b.boardNo })"></td>
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
	<input type="button" class="btn btn-outline-danger btn-sm" value="선택삭제" onclick="deleteValue()">
	</div>
	<script type="text/javascript">
	
		function boardSearch() { //게시글 검색
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
							$("<td />").append($("<button onclick='boardDelete(this)' />").text("삭제"))
						);
				tbody.append(row);
			});
			$('table').append(tbody);
		}
		
		function errorCallback(err){ //에러
			console.log('error : '+err.message);
		}
		
		function boardDelete(id){ //게시판 삭제
			let key = id;
			$.ajax({
				url : "managerBoardDelete.do",
				type : "post",
				data : {key : key},
				dataType : "json",
				success : function(result){
					if(result ==1){
						alert("삭제되었습니다.");
						$(row).remove();
						location.reload();
					}else{
						alert("삭제할 수 없습니다.");
					}
				},
				error : function(error){
					alert("ERROR!")
				}
			});
		}
	</script>
</body>
</html>