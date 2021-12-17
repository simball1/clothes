$(document).ready(function(){
	$("#conShopping").click(function(){//[쇼핑계속]버튼 클릭
		window.location.href="/clothes/list.do?clothes_kind=all";
	});
	
	$("#shopMain").click(function(){//[메인으로]버튼 클릭
		window.location.href="/clothes/index.do";
	});
});
