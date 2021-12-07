$(document).ready(function(){
	$("#clothesMain").click(function(){//[관리자 메인으로]버튼 클릭
		window.location.href="/clothes/manager/managerMain.do";
	}); 
});

function reply(replyBtn){//[답변하기]버튼 클릭
	var rStr = replyBtn.name;
	var query = "/clothes/manager/qnaReplyForm.do?qna_id="+rStr;
	window.location.href=query;
}

function edit(editBtn){//[수정]버튼 클릭
	var rStr = editBtn.name;
	var query = "/clothes/manager/qnaReplyUpdateForm.do?qna_id="+rStr;
	window.location.href=query;
}