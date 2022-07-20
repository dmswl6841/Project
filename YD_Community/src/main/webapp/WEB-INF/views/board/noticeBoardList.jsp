<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
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
    text-align: center;
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

<body>
	<div align="center">
	
	    <!-- -------- 공지게시판 ------------->
		<div class="board_list_wrap" align="left">
		<h1>공지사항</h1></div><br>
		
		
		<!-- 공통 검색기능 -->
		<div>
			<form id="frm">
				<select id="key" name="key">
					<option value="board_title">제목</option>
					<option value="board_subject">내용</option>
					<option value="board_writer">작성자</option>
				</select> &nbsp; 
				<input type="text" id="val" name="val">&nbsp;&nbsp;
				<input type="button" value="검색" onclick=boardSearch()>
			</form>
		</div>
		<br>
		<!-- 공통 검색기능 -->
	
		
		<div class="board_list_wrap" align="right">
			<table class="board_list">
				<thead>
					<tr>
						<th width="3">No.</th>
						<th width="3">작성자</th>
						<th width="3">제목</th>
						<th width="3">작성일자</th>
						<th width="3">스크랩수</th>	
						<th width="3">추천수</th>
						<th width="3">조회수</th>					
					</tr>
				</thead>
				<tbody id="tb">
					<c:choose>
						<c:when test="${not empty noticeboardlist }">
							<c:forEach items="${noticeboardlist }" var="b">
								<tr>
									<td>${b.boardNo }</td>
									<td>${b.boardWriter }</td>
									<td><a href="boardView.do?board_no=${b.boardNo}">${b.boardTitle }</a></td>
									<td>${b.boardDate }</td>
									<td>${b.boardScrap }</td>
									<td>${b.boardRecommend }</td>
									<td>${b.boardHit }</td>
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
			
			<!-- 관리자 권한으로 글쓰기 -->
			<div>
				<c:if test="${author == 'ADMIN'}">
					<button type="button" onclick="location.href='boardForm.do'">글등록</button>
				</c:if>
			</div>
		</div><br>

	</div>



	
	<script type="text/javascript">
		function boardSearch() {
			let key = $("#key").val();
			let val = $("#val").val();
			$.ajax({
				url : "ajaxBoardSearche.do",
				type : "post",
				data : {key : key, val : val},
				dataType : "Json",
				success : function(result){
					if(result.length > 0) {
						jsonHtmlConvert(result);
					} else {
						alert("검색한 결과가 없습니다.");
					} console.log(result);
				},
				error : function() {	
				}
			})
		}
		
		function jsonHtmlConvert(data) {
			$("#tb").remove();
			var tbody = $("<tbody id />");
			$.each(data, function(index, item){
				var row = $("<tr />").append(
							$("<td />").text(item.boardNo),
							$("<td />").text(item.boardWriter),
							$("<td />").text(item.boardTitle),
							$("<td />").text(item.boardDate),
							$("<td />").text(item.boardAttech),
							$("<td />").text(item.boardScrap),	
							$("<td />").text(item.boardRecommend),
							$("<td />").text(item.boardHit),
						);
				tbody.append(row);
			});
			$('table').append(tbody);
		}
	</script>

</body>
</html>