$(document).ready(function() {
	$('#delBtn').on('click', delComment);
})

function deletePost() {
	var result = confirm("게시글을 삭제하시겠습니까?");
	if (result) {
		var form = document.writeFrm;
		form.method = "post";
		form.action = "boardDelete.do";
		form.submit();
	}
}

function updatePost() {
	var result = confirm("게시글을 수정하시겠습니까?");
	if (result) {
		var form = document.writeFrm;
		form.method = "post";
		form.action = "boardUpdateForm.do";
		form.submit();
	}
}

function insertComment() {
	var result = confirm("댓글을 등록하시겠습니까?");
	if (result) {
		let bNo = document.commentFrm.boardNo.value;
		let mNo = document.commentFrm.memberNo.value;
		let wr = document.commentFrm.commentWriter.value;
		let con = document.commentFrm.commentContent.value;

		$.ajax({
			url: 'commentInsert.do',
			method: 'post',
			contentType: 'application/x-www-form-urlencoded',
			data: {
				bNo: bNo,
				mNo: mNo,
				wr: wr,
				con: con
			},
			//data: `bNo=${bNo}&mNo=${mNo}&wr=${wr}&con=${con}`,
			dataType: 'text',
			success: result => {
				//console.log("ajaxres: " + result);
				location.href = 'boardView.do?board_no=' + result
			},
			error: function(error) {
				alert('처리 중 오류 발생!');
			}
		})

		// 폼 인풋 초기화
		document.commentFrm.commentContent.value = "";
	}
}

function delComment() {
	console.log(this);

	var result = confirm("댓글을 삭제하시겠습니까?");
	if (result) {
		$.ajax({
			url: 'commentDelete.do',
			method: 'post',
			contentType: 'application/x-www-form-urlencoded',
			data: { commentNo: commentNo },
			dataType: 'text',
			success: result => {

				console.log("ajaxres: " + result);
				//location.href='boardView.do?board_no=' + result
			},
			error: function(error) {
				alert('처리 중 오류 발생!');
			}
		})

	}
}

function modComment() {
	var result = confirm("댓글을 수정하시겠습니까?");
	if (result) {

	}

}

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
