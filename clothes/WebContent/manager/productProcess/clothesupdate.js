$(document).ready(function(){
	$("#upForm1").ajaxForm({//[옷수정]버튼 클릭
		success: function(data, status){//업로드에 성공하면 수행
   			window.location.href="/clothes/manager/clothesList.do?book_kind=all";
   		}
    });
	
	$("#clothesMain").click(function(){//[관리자 메인으로]버튼 클릭
		window.location.href="/clothes/manager/managerMain.do";
	});
	
	$("#clothesList").click(function(){//[목록으로]버튼 클릭
		window.location.href="/clothes/manager/clothesList.do?clothes_kind=all";
	});
});