<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>YEDAMSTUDY_스터디게시판</title>
<script src="js/jquery-3.6.0.min.js"></script>

<link rel="stylesheet" href="css/css.css">
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
			<strong>스터디게시판</strong>
			<p>더 나은 개발자로 나가는 지름길</p>
		</div>
		<div class="board_view_wrap">
			<div class="board_view">
				<div class="title"></div>
				<c:choose>
					<c:when test="${not empty vo}">

						<form name="writeFrm">
							<input type="hidden" name="studyNo" value="${vo.getStudyNo()}">
						</form>

						<div class="info" id="studyDel">

							<dl>
								<dt>제 목</dt>
								<dd>${vo.getStudyTitle()}</dd>
							</dl>
							<dl>
								<dt>스터디방식</dt>
								<dd>${vo.studySystem }</dd>
							</dl>
							<dl>
								<dt>작성날짜</dt>
								<dd>${vo.studyDate }</dd>
							</dl>
							<dl>
								<dt>작성자</dt>
								<dd>${vo.getStudyWriter() }</dd>
							</dl>
							<dl>
								<dt>스터디언어</dt>
								<dd>${vo.studyLanguage }</dd>
							</dl>

							<dl>
								<dt>스터디인원</dt>
								<dd>${vo.studyMember }</dd>
							</dl>


							<br>
						</div>
						<div class="cont">${vo.studySystem }</div>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5" align="center">게시글이 존재하지 않습니다</td>
						</tr>
					</c:otherwise>
				</c:choose>

			</div>
			&nbsp;


			<button type="button" class="btn btn-success"
				onclick="studyUpdate(${vo.studyNo })">수정</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="btn btn-success"
				onclick="studyDelete(${vo.studyNo })">삭제</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="StudyList.do"
				class="btn btn-success">목록</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

			<div id="commentArea">
				<!-- 로그인 한 사람만 댓글 작성폼 보이게 -->
				<c:if test="${not empty member}">
					<form name="commentFrm" action="">
						<input type="hidden" name="studyNo" id="studyNo"
							value="${vo.studyNo}"> <input type="hidden"
							name="memberNo" id="memberNo" value="${member.memberNo}">
						<input type="text" name="commentWriter" id="commentWriter"
							value="${member.memberNick }" readonly><input type="text"
							name="commentContent" id="commentContent" placeholder="댓글을 입력하세요">
						<input type="button" onclick="insertComment()" value="등록">
					</form>
				</c:if>

				<!-- 로그인 안해도 볼 수 있는 댓글리스트 -->
				<p>댓글 수: ${count }</p>
				<table id="list" border=1>
					<thead>
						<tr>
							<th><input type="checkbox"></th>
							<th>닉네임</th>
							<th>등록 날짜</th>
							<th width="500px">내용</th>
							<c:if test="${not empty member}">
								<c:if test="${list.memberNo eq member.memberNo }">
									<th>수정</th>
									<th>삭제</th>
								</c:if>
							</c:if>

						</tr>
					</thead>
					<tbody>
						<c:forEach items="${commentList}" var="list">
							<tr>

								<td><input type="checkbox"></td>
								<td>${list.commentWriter }</td>
								<td>${list.commentDate }</td>
								<th>${list.commentContent }</th>
								<c:if test="${not empty member}">
									<c:if test="${list.memberNo eq member.memberNo }">

										<td><button type="button"
												onclick="modComment(${list.commentNo })">수정</button></td>
										<td><button type="button"
												onclick="delComment(${list.commentNo },${list.studyNo })">삭제</button></td>
									</c:if>
								</c:if>
							</tr>
						</c:forEach>

					</tbody>
					<tr></tr>
				</table>

			</div>


		</div>
	</div>

	<br>

	<script type="text/javascript">
		function studyDelete(id) {
			
			const xhr = new XMLHttpRequest();
			const url = "StudyDelete.do?studyNo=" + id;
			xhr.onload = function() {
				if (xhr.status >= 200 && xhr.status < 300) {
					if (xhr.response == 1) {
						alert("데이터가 삭제되었습니다.");
						location.href = "StudyList.do";
					} else {
						alert("데이터가 삭제되지 않았습니다.");
					}
				};					
			} 
			xhr.open('GET', url);
			xhr.send();
		}
		
		// 수정
		function studyUpdate(id) {
			var result = confirm("게시글을 수정하시겠습니까?");
			if (result) {
				var form = document.writeFrm;
				form.method = "post";
				form.action = "StudyUpdateForm.do";
				form.submit();
			}
		}
		
		function insertComment() {
			var result = confirm("댓글을 등록하시겠습니까?");
			if (result) {
				let sNo = document.commentFrm.studyNo.value;
				let mNo = document.commentFrm.memberNo.value;
				let wr = document.commentFrm.commentWriter.value;
				let con = document.commentFrm.commentContent.value;

				$.ajax({
					url : 'commentInsert.do',
					method : 'post',
					contentType : 'application/x-www-form-urlencoded',
					data : {
						sNo : sNo,
						mNo : mNo,
						wr : wr,
						con : con
					},
					dataType : 'text',
					success : result => {
						//console.log("ajaxres: " + result);
						location.href='StudyView.do?study_no=' + result},
					error : function(error) {
						alert('처리 중 오류 발생!');
					}
				})

				// 폼 인풋 초기화
				document.commentFrm.commentContent.value = "";
			}
		}
		
		
		function delComment(cNo,sNo) {
			
			var result = confirm("댓글을 삭제하시겠습니까?");
			if (result) {
				$.ajax({
					url : 'commentDelete.do',
					method : 'post',
					contentType : 'application/x-www-form-urlencoded',
					data : {commentNo: cNo, studyNo: sNo},
					dataType : 'text',
					success : result => {
						//console.log("ajaxres: " + result);
						location.href='StudyView.do?study_no=' + result},
					error : function(error) {
						alert('처리 중 오류 발생!');
					}
				})

			}
		}


		
		
	</script>

</body>
</html>