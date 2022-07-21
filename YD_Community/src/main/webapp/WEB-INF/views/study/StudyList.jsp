<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>
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



   <div class="board_title" align="left">
      <strong>스터디게시판</strong>
   </div>
   
   <!-- 공통 검색기능 -->
      <div class="board_list_wrap" align="right">
         <form id="frm">
            <select id="key" name="key">
               <option value="study_title">제목</option>
               <option value="study_subject">내용</option>
               <option value="study_writer">작성자</option>
            </select> &nbsp; 
            <input type="text" id="val" name="val">&nbsp;&nbsp;
            <input type="button" class="btn btn-success" value="검색" onclick="studySearch()">
         </form>
      </div>
      <br>
      <!-- 공통 검색기능 -->
   
   
   
   
   
   
   
   
   <div class="board_list_wrap" align="center">
      <table class="board_list">
         <thead>
            <tr>
               <th width="70">번호</th>
               <th width="200">제목</th>
               <th width="100">스터디방식</th>
               <th width="100">스터디기간</th>
               <th width="100">스터디언어</th>
               <th width="90">작성일자</th>
               <th width="80">글작성자</th>
            </tr>
         </thead>
         <tbody id="tb">
            <c:choose>
               <c:when test="${not empty list }">
                  <c:forEach items="${list }" var="b">

                     <tr>
                        <td class="tit">${b.studyNo }</td>
                        <td class="tit"><a href="StudyView.do?study_no=${b.studyNo}">${b.studyTitle }</a></td>
                        <td class="tit">${b.studySystem }</td>
                        <td class="tit">${b.studyPeriod }</td>
                        <td class="tit">${b.studyLanguage }</td>
                        <td class="tit">${b.studyDate }</td>
                        <td class="tit"><a href="myPage.do?no=${b.memberNo}">${b.studyWriter }</a></td>
                     </tr>
                  </c:forEach>
               </c:when>
               <c:otherwise>

                  <tr>
                     <td colspan="8" align="center">게시글이 존재하지않습니다</td>
                  </tr>
               </c:otherwise>
            </c:choose>

         </tbody>
      </table>

      <button type="button"  class="btn btn-success" onclick="location.href='StudyForm.do'">글등록</button>
        
      
   
      <div class="paging" align="center">
         <a href="#" class="bt">첫 페이지</a> <a href="#" class="bt">이전 페이지</a> <a
            href="#" class="num on">1</a> <a href="#" class="num">2</a> <a
            href="#" class="num">3</a> <a href="#" class="bt">다음 페이지</a> <a
            href="#" class="bt">마지막 페이지</a>
      </div>
   </div>
   
   
   <script type="text/javascript">
      function studySearch() {
         let key = $("#key").val();
         let val = $("#val").val();
         $.ajax({
            url : "ajaxNoticeSearche.do",
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
                     $("<td />").text(item.studyNo),
                     $("<td><a href=${b.studyTitle}</a></td>").text(item.studyTitle),
                     $("<td />").text(item.studySystem),
                     $("<td />").text(item.studyPeriod),
                     $("<td />").text(item.studyLanguage),
                     $("<td />").text(item.studyDate),
                     $("<td />").text(item.studyWriter)               
                  );
            tbody.append(row);
         });
         $('table').append(tbody);
      }
      
      
   </script>
</body>
</html>