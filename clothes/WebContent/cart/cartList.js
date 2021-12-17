durl$(document).ready(function(){
	$("#conShopping").click(function(){ // 쇼핑계속 버튼 클릭
		window.location.href="/clothes/list.do?clothes_kind=all";
	});
	
	$("#shopMain").click(function(){ // 메인으로 버튼 클릭
		window.location.href="/clothes/loginForm.do";
	});
});

function editSu(editBtn){ // 수정 버튼 클릭

	var rStr = editBtn.name;
	var arr = rStr.spit(",");
	var query = "/clothes/cartUpdateForm.do?cart_id="+arr[0];
	query += "&buy_count="+arr[1];
	window.location.href=query;
}

function delList(delBtn){ //삭제버튼 클릭
	var rStr = delBtn.name;
	var query = "/clothes/deleteCart.do?list="+rStr;
	window.location.href=query;
}