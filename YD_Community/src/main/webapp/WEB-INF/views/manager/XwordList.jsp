<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>금지어 관리</title>
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
      <strong>금 지 어 목 록</strong>
   </div>
	<div class="board_list_wrap" align="right">
	<form id="frm">
		<input type="text" id="search" name="search">&nbsp;
		<input type="button" class="btn btn-success" value="검색" onclick="XwordSearch()">
	</form>
	</div>
	<div class="board_list_wrap" align="center">
	<table class="board_list">
		<thead>
			<tr>
				<th><input type="checkbox"></th>
				<th>NO</th>
				<th>금지어</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty list }">
					<c:forEach items="${list }" var="x">
						<tr>
							<td><input type="checkbox"></td>
							<td id="xwordNo" name="xwordNo">${x.xwordNo }</td>
							<td id="xword" name="xword">${x.xword }</td>
							<td><input type="button" class="btn btn-success btn-sm" value="수정" onclick="XwordUpdate(${x.xwordNo })"></td>
							<td><input type="button" class="btn btn-outline-danger btn-sm" value="삭제" onclick="XwordDelete(this)"></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5" align="center">
							금지어가 존재하지 않습니다
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<button type="button"  class="btn btn-success btn-sm" style="background-color: #1abc9c"  onclick="XwordInsert()">추가</button>
	<input type="button" class="btn btn-outline-danger btn-sm" value="선택삭제" onclick="deleteValue()">
	</div>
	<script type="text/javascript">
		function XwordSearch() { // 검색
			let key = $("#search").val();
			console.log(key);
			$.ajax({
				url : "XwordSearch.do", 
				type : "post",
				data : {key : key},
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
		
		function XwordInsert(){
			window.open("XwordInsertForm.do","금지어 추가","width=400, height=300, top=10, left=10");
		}
		
		function jsonHtmlConvert(data) { //새로 불러오기
			$('tbody').remove();
			var tbody = $("<tbody />");
			$.each(data, function(index, item){
				var row = $("<tr />").append(
							$("<td />").append($("<input>").attr('type','checkbox')),
							$("<td />").text(item.xwordNo),
							$("<td />").text(item.xword),
							$("<td />").append($("<button class='btn btn-success btn-sm' onclick='XwordUpdate(this)' />").text("수정")),
							$("<td />").append($("<button onclick='XwordDelete(this)' />").text("삭제"))
						);
				tbody.append(row);
			});
			$('table').append(tbody);
		}
		
		function errorCallback(err){ //에러
			console.log('error : '+err.message);
		}
		
/* 	 	function XwordUpdate(obj) { //금지어 수정
			let key = obj;
	 		window.open("XwordUpdate.do");
			console.log(key);
			$.ajax({
				url : "XwordUpdate.do",
				type : "post",
				data : {key : key},
				dataType : "json",
				success : function(result){
					if(result = 1){
						console.log(result);
						window.open("XwordUpdateFrom.do");
					}else{
						alert("수정할 수 없습니다.");
					}
			},
			error : function(error){
				alert("ERROR!")
			}
		});
	}  */
	 	
	 	function XwordUpdate(obj){
			let key = obj;
			$.ajax({
				url : "XwordSelectOne.do",
				type : "post",
				data : {key : key},
				dataType : "json",
				success : function(result){
					if(result = 1){
						console.log("단건조회 결과 : ")
						console.log(result);
						window.open("XwordUpdateForm.do");
					}else{
						alert("수정할 수 없습니다.");
					}
			},
			error : function(error){
				alert("ERROR!")
			}
		});
		}
		
		function XwordDelete(obj){ //금지어 삭제
			let row = $(obj).parent().parent().get(0);
			let td = row.cells[1];
			let no = $(td).html();
			
			const xhr = new XMLHttpRequest();
			const url = "XwordDelete.do?xwordNo="+no;
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