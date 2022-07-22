<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주간 HOT</title>
<script src="js/jquery-3.6.0.min.js"></script>

<style>
* {
    margin: 0;
    padding: 0;
}

.board_title {
font-size: 3.5em;
font-style :italic;
text-align: left;
color: gray;
padding-left: 3em;
padding-right:0.5em;
padding-top: 1em;
padding-bottom: 0.5em;
float: left;
}

.board_title a {
text-decoration:none;
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


tr >td >a {
 	text-decoration-line : none;
}
 
div > a {
	text-decoration-line : none;
}

#hotbtn {
color:#FFFFFF; 
background-color:#FE2E2E;
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

	
	    <!-- -------- HOT게시판 ------------->
	    <div class="board_title">
		<strong><a href="hotBoardList.do">Hot Board</a></strong>
		</div>
		
		
		<!-- -------- 공통 검색기능 ------------->
		<div class="board_list_wrap" align="right">
			<form id="frm">
				<select id="searchkey" name="searchkey">
				  	<option value="board_title">제목</option>
				  	<option value="board_subject">내용</option>
				  	<option value="board_writer">작성자</option>			  
				</select> &nbsp;
				<input type="text" id="searchval" name="searchval">&nbsp;&nbsp;
				<input type="button"  class="btn btn-success" value="검색" onclick="boardSearch()">
			</form>
		</div><br>
		<!-- -------- 공통 검색기능 ------------->
	
		

		<div class="board_list_wrap" align="right">


			<table class="board_list">
				<thead>
					<tr>
						<th width="130">카테고리</th>
						<th width="70">글번호</th>
						<th width="130">작성자</th>
						<th width="200">제목</th>
						<th width="130">작성일자</th>
						<th width="180">첨부파일</th>
						<th width="70">스크랩수</th>	
						<th width="70">추천수</th>
						<th width="70">조회수</th>					
					</tr>
				</thead>
				<tbody id="tb">
					<c:choose>
						<c:when test="${not empty hotboardlist }">
							<c:forEach items="${hotboardlist }" var="b">
								<tr>
									<td width="70px"><button id="hotbtn" disabled>HOT</button>&nbsp;&nbsp;${b.boardCategory }</td>
									<td>#${b.boardNo }</td>
									<td><a href="myPage.do?no=${b.memberNo}">${b.boardWriter }</a></td>
									<td><a href="boardView.do?board_no=${b.boardNo}">${b.boardTitle }</a></td>
									<td>${b.boardDate }</td>
									<td>${b.boardAttech }</td>
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
		</div><br>


	</div>
	
	
	
	<script>
		function clickBoardView() {
				var boardNo = $(this).children().eq().text();
				location.href="boardView.do?boardNo=" + boardNo;
		}
	</script>
	
	<script type="text/javascript">
		function noticeSearch() {
			let categorykey = $("#categorykey").val();
			let categoryval = $("#categoryval").val();
			let searchkey = $("#searchkey").val();
			let searchval = $("#searchval").val();
			$.ajax({
				url : "ajaxboardSearche.do",
				type : "post",
				data : {categorykey : categorykey, categoryval : categoryval, searchkey : searchkey, searchval : searchval},
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
							$("<td />").text(b.boardNo),
							$("<td />").text(b.boardWriter),
							$("<td />").text(b.boardTitle),
							$("<td />").text(b.boardDate),
							$("<td />").text(b.boardAttech),
							$("<td />").text(b.boardScrap),	
							$("<td />").text(b.boardRecommend),
							$("<td />").text(b.boardHit),
						);
				tbody.append(row);
			});
			$('table').append(tbody);
		}
	</script>
	
</body>
</html>