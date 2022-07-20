<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>
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





</style>
</head>



	<div class="board_list_wrap" align="left">
		<div>스터디 게시글 목록</div>
	
		<br>
	</div>
	<div class="board_list_wrap" align="center">
		<table class="board_list">
			<thead>
				<tr>
					<th width="70">번호</th>
					<th width="200">제목</th>
					<th width="100">스터디방식</th>
					<th width="100">스터디기간</th>
					<th width="100">스터디언어</th>
					<th width="90">작성일자</th>
					<th width="80">글작성자</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty list }">
						<c:forEach items="${list }" var="b">

							<tr>
								<td class="tit">${b.studyNo }</td>
								<td class="tit"><a href="StudyView.do?study_no=${b.studyNo}">${b.studyTitle }</a></td>
								<td class="tit">${b.studySystem }</td>
								<td class="tit">${b.studyPeriod }</td>
								<td class="tit">${b.studyLanguage }</td>
								<td class="tit">${b.studyDate }</td>
								<td class="tit"><a href="myPage.do?no=${b.memberNo}">${b.studyWriter }</a></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>

						<tr>
							<td colspan="8" align="center">게시글이 존재하지않습니다</td>
						</tr>
					</c:otherwise>
				</c:choose>

			</tbody>
		</table>

		<button type="button" onclick="location.href='StudyForm.do'">글등록</button>

		
	
		<div class="paging" align="center">
			<a href="#" class="bt">첫 페이지</a> <a href="#" class="bt">이전 페이지</a> <a
				href="#" class="num on">1</a> <a href="#" class="num">2</a> <a
				href="#" class="num">3</a> <a href="#" class="bt">다음 페이지</a> <a
				href="#" class="bt">마지막 페이지</a>
		</div>
	</div>
	<script type="text/javascript">
		function noticeSearch() {
			let key = $("#key").val();
			let val = $("#val").val();
			$.ajax({
				url : "ajaxNoticeSearche.do",
				type : "post",
				data : {
					key : key,
					val : val
				},
				dataType : "json",
				success : function(result) {
					console.log(result);
				},
				error : function() {

				}
			})
		}
	</script>
</body>
</html>