<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>금지어 관리</title>
</head>
<body>
	<div align="center">
		<div>금지어 목록</div>
	</div>
	<div>
	<form id="frm">
		<input type="text" id="xword" name="xword">&nbsp;
		<input type="button" value="검색" onclick="XwordSearch()">
	</form>
	</div>
	<div align="center">
	<table border="1">
		<thead>
			<tr>
				<th><input type="checkbox"></th>
				<th>NO</th>
				<th>금지어</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty list }">
					<c:forEach items="${list }" var="x">
						<tr>
							<td><input type="checkbox"></td>
							<td>${x.xwordNo }</td>
							<td>${x.xword }</td>
							<td><input type="button" value="삭제" onclick="location.href='XwordDelete.do'"></td>
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
	</div>
	<script type="text/javascript">
		function XwordSearch() {
			let xword = $("#xword").val();
			$.ajax({
				url : "",
				type : "post",
				data : {},
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
			})
		}
		function jsonHtmlConvert(data) {
			$('tbody').remove();
			var tbody = $("<tbody />");
			$.each(data, function(index, item){
				var row = $("<tr />").append(
						$("<td />").text(item.<input type="checkbox">),
							$("<td />").text(item.xwordNo),
							$("<td />").text(item.xword)
						);
				tbody.append(row);
			});
			$('table').append(tbody);
		}
	</script>
</body>
</html>