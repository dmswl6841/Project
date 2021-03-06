<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">


  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA게시판</title>
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


strong {
	font-size: 60px;
}



</style>

</head>

<body>
	<div class="board_list_wrap" align="center">
	
	    <!-- QnA게시판 -->
	    <div class="board_title">
		<strong><a href="qnaBoardList.do">QnA Board</a></strong></div><br>
		
		
		<!-- 공통 검색기능 -->
		
		<div>
			<form id="frm" action="qnaBoardList.do">
				<select id="searchType" name="searchType">
					<option value="board_title">제목</option>
					<option value="board_subject">내용</option>
					<option value="board_writer">작성자</option>
				</select> &nbsp; 
				<input type="text" id="searchKeyword" name="searchKeyword" value="">&nbsp;&nbsp;
				<button type="submit" class="btn btn-success" id="searchBtn">검색</button>
			</form>
		</div>
		<br>
		
		
		
		<!-- 공통 검색기능 -->
	

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
						<c:when test="${not empty qnaboardlist }">
							<c:forEach items="${qnaboardlist }" var="b">
								<tr>
									<td>${b.boardCategory }</td>
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
		
		<!-- Pagination -->
					<div class="page">
						<c:if test="${page.prev}"> 
							<a href="${page.startPage - 1}" class="bt">이전 페이지</a>
						</c:if>
						<c:forEach var="num" begin="${page.startPage }" end="${page.endPage }">
							<!-- <a href="freeBoardList.do?pageNum=${num}" class="pagination-num">${num}</a> -->
							<a href="${num}">${num}</a>
						</c:forEach>
						<c:if test="${page.next}">
							<a href="${page.endPage + 1}" class="bt">다음 페이지</a>
						</c:if>
					</div>
					<form id='actionForm' action="qnaBoardList.do" method="get"> 
						<input type="hidden" id="pageNum" name="pageNum" value="${page.cri.pageNum}"> 
						<input type="hidden" name="searchType" value="${page.cri.searchType}"> 
						<input type="hidden" name="searchKeyword" value="${page.cri.keyword}"> 
					</form>

		<!-- 글쓰기 버튼 -->
		<div>
			<button type="button" class="btn btn-success" onclick="location.href='boardForm.do'">글쓰기</button>
		</div>
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
		
		let actionForm = $('#actionForm'); 
		// let _actionForm = document.getElementById('actionForm');
		$('.page a').on('click', function(e) { 
			e.preventDefault(); 
			//걸어둔 링크로 이동하는 것을 일단 막음 
			console.log($(this).attr('href'));
			actionForm.find('input[name="pageNum"]').val($(this).attr('href')); 
			// actionForm.find('#pageNum').val($(this).attr('href')); 
			// let paginationNum = document.getElementById('num');
			// let pageNum = document.getElementById('pageNum');

			// pageNum.value = paginationNum.value;
			
			actionForm.submit(); 
		});
		
	</script>

	
</body>
</html>