<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
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
      <strong>회 원 목 록</strong>
   </div>
   
	<div class="board_list_wrap" align="right">
		<form id="frm">
			<select id="key" name="key">
				<option value="member_id">아이디</option>
				<option value="member_nick">닉네임</option>
				<option value="member_warning">경고횟수</option>
				<option value="member_join">회원권한</option>
			</select>&nbsp;
			<input type="text" id="val" name="val">&nbsp;&nbsp;
			<input type="button" class="btn btn-success" value="검색" onclick="memberSearch()">
		</form>
		
		<br>
	
	</div>
	<form id=frm2>
		<div class="board_list_wrap" align="center">
		<table class="board_list">
			<thead>
				<div>
					<tr>
						<th width="7">NO</th>
						<th width="7">사용자 ID</th>
						<th width="7">사용자 명</th>
						<th width="7">경고 횟수</th>
						<th width="7">회원권한</th>
						<th width="7">가입여부</th>
						<th width="7">탈퇴</th>
					</tr>
				</div>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty list }">
						<c:forEach items="${list }" var="m">
							<tr>
								<td id="memberNo" name="memberNo">${m.memberNo }</td>
								<td id="memberId" name="memberId">${m.memberId }</td>
								<td id="memberNick" name="memberNick">${m.memberNick }</td>
								<td id="memberWarning" name="memberWarning">${m.memberWarning }</td>
								<td id="memberAuthor" name="memberAuthor">${m.memberAuthor }</td>
								<td><button type="button" class="btn btn-success btn-sm" style="background-color: #1abc9c;"
								
								 onclick="memberUpdate(this)">승인</button></td>
								<td align="center">
									<button type="button" class="btn btn-outline-danger btn-sm"  onclick="memberDelete(${m.memberNo })">삭제</button>
								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="8" align="center">
								회원이 존재하지 않습니다
							</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		</div>
	</form>
	<script type="text/javascript">
﻿
		function memberSearch() { //검색 기능
			let key = $("#key").val();
			let val = $("#val").val();
			$.ajax({
				url : "managermemberSearch.do",
				type : "post",
				data : {key : key, val : val},
				dataType : "json",
				success : function(result){
					console.log(result);
					if(result.length > 0) {
						jsonHtmlConvert(result);
					}else {
						alert("검색한 결과가 없습니다.");
					}
				},
				error : function(error){
					alert("ERROR!")
				}
			})
		}
		
		function jsonHtmlConvert(data) { //새로 불러오기
			$('tbody').remove();
			var tbody = $("<tbody />");
			$.each(data, function(index, item){
				var row = $("<tr />").append(
							$("<td />").text(item.memberNo),
							$("<td />").text(item.memberId),
							$("<td />").append($("<a href='myPage.do?no=${b.memberNo}'>").text(item.memberNick)),
							$("<td />").text(item.memberWarning),
							$("<td />").text(item.memberAuthor),
							$("<td />").append($("<button class='tn btn-success btn-sm' style='background-color: #1abc9c' onclick='memberUpdate(this)' />").text("승인")),
							$("<td />").append($("<button class='btn btn-outline-danger btn-sm' onclick='memberDelete(this)' />").text("삭제"))
						);
				tbody.append(row);
			});
			$('table').append(tbody);
		}
		
		function errorCallback(err){ //에러
			console.log('error : '+err.message);
		}
		
		function memberUpdate(obj) { //가입 승인
			let key = obj.parentElement.parentElement.children[5].textContent;
	   		console.log(key);
			let row = $(obj).parent().parent().get(0);
			let td = row.cells[1];
			let val = $(td).html();
			console.log(val);
			$.ajax({
				url : "memberUpdateAuthor.do",
				type : "post",
				data : {key : key, val : val},
				dataType : "json",
				success : function(result){
					console.log(result);
					if(result == "1") {
						alert("가입 승인되었습니다.");
						location.reload();
					}else if(result == "2") {
						alert("관리자는 바꿀 수 없습니다!");
						location.reload();
					}else {
						alert("이미 승인된 회원입니다.");
						location.reload();
					}
				},
				error : function(error){
					alert("ERROR!")
				}
			});
		}
		
		/* function memberUpdate(obj) { //가입 승인 -ing
			let row = $(obj).parent().parent().get(0);
			let td = row.cells[1];
			let no = $(td).html();
			console.log(author);
			
			const xhr = new XMLHttpRequest();
			const url = "memberUpdateAuthor.do?memberNo="+no;
			console.log(url)
			xhr.onload = function(){
				if(xhr.status >=200 && xhr.status <300){
					if(xhr.response ==0){
						if(memberAuthor =="GUEST"){
							alert("가입 승인되었습니다.");
							jsonHtmlConvert(result);
						}else if(memberAuthor=="ADMIN"){
							alert("관리자는 바꿀 수 없습니다!")
						}else{
							alert ("이미 승인된 회원입니다.");
						}
					}else{
						alert ("이미 승인된 회원입니다.");
					}
				}else {
					errorCallback(new Error(xhr.stautsText));
				}
			};
			xhr.open('GET',url);
			xhr.send();
		} */
		
		function memberDelete(id){ //회원 삭제
			const xhr = new XMLHttpRequest();
			const url = "managerMemberDelete.do?memberNo="+id;
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
			xhr.send(); 
		}
	</script>
</body>
</html>