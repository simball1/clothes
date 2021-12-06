$(document).ready(function(){
	
	$("#regist").click(function(){//[책등록]버튼 클릭
		window.location.href="/clothes/manager/clothesRegisterForm.do";
	});
	
	$("#clothesMain").click(function(){//[관리자 메인으로]버튼 클릭
		window.location.href="/clothes/manager/managerMain.do";
	});
});

//[수정]버튼을 클릭하면 자동실행
function edit(editBtn) {
	var rStr = editBtn.name;
	var arr = rStr.split(",");
	var query = "/clothes/manager/clothesUpdateForm.do?clothes_id="+arr[0];
	query += "&clothes_kind="+arr[1];
	window.location.href=query;
}

//[삭제]버튼을 클릭하면 자동실행
function del(delBtn){
	var rStr = delBtn.name;
	var arr = rStr.split(",");
	var query = "/clothes/manager/clothesDeletePro.do?clothes_id="+arr[0];
	query += "&clothes_kind="+arr[1];
	window.location.href=query;
}