<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>금지어 추가</title>
<script src="js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div><h3>금지어 추가</h3></div>
	<form id="frm">
		<div>
			<input type="text" id="Xword" name="Xword">
			<button type="button" onclick="XwordInsert()">추가</button>
		</div>
	</form>
<script type="text/javascript">

function XwordInsert() { //추가
	let key = $("#Xword").val();
	console.log(key);
	$.ajax({
		url : "XwordInsert.do",
		type : "post",
		data : {key : key},
		dataType : "json",
		success : function(result){
				if(result =="1"){
					alert("등록되었습니다.");
					opener.location.reload();
					window.close();
					
				}else{
					alert("등록할 수 없습니다.");
				}
		},
		error : function(error){
			alert("ERROR!")
		}
	});
}

</script>
</body>
</html>