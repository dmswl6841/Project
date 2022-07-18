<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
<script src="js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div align="center">
		<div>회원 목록</div>
	</div>
	<div align="center">
	<form id="frm">
		<select id="key" name="key">
			<option value="member_id">아이디</option>
			<option value="member_warning">경고횟수</option>
			<option value="member_join">회원권한</option>
		</select>&nbsp;
		<input type="text" id="val" name="val">&nbsp;
		<input type="button" value="검색" onclick="memberSearch()">
	</form>
	</div>
	<form id=frm2>
	<div align="center">
	<table border="1">
		<thead>
			<tr>
				<th><input id="allCheck" name="allCheck" type="checkbox"></th>
				<th>NO</th>
				<th>사용자 ID</th>
				<th>사용자 명</th>
				<th>경고 횟수</th>
				<th>회원권한</th>
				<th>가입여부</th>
				<th>탈퇴</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty list }">
					<c:forEach items="${list }" var="m">
						<tr>
							<td><input id="check" name="check"type="checkbox"></td>
							<div id="memberNo" name="memberNo"><td>${m.memberNo }</td></div>
							<div id="memberId" name="memberId"><td>${m.memberId }</td></div>
							<div id="memberNick" name="memberNick"><td>${m.memberNick }</td></div>
							<div id="memberWarning" name="memberWarning"><td>${m.memberWarning }</td></div>
							<div id="memberAuthor" name="memberAuthor"><td>${m.memberAuthor }</td></div>
							<td><button type="button" onclick="memberJoin()">승인</button></td>
							<td align="center">
										<button type="button" onclick="memberDelete(this)">삭제</button>
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
	<input type="button" value="선택삭제" onclick="deleteValue()">
	</div>
	</form>
	<script type="text/javascript">
		   function deleteValue(){
			   var memberId = "";
			   var memberChk = document.getElementsByName("RowCheck");
			   var chked = false;
			   var indexid = false;
			   for(i=0; i < memberChk.length; i++){
			    if(memberChk[i].checked){
			     if(indexid){
			       userid = userid + '-';
			     }
			     userid = userid + memberChk[i].value;
			     indexid = true;
			    }
			   }
			   if(!indexid){
			    alert("삭제할 사용자를 체크해 주세요");
			    return;
			   }
			   document.userForm.delUserid.value = userid;       // 체크된 사용자 아이디를 '-'로 묶은 userid 를 document.userForm.delUserid 의 value로 저장
			   
			   var agree=confirm("삭제 하시겠습니까?");
			      if (agree){
			    document.userForm.execute.value = "userDel";
			      document.userForm.submit();
			      }
			   }﻿
		
		function memberSearch() {
			let key = $("#key").val();
			let val = $("#val").val();
			$.ajax({
				url : "memberSearchList.do",
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
				error : function() {
				}
			})
		}
		
		function jsonHtmlConvert(data) {
			$('tbody').remove();
			var tbody = $("<tbody />");
			$.each(data, function(index, item){
				var row = $("<tr />").append(
							$("<td />").text(item.memberNo),
							$("<td />").text(item.memberId),
							$("<td />").text(item.memberNick),
							$("<td />").text(item.memberWarning),
							$("<td />").text(item.memberAuthor),
							$("<td />").append($("<botton onclick=memberJoin(${item.Author})/>").text("승인")),
							$("<td />").append($("<botton onclick=memberDelete(${item.memberNo})/>").text("탈퇴"))
						);
				tbody.append(row);
			});
			$('table').append(tbody);
		}
		
		function memberJoin() {
			let author = document.getElementById(memberAuthor);
			if(author == "guest"){
				alert("가입 승인되었습니다.");
				author ="USER";
				
			}else{
				alert("이미 가입된 회원입니다.");
			}
			return true;
		}
		
		function errorCallback(err){
			console.log('error : '+err.message);
		}
		function memberDelete(obj){
			let mid = document.getElementById('memberId');
			console.log(mid);
			let row = $(obj).parent().parent().get(0);
			let td = row.cells[0];
			let id = $(td).html();
			
			const xhr = new XMLHttpRequest();
			const url = "memberDelete.do?memberId="+mid;
			xhr.onload = function(){
				if(xhr.status >=200 && xhr.status <300){
					if(xhr.response ==1){
						$(row).remove();
					}else{
						alert ("삭제할 수 없습니다.")
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