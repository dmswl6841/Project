<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글목록</title>

</head>
<style>
select{
    width: 500px;
    height: 50px;
    border-radius: 5px;
}
option{
    font-family: 'Courier New', Courier, monospace;
    font-size: 20px;
   
}

input{
    width: 500px;
    height: 50px;
    border-radius: 5px;
}

</style>
<body>
	<div align="left">
		<div>
			<h1>1.프로젝트 기본 정보를 입력해주세요.</h1>
		</div>
		<div>
			<form id="frm" action="studyInsert.do" method="post"
				enctype="multipart/form-data">
                <h4>진행방식</h4>
				<select name='StudySystem'>
                    <option value='' selected></option>
                    <option value='OnLine'>온라인</option>
                    <option value='OffLine'>오프라인</option>
                  </select>
                  </form>
                  </div>

                  <div>
                    <form id="frm" action="studyInsert.do" method="post"
                        enctype="multipart/form-data">
                        <h4>기술스택</h4>
                        <select name='StudySystem'>
                            <option value='' selected></option>
                            <option value='JAVA'>JAVA</option>
                            <option value='JAVA'>JAVA</option>
                            <option value='JAVA'>JAVA</option>
                            <option value='JAVA'>JAVA</option>
                            <option value='JAVA'>JAVA</option>
                            <option value='JAVA'>JAVA</option>
                            <option value='JAVA'>JAVA</option>
                            <option value='JAVA'>JAVA</option>
                            <option value='JAVA'>JAVA</option>
                            <option value='JAVA'>JAVA</option>
                            <option value='JAVA'>JAVA</option>
                            <option value='JAVA'>JAVA</option>
                            <option value='JAVA'>JAVA</option>
                            <option value='JAVA'>JAVA</option>
                          </select>
                          </form>
                          </div>


                          <div>
                            <form id="frm" action="studyInsert.do" method="post"
                                enctype="multipart/form-data">
                                <h4>모집인원</h4>
                                <select name='StudySystem'>
                                    <option value='' selected></option>
                                    <option value='0'>인원미정</option>
                                    <option value='1'>1명</option>
                                    <option value='2'>2명</option>
                                    <option value='3'>3명</option>
                                    <option value='4'>4명</option>
                                    <option value='5'>5명</option>
                                    <option value='6'>6명</option>
                                    <option value='7'>7명</option>
                                    <option value='8'>8명</option>
                                    <option value='9'>9명</option>
                                    <option value='10명이상'>10명이상</option>
                                  </select>
                                  </form>
                                  </div>



                                  <div>
                                    <form id="frm" action="studyInsert.do" method="post"
                                        enctype="multipart/form-data">
                                        <h4>시작예정일</h4>
                                   <input type="date">
                                          </form>
                                          </div>
</body>
</html>