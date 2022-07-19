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
</head>
<style>
* {
  box-sizing: border-box;
}

body {
  font-family: Arial, Helvetica, sans-serif;
}

/* Style the header */
header {
  background-color: #666;
  padding: 30px; 
  text-align: center;
  font-size: 35px;
  color: white;
}

/* Create two columns/boxes that floats next to each other */
nav {
  float: left;
  width: 30%;
  height: 300px; /* only for demonstration, should be removed */
  background: #ccc;
  padding: 20px;
}

/* Style the list inside the menu */
nav ul {
  list-style-type: none;
  padding: 0;
}

article {
  float: left;
  padding: 20px;
  width: 70%;
  background-color: #f1f1f1;
  height: 300px; /* only for demonstration, should be removed */
}

/* Clear floats after the columns */
section::after {
  content: "";
  display: table;
  clear: both;
}

/* Style the footer */
footer {
  background-color: #777;
  padding: 10px;
  text-align: center;
  color: white;
}

/* Responsive layout - makes the two columns/boxes stack on top of each other instead of next to each other, on small screens */
@media (max-width: 600px) {
  nav, article {
    width: 100%;
    height: auto;
  }
}
</style>
<body>

<header>
<h2>YEDAM</h2>
</header>


	<div class="board_list_wrap" align="center">
		<div>게시글 목록</div>
		<div>
			<form id="frm">
				<select id="key" name="key">
					<option value="studytitle">제목</option>
					<option value="studysubject">내용</option>
					<option value="studywriter">작성자</option>
				</select> &nbsp; <input type="text" id="val" name="val">&nbsp;&nbsp;
				<input type="button" value="검색" onclick="StudySearch()">

			</form>
		</div>
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
								<td class="tit">${b.studyWriter }</td>
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