<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA게시판</title>
<script src="js/jquery-3.6.0.min.js"></script>
</head>

<body>
	<div align="center">
	
	    <!-- QnA게시판 -->
		<div><h1>QnA게시판</h1></div><br>
		
		
		<!-- 공통 검색기능 -->
		
		<div>
			<form id="frm" action="qnaBoardList.do">
				<select id="searchType" name="searchType">
					<option value="board_title">제목</option>
					<option value="board_subject">내용</option>
					<option value="board_writer">작성자</option>
				</select> &nbsp; 
				<input type="text" id="searchKeyword" name="searchKeyword" value="">&nbsp;&nbsp;
				<button type="submit" id="searchBtn">검색</button>
			</form>
		</div>
		<br>
		
		
		
		<!-- 공통 검색기능 -->
	
		
		<div>
			<table border="1">
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
						<c:when test="${not empty qnaBoardList }">
							<c:forEach items="${qnaBoardList }" var="b">
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
			<button type="button" onclick="location.href='boardForm.do'">글쓰기</button>
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