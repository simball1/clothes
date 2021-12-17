$(document).ready(function(){

	$("#modifyProcess").click(function(){//[수정]버튼 클릭
		var query = {id:$("#id").val(), 
				  passwd:$("#passwd").val(),
			      name:$("#name").val(),
			      address:$("#address").val(),
			      tel:$("#tel").val(),
				  year:$("#year").val(),
				  month:$("#month").val(),
			      day:$("#day").val()};
		
		$.ajax({
			type: "post",
			url: "/clothes/modifyPro.do",
			data: query,
			success: function(data){
				var str1 = '<p id="ck">';
	    		var loc = data.indexOf(str1);
	    		var len = str1.length;
	    		var check = data.substr(loc+len,1);
	    		if(check == "1"){//
	    			alert("회원정보가 수정되었습니다.");
					window.location.href="/clothes/modify.do";
	    	    }else{
	    	    	alert("비밀번호 틀림.");
	    	    	$("#passwd").val("");
	    	    	$("#passwd").focus();
	    	    }
		   }
		});
	});
	
	$("#cancle").click(function(){//[취소]버튼 클릭
		window.location.href="/clothes/modify.do";
	});
	
	//생년월일 
	for (var i = 2021; i > 1920; i--) {
		 $('#year').append('<option value="' + i + '">' + i + '</option>'); 
	} 
	for (var i = 1; i < 13; i++) {
		 $('#month').append('<option value="' + i + '">' + i + '</option>'); 
	} 
	for (var i = 1; i < 32; i++) {
		 $('#day').append('<option value="' + i + '">' + i + '</option>'); 
	} 
 });