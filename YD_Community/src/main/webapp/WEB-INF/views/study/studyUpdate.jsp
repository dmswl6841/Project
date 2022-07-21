<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

.bt_wrap {
    margin-top: 30px;
    text-align: center;
    font-size: 0;
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

.board_list > div {
    border-bottom: 1px solid #ddd;
    font-size: 0;
}

.board_list > div.top {
    border-bottom: 1px solid #999;
}

.board_list > div:last-child {
    border-bottom: 1px solid #000;
}

.board_list > div > div {
    display: inline-block;
    padding: 15px 0;
    text-align: center;
    font-size: 1.4rem;
}

.board_list > div.top > div {
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

.board_view .info dl dt,
.board_view .info dl dd {
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

.board_write .title,
.board_write .info {
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

.board_write .title dt,
.board_write .title dd,
.board_write .info dt,
.board_write .info dd {
    display: inline-block;
    vertical-align: middle;
    font-size: 1.4rem;
}

.board_write .title dt,
.board_write .info dt {
    width: 100px;
}

.board_write .title dd {
    width: calc(100% - 100px);
}

.board_write .title input[type="text"],
.board_write .info input[type="text"],
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
    
    
   
    #input1 {
    width : 200px;
    height: 50px;
    color: black;
    margin: 50px;
    text-align: center;
    }
    #input2 {
    width : 200px;
    height: 50px;
    color: black;
    text-align: center;
    right: 100px;
    }
    
    
    textarea::placeholder {
         font-size: 2.5em;
}

.board_view .cont {
    padding: 15px;
    border-bottom: 1px solid #000;
    line-height: 160%;
    font-size: 1.4rem;
    font-size:14px;
}
    
    </style>


</head>
<body>
	 <div class="board_wrap">
        <div class="board_title">
            <strong>스터디</strong>
            <p> 더 나은 개발자가 되기위한 예담 스터디 게시판</p>
        </div>

			<form id="frm" action="StudyUpdate.do" method="post"
				enctype="multipart/form-data">
						
							<c:choose>
								<c:when test="${not empty vo}">
									<input type="hidden" name="studyNo" value="${vo.studyNo}">
				                  <div class="board_write_wrap">
					              <div class="board_write">
					
									
					                <div class="title">
									<dl>
                                      <dt>제목</dt>
                                      <dd><input type="text" size="73" id="studyTitle"
											name="studyTitle" value="${vo.studyTitle }"></dd>
                                    </dl>
                                   </div>
									 <div class="info">
                    <dl>
                        <dt>글쓴이</dt>
                        <dd><input type="text" id="studyWriter"
                            name="studyWriter" value="${member.memberNick }" readonly></dd>
                    </dl>
                    <dl>
                        <dt>사용언어</dt>
                        <dd><input type="text" id="studyLang"
						    name="studyLang" value="${vo.studyLanguage }"></dd>
                    </dl><br>
                 
                    <dl>
                        <dt>스터디기간</dt>
                        <dd><input type="text"  id="studyPeriod"
							name="studyPeriod" value="${vo.studyPeriod }"></dd>
                    </dl><br>
                    
                    <dl>
                        <dt>스터디방식</dt>
                        <dd><input type="text"  id="studySystem"
							name="studySystem" value="${vo.studySystem }"></dd>
                    </dl>
                   
                    <dl>
                        <dt>스터디 멤버수</dt>
                        <dd><input type="text" id="studyMember"
                            name="${vo.studyMember } value="${vo.studyMember }"></dd>
                    </dl>

                    <dl>
                        <dt>첨부파일</dt>
                        <dd><input type="file" id="studyAttech" name="studyAttech" value="${vo.studyAttech }"></dd>
                    </dl>



                    <td width="200"><input type="hidden" id="memberNo"
                        name="memberNo" value="${member.memberNo }"></td>
                </tr>


                </div>
                <div class="cont">
                    <textarea placeholder="스터디내용을 자유롭게 적어보세요~"name="studySubject"
							value=${vo.studySubject }></textarea>
                </div>
            </div> 

								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="8" align="center">게시글이 존재하지 않습니다</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
				<br>
				<div>
					<input type="submit" value="수정">&nbsp;&nbsp;&nbsp; <input
						type="reset" value="취소">
				</div>
			</form>


		</div>
	</div>
</body>
</html>