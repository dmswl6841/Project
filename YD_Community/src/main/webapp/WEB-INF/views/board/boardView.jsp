<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
<script src="js/jquery-3.6.0.min.js"></script>


<style>
</style>

</head>

<body>
	<div align="center">
		<div>
			<h1>게시글 보기</h1>
		</div>
		<form name="writeFrm">
			<div>
				<table border="1">
					<tbody>
						<c:choose>
							<c:when test="${not empty vo}">
								<input type="hidden" name="board_no" value="${vo.boardNo}">
								<input type="hidden" name="member_no"
									value="${member.memberNo }">
								<tr>
									<td><a href="myPage.do?no=${vo.memberNo}">${vo.boardWriter }</a></td>
									<td>👀 ${vo.boardHit }</td>
								</tr>
								<tr>
									<td># ${vo.boardNo }</td>
									<c:if test="${vo.boardCategory =='자유' }">
										<td>🎡 ${vo.boardCategory }</td>
									</c:if>
									<c:if test="${vo.boardCategory =='QnA' }">
										<td>💡 ${vo.boardCategory }</td>
									</c:if>
									<c:if test="${vo.boardCategory =='공지' }">
										<td>📢 ${vo.boardCategory }</td>
									</c:if>
									<c:if test="${vo.boardCategory =='HOT' }">
										<td>🏆 ${vo.boardCategory }</td>
									</c:if>
								</tr>
								<tr>
									<td colspan="3">${vo.boardTitle }</td>
									<td>${vo.boardDate }</td>
									<td>${vo.boardAttech }</td>
								</tr>
								<tr>
									<td colspan="4">${vo.boardSubject }</td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="8" align="center">게시글이 존재하지 않습니다</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
				<br>


				<div align="center">
					<button type="button" id="recommend" onclick="recommendPost()"
						title="이 글을 추천하기">👍 ${vo.boardRecommend }</button>

					<button type="button" id="scrap" onclick="scrapPost()"
						title="이 글을 스크랩하기">📌 ${vo.boardScrap }</button>

					<button type="button" id="scrap" onclick="reportPost()"
						title="이 글을 신고하기"> 🚫 </button>
				</div>
				<br>

				<c:if test="${vo.memberNo eq memberNo}">


					<div align="center">
						<button type="button" id="recommend" onclick="recommendPost()"
							title="이 글을 추천하기">👍</button>
						<button type="button" id="scrap" onclick="scrapPost()"
							title="이 글을 스크랩하기">📌</button>
					</div>

					<div align="center">
						<button type="button" onclick="updatePost()">수정</button>
						<button type="button" onclick="deletePost()">삭제</button>
					</div>
				</c:if>

			</div>
		</form>


		<div>
			<h1>댓글</h1>

		</div>

		<!-- 로그인 한 사람만 댓글 작성폼 보이게 -->
		<c:if test="${not empty member}">
			<form name="commentFrm" action="">
				<input type="hidden" name="boardNo" id="boardNo"
					value="${vo.boardNo}"> <input type="hidden" name="memberNo"
					id="memberNo" value="${member.memberNo}"> <input
					type="text" name="commentWriter" id="commentWriter"
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
		function modComment(cNo,bNo) {
			var result = confirm("댓글을 수정하시겠습니까?");
			if (result) {
				window.open("","댓글 수정","width=400, height=300, top=10, left=10");
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