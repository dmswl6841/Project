<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
    text-align: center;
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
</style>

</head>
<body>
<div class="board_title" align="center">
      <strong>관리자 페이지</strong>
   </div>
<h5>가입 대기</h5>
<a href="managerMemberList.do">회원 전체보기</a><br>
<form id="form1">
	<div class="board_list_wrap" align="center">
	<table class="board_list">
		<thead>
			<tr>
				<th width="7">NO</th>
				<th width="7">사용자 ID</th>
				<th width="7">사용자 명</th>
				<th width="7">경고 횟수</th>
				<th width="7">회원 권한</th>
				<th width="7">가입 여부</th>
				<th width="7">회원 탈퇴</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty mlist }">
					<c:forEach items="${mlist }" var="m" begin="0" end="9">
						<tr>
							<td id="memberNo" name="memberNo">${m.memberNo }</td>
							<td id="memberId" name="memberId">${m.memberId }</td>
							<td id="memberNick" name="memberNick">${m.memberNick }</td>
							<td id="memberWarning" name="memberWarning">${m.memberWarning }</td>
							<td id="memberAuthor" name="memberAuthor">${m.memberAuthor }</td>
							<td><button type="button" class="btn btn-success btn-sm" style="background-color: #1abc9c;"
							 onclick="memberUpdate(this)">승인</button></td>
							<td align="center">
								<button type="button" class="btn btn-outline-danger btn-sm"  onclick="memberDelete(${m.memberNo })">삭제</button>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="7" align="center">
							가입 대기 중인 회원이 존재하지 않습니다
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	</div>
</form>

<h5>최신 게시글</h5>
<a href="managerBoardList.do">게시글 전체보기</a><br>
<form id="form2">
	<div class="board_list_wrap" align="center">
	<table class="board_list">
		<thead>
			<tr>
				<th width="70">NO</th>
				<th width="70">게시판</th>
				<th width="50">게시글 제목</th>
				<th width="50">작성자</th>
				<th width="50">작성일자</th>
				<th width="50">글 삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty  bList }">
					<c:forEach items="${bList }" var="b" begin="0" end="9">
						<tr>
							<td id="boardNo" name="boardNo">${b.boardNo }</td>
							<td id="boardCategory" name="boardCategory">${b.boardCategory }</td>
							<td id="boardTitle" name="boardTitle">${b.boardTitle }</td>
							<td id="boardWriter" name="boardWriter">${b.boardWriter }</td>
							<td id="boardDate">${b.boardDate }</td>
							<td><input type="button" class="btn btn-outline-danger btn-sm" value="삭제" onclick="boardDelete(${b.boardNo })"></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="6" align="center">
							게시글이 존재하지 않습니다
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	</div>
</form>

<h5>금지어</h5>
<a href="XwordList.do">금지어 전체보기</a><br>
<form id="form3">
	<div class="board_list_wrap" align="center">
	<table class="board_list">
		<thead>
			<tr>
				<th width="70">NO</th>
				<th width="50">금지어</th>
				<th width="50">수정</th>
				<th width="50">삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty  xList }">
					<c:forEach items="${xList }" var="x" begin="0" end="9">
						<tr>
							<td id="xwordNo" name="xwordNo">${x.xwordNo }</td>
							<td id="xword" name="xword">${x.xword }</td>
							<td><input type="button" class="btn btn-success btn-sm" value="수정" onclick="XwordUpdate(${x.xwordNo })"></td>
							<td><input type="button" class="btn btn-outline-danger btn-sm" value="삭제" onclick="XwordDelete(this)"></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4" align="center">
							금지어가 존재하지 않습니다
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	</div>
</form>
<script type="text/javascript">
	function memberUpdate(obj) { //가입 승인
		let key = obj.parentElement.parentElement.children[4].textContent;
			console.log(key);
		let row = $(obj).parent().parent().get(0);
		let td = row.cells[0];
		let val = $(td).html();
		console.log(val);
		$.ajax({
			url : "memberUpdateAuthor.do",
			type : "post",
			data : {key : key, val : val},
			dataType : "json",
			success : function(result){
				console.log(result);
				if(result == "1") {
					alert("가입 승인되었습니다.");
					location.reload();
				}else if(result == "2") {
					alert("관리자는 바꿀 수 없습니다!");
					location.reload();
				}else {
					alert("이미 승인된 회원입니다.");
					location.reload();
				}
			},
			error : function(error){
				alert("ERROR!")
			}
		});
	}
	
	function memberDelete(id){ //회원 삭제
		const xhr = new XMLHttpRequest();
		const url = "managerMemberDelete.do?memberNo="+id;
		console.log(url)
		xhr.onload = function(){
			if(xhr.status >=200 && xhr.status <300){
				if(xhr.response ==1){
					alert("삭제되었습니다.");
					$(row).remove();
					location.reload();
				}else{
					alert("삭제할 수 없습니다.");
				}
				
			}else {
				errorCallback(new Error(xhr.stautsText));
			}
		};
		xhr.open('GET',url);
		xhr.send(); 
	}
	
	function boardDelete(id){ //게시판 삭제
		let key = id;
		$.ajax({
			url : "managerBoardDelete.do",
			type : "post",
			data : {key : key},
			dataType : "json",
			success : function(result){
				if(result ==1){
					alert("삭제되었습니다.");
					$(row).remove();
					location.reload();
				}else{
					alert("삭제할 수 없습니다.");
				}
			},
			error : function(error){
				alert("ERROR!")
			}
		});
	}
	
	function XwordDelete(obj){ //금지어 삭제
		let row = $(obj).parent().parent().get(0);
		let td = row.cells[0];
		let no = $(td).html();
		
		const xhr = new XMLHttpRequest();
		const url = "XwordDelete.do?xwordNo="+no;
		console.log(url)
		xhr.onload = function(){
			if(xhr.status >=200 && xhr.status <300){
				if(xhr.response ==1){
					alert("삭제되었습니다.");
					$(row).remove();
				}else{
					alert("삭제할 수 없습니다.");
				}
				
			}else {
				errorCallback(new Error(xhr.stautsText));
			}
		};
		xhr.open('GET',url);
		console.log(xhr.open('GET',url));
		xhr.send(); 
	}
</script>
</body>
</html>