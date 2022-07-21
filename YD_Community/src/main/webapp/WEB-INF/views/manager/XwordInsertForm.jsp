<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>금지어 추가</title>
<script src="js/jquery-3.6.0.min.js"></script>

<style>
input[type=text]{
    width: 300px;
    height: 50px;
    font-size: larger;
    color: grey;
    margin: 0 auto;
    text-align: center;
    display: inline;
    border: 2px solid  #1abc9c;
  border-radius: 5px 5px;
}

button{
   width: 70px;
   height: 50px;
   font-size: larger;
   color:rgb(255, 255, 255);
   background-color: #1abc9c;
   font-weight: bold;
   margin:-1px;
   text-align: center;
   display: inline;
   border: 2px solid  #1abc9c;
  border-radius: 5px 5px;
}
</style>

</head>
<body>
	<div><h3>금지어 추가</h3></div>
	<form id="frm">
		<div>
			<input type="text" class="form-control is-invalid" id="Xword" name="Xword">
			<button type="button" class="btn btn-success" onclick="XwordInsert()">추가</button>
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