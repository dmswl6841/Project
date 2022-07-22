<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.6.0.min.js"></script>

</head>
<body>
	<div align="center">
		<form name="commentFrm" action="">
			<input type="hidden" name="cNo" id="cNo" value="${vo.commentNo}">
			<input type="hidden" name="bNo" id="bNo" value="${vo.boardNo}">
			<input type="text" name="con" id="con" value="${vo.commentWriter }" readonly><br> 
			<input type="textarea" name="commentContent" id="commentContent" value="${vo.commentContent }"> 
			<input type="button" onclick="updateComment()" value="수정">
		</form>
	</div>

	<script>
function updateComment(){
	var result = confirm("댓글을 수정하시겠습니까?");
	if (result) {
		
		$.ajax({
			url : 'commentUpdate.do',
			method : 'post',
			data : {cNo : cNo, bNo : bNo, con : con},
			dataType : 'text',
			success : function(result){console.log(result)},
			error : function(error) {
				alert('처리 중 오류 발생!');
			}
		})
	}
}
</script>
</body>
</html>