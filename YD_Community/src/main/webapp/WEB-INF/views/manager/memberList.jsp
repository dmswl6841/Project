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
	<div align="center">
	<table border="1">
		<thead>
			<tr>
				<th><input type="checkbox"></th>
				<th>NO</th>
				<th>사용자 ID</th>
				<th>사용자 명</th>
				<th>경고 횟수</th>
				<th>회원권한</th>
				<th>탈퇴</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty list }">
					<c:forEach items="${list }" var="m">
						<tr>
							<td><input type="checkbox"></td>
							<td>${m.memberNo }</td>
							<td>${m.memberId }</td>
							<td>${m.memberNick }</td>
							<td>${m.memberWarning }</td>
							<td>${m.memberAuthor }</td>
							<td><input type="button" value="탈퇴" onclick="memberDelete()"></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="7" align="center">
							회원이 존재하지 않습니다
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	</div>
	<script type="text/javascript">
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
							$("<td />").append($("<botton onclick=memberDelete(${item.memberId})/>").text("탈퇴"))
						);
				tbody.append(row);
			});
			$('table').append(tbody);
		}
		
		function memberJoin(){
			var memberState = memberAuthor;
			if(memberState == "GEUST"){
				memberState = "USER";
				alert("가입 승인되었습니다.");
			}else{
				alert("이미 가입된 회원입니다.");
			}
			return memberState;
		}
	</script>
</body>
</html>