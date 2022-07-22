<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
<script src="js/jquery-3.6.0.min.js"></script>

<style>
* {
	margin: 0;
	padding: 0;
}

html {
	font-size: 10px;
}

ul, li {
	list-style: none;
}

a {
	text-decoration: none;
	color: inherit;
}

.board_wrap {
	width: 1000px;
	margin: 100px auto;
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

.bt_wrap a {
	display: inline-block;
	min-width: 80px;
	margin-left: 10px;
	padding: 10px;
	border: 1px solid #000;
	border-radius: 2px;
	font-size: 1.4rem;
}

.bt_wrap a:first-child {
	margin-left: 0;
}

.bt_wrap a.on {
	background: #000;
	color: #fff;
}

.board_list {
	width: 100%;
	border-top: 2px solid #000;
}

.board_list>div {
	border-bottom: 1px solid #ddd;
	font-size: 0;
}

.board_list>div.top {
	border-bottom: 1px solid #999;
}

.board_list>div:last-child {
	border-bottom: 1px solid #000;
}

.board_list>div>div {
	display: inline-block;
	padding: 15px 0;
	text-align: center;
	font-size: 1.4rem;
}

.board_list>div.top>div {
	font-weight: 600;
}

.board_list .num {
	width: 10%;
}

.board_list .title {
	width: 60%;
	text-align: left;
}

.board_list .top .title {
	text-align: center;
}

.board_list .writer {
	width: 10%;
}

.board_list .date {
	width: 10%;
}

.board_list .count {
	width: 10%;
}

.board_page {
	margin-top: 30px;
	text-align: center;
	font-size: 0;
}

.board_page a {
	display: inline-block;
	width: 32px;
	height: 32px;
	box-sizing: border-box;
	vertical-align: middle;
	border: 1px solid #ddd;
	border-left: 0;
	line-height: 100%;
}

.board_page a.bt {
	padding-top: 10px;
	font-size: 1.2rem;
	letter-spacing: -1px;
}

.board_page a.num {
	padding-top: 9px;
	font-size: 1.4rem;
}

.board_page a.num.on {
	border-color: #000;
	background: #000;
	color: #fff;
}

.board_page a:first-child {
	border-left: 1px solid #ddd;
}

.board_view {
	width: 100%;
	border-top: 2px solid #000;
}

.board_view .title {
	padding: 20px 15px;
	border-bottom: 1px dashed #ddd;
	font-size: 2rem;
}

.board_view .info {
	padding: 15px;
	border-bottom: 1px solid #999;
	font-size: 0;
}

.board_view .info dl {
	position: relative;
	display: inline-block;
	padding: 0 20px;
}

.board_view .info dl:first-child {
	padding-left: 0;
}

.board_view .info dl::before {
	content: "";
	position: absolute;
	top: 1px;
	left: 0;
	display: block;
	width: 1px;
	height: 13px;
	background: #ddd;
}

.board_view .info dl:first-child::before {
	display: none;
}

.board_view .info dl dt, .board_view .info dl dd {
	display: inline-block;
	font-size: 1.4rem;
}

.board_view .info dl dt {
	
}

.board_view .info dl dd {
	margin-left: 10px;
	color: #777;
}

.board_view .cont {
	padding: 15px;
	border-bottom: 1px solid #000;
	line-height: 160%;
	font-size: 1.4rem;
}

.board_write {
	border-top: 2px solid #000;
}

.board_write .title, .board_write .info {
	padding: 15px;
}

.board_write .info {
	border-top: 1px dashed #ddd;
	border-bottom: 1px solid #000;
	font-size: 0;
}

.board_write .title dl {
	font-size: 0;
}

.board_write .info dl {
	display: inline-block;
	width: 50%;
	vertical-align: middle;
}

.board_write .title dt, .board_write .title dd, .board_write .info dt,
	.board_write .info dd {
	display: inline-block;
	vertical-align: middle;
	font-size: 1.4rem;
}

.board_write .title dt, .board_write .info dt {
	width: 100px;
}

.board_write .title dd {
	width: calc(100% - 100px);
}

.board_write .title input[type="text"], .board_write .info input[type="text"],
	.board_write .info input[type="password"] {
	padding: 10px;
	box-sizing: border-box;
}

.board_write .title input[type="text"] {
	width: 80%;
}

.board_write .cont {
	border-bottom: 1px solid #000;
}

.board_write .cont textarea {
	display: block;
	width: 100%;
	height: 300px;
	padding: 15px;
	box-sizing: border-box;
	border: 0;
	resize: vertical;
}
</style>

</head>

<body>
	<div class="board_wrap">
		<div class="board_title">
			<strong>게시글 보기</strong>
		</div>

		<div class="board_view_wrap">
			<div class="board_view">
				<div class="title"></div>
				<c:choose>
					<c:when test="${not empty vo}">

						<form name="writeFrm">
							<input type="hidden" name="board_no" value="${vo.boardNo}">
							<input type="hidden" name="member_no" value="${member.memberNo }">


							<div class="info" id="studyDel">


								<dl>
									<dt>작성자</dt>
									<dd>
										<a href="myPage.do?no=${vo.memberNo}">${vo.boardWriter }</a>
									</dd>
								</dl>
								<dl>
									<dt>👀</dt>
									<dd> ${vo.boardHit }</dd>
								</dl>
								<dl>
									<dt>#</dt>
									<dd> ${vo.boardNo }</dd>
								</dl>
								<dl>
									<dt>카테고리</dt>
									<c:if test="${vo.boardCategory =='자유' }">
										<dd>🎡 ${vo.boardCategory }</dd>
									</c:if>
									<c:if test="${vo.boardCategory =='QnA' }">
										<dd>💡 ${vo.boardCategory }</dd>
									</c:if>
									<c:if test="${vo.boardCategory =='공지' }">
										<dd>📢 ${vo.boardCategory }</dd>
									</c:if>
									<c:if test="${vo.boardCategory =='HOT' }">
										<dd>🏆 ${vo.boardCategory }	</dd>
									</c:if>
								</dl>
								<dl>
									<dt>제목</dt>
									<dd>${vo.boardTitle }</dd>
								</dl>
								<dl>
									<dt>작성일자</dt>
									<dd>${vo.boardDate }</dd>
								</dl>
								<dl>
									<dt>첨부파일</dt>
									<dd>${vo.boardAttech }</dd>
								</dl>
								<br>
							</div>
							<div class="cont">${vo.boardSubject }</div>
					</form>
				
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="8" align="center">게시글이 존재하지 않습니다</td>
						</tr>
					</c:otherwise>
				</c:choose>

			</div>

			<br>


				<div align="center">
					<button type="button" class="btn btn-success btn-lg" onclick="recommendPost()"
						title="이 글을 추천하기">👍 ${vo.boardRecommend }</button>
					<button type="button" class="btn btn-success btn-lg" onclick="scrapPost()"
						title="이 글을 스크랩하기">📌 ${vo.boardScrap }</button>
				</div>

				<div align="center">
					<button type="button" class="btn btn-success" onclick="updatePost()">수정</button>
					<button type="button" class="btn btn-success" onclick="deletePost()">삭제</button>
				</div>
	

		</div>
	</div>



	<div>
		<h1>댓글</h1>

	</div>


	<!-- 로그인 한 사람만 댓글 작성폼 보이게 -->
	<c:if test="${not empty member}">
		<form name="commentFrm" action="">
			<input type="hidden" name="boardNo" id="boardNo"
				value="${vo.boardNo}"> <input type="hidden" name="memberNo"
				id="memberNo" value="${member.memberNo}"> <input type="text"
				name="commentWriter" id="commentWriter"
				value="${member.memberNick }" readonly><input type="text"
				name="commentContent" id="commentContent" placeholder="댓글을 입력하세요">
			<input type="button" onclick="insertComment()" value="등록">
		</form>
	</c:if>

	<!-- 로그인 안해도 볼 수 있는 댓글리스트 -->
	<p>댓글 수:</p>
	<table id="list" border=1>
		<thead>
			<tr>
				<th><input type="checkbox"></th>
				<th>닉네임</th>
				<th>등록 날짜</th>
				<th width="500px">내용</th>
				<th>수정</th>
				<th>삭제</th>



			</tr>
		</thead>
		<tbody>
			<c:forEach items="${commentList}" var="list">
				<tr>


					<td><input type="checkbox"></td>
					<td>${list.commentWriter }</td>
					<td>${list.commentDate }</td>
					<th>${list.commentContent }</th>
					<td><button type="button"
							onclick="modComment(${list.commentNo },${list.boardNo })">수정</button></td>
					<td><button type="button"
							onclick="delComment(${list.commentNo },${list.boardNo })">삭제</button></td>
				</tr>

			</c:forEach>

		</tbody>
		<tr></tr>
	</table>


	</div>


	<script type="text/javascript">
		function deletePost() {
			var result = confirm("게시글을 삭제하시겠습니까?");
			if (result) {
				var form = document.writeFrm;
				form.method = "post";
				form.action = "boardDelete.do";
				form.submit();
			}
		}

	</script>
	<script type="text/javascript">
		function reportPost(){
			var result = confirm("게시글을 신고하시겠습니까?");
			if(result){
				window.open("reportForm.do");
			}
			function recommendPost() {
				var result = confirm("게시글을 추천하시겠습니까?");
				if (result) {
					var form = document.writeFrm;
					form.method = "post";
					form.action = "recommendInsert.do";
					form.submit();
				}
			}
		}
	




	</script>

	<script type="text/javascript">
		function updatePost() {
			var result = confirm("게시글을 수정하시겠습니까?");
			if (result) {
				var form = document.writeFrm;
				form.method = "post";
				form.action = "boardUpdateForm.do";
				form.submit();
			}
		}
		</script>

	<script type="text/javascript">
		function insertComment() {
			var result = confirm("댓글을 등록하시겠습니까?");
			if (result) {
				let bNo = document.commentFrm.boardNo.value;
				let mNo = document.commentFrm.memberNo.value;
				let wr = document.commentFrm.commentWriter.value;
				let con = document.commentFrm.commentContent.value;

				$.ajax({
					url : 'commentInsert.do',
					method : 'post',
					contentType : 'application/x-www-form-urlencoded',
					data : {
						bNo : bNo,
						mNo : mNo,
						wr : wr,
						con : con
					},
					//data: `bNo=${bNo}&mNo=${mNo}&wr=${wr}&con=${con}`,
					dataType : 'text',
					success : result => {
						//console.log("ajaxres: " + result);
						location.href='boardView.do?board_no=' + result},
					error : function(error) {
						alert('처리 중 오류 발생!');
					}
				})

				// 폼 인풋 초기화
				document.commentFrm.commentContent.value = "";
			}
		}
	</script>




	<script type="text/javascript">
		function recommendPost() {
			var result = confirm("게시글을 추천하시겠습니까?");
			if (result) {
				var form = document.writeFrm;
				form.method = "post";
				form.action = "recommendInsert.do";
				form.submit();
			}
		}
	</script>


	<script type="text/javascript">
		function scrapPost() {
			var result = confirm("게시글을 스크랩하시겠습니까?");
			if (result) {
				var form = document.writeFrm;
				form.method = "post";
				form.action = "scrapInsert.do";
				form.submit();
			}

	<script type="text/javascript">
	
	
	function delComment(cNo,bNo) {
		
		var result = confirm("댓글을 삭제하시겠습니까?");
		if (result) {
			$.ajax({
				url : 'commentDelete.do',
				method : 'post',
				contentType : 'application/x-www-form-urlencoded',
				data : {commentNo: cNo, boardNo: bNo},
				dataType : 'text',
				success : result => {
					//console.log("ajaxres: " + result);
					location.href='boardView.do?board_no=' + result},
				error : function(error) {
					alert('처리 중 오류 발생!');
				}
			})

		}
	}
	</script>


	<script type="text/javascript">
		function modComment(cNo) {
			var result = confirm("댓글을 수정하시겠습니까?");
			if (result) {
				location.href='commentUpdateForm.do?commentNo=' + cNo;
				//window.open("","댓글 수정","width=400, height=300, top=10, left=10");
			}
			
		}
	</script>


	<script type="text/javascript">
		function makeList(result) {
			let tbody = $('#list tbody');
			tbody.append(makeTr(result));
		}

		function makeTr(result) { // {}

			let input = $('<input />').attr('type', 'checkbox');
			let delBtn = $('<button />').text('삭제');
			//delBtn.on('click', delBtnFnc)
			let modBtn = $('<button />').text('수정');
			//modBtn.on('click', modBtnFnc)

			return $('<tr/ >').attr('id', result.commentNo).append(
					$('<td />').append(input),
					$('<td />').text(result.commentWriter),
					$('<td />').text(result.commentDate),
					$('<td />').text(result.commentContent),
					$('<td />').append(modBtn), $('<td />').append(delBtn))
		}
		</script>
	<script type="text/javascript">
		function recommendPost() {
			var form = document.writeFrm;
			form.method = "post";
			form.action = "recommendInsert.do";
			form.submit();
		}

		function scrapPost() {
			var form = document.writeFrm;
			form.method = "post";
			form.action = "scrapInsert.do";
			form.submit();


		}
	</script>
</body>
</html>