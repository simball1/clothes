$(document).ready(function(){
    $("#regist").click(function(){//[등록]버튼 클릭
    	  var clothes_kind = $("#clothes_kind").val();
    	  var clothes_id = $("#clothes_id").val();

  		  var query = {qna_content:$("#qnaCont").val(),
  				       qna_writer:$("#qna_writer").val(),
  				       clothes_title:$("#clothes_title").val(),
  				       clothes_id:clothes_id,
  				       qora:$("#qora").val()};
  		  
  		  $.ajax({
  		     type: "POST",
  		     url: "/clothes/qnaPro.do",
  		     data: query,
  		     success: function(data){
  		    	var str1 = '<p id="ck">';
	    		var loc = data.indexOf(str1);
	    		var len = str1.length;
	    		var check = data.substr(loc+len,1);
	    		if(check == "1"){//
	    			alert("QnA가 등록되었습니다.");
 		    		var query = "/clothes/clothesContent.do?clothes_id="+clothes_id;
 		    		query += "&clothes_kind="+clothes_kind;
 		    		window.location.href=query;
	    	     }else
	    	    	 alert("QnA 등록 실패");
  		     }
  		  });
	});
    
    $("#cancle").click(function(){//[취소]버튼 클릭
    	var clothes_kind = $("#clothes_kind").val();
    	var clothes_id = $("#clothes_id").val();
    	var query = "/clothes/clothesContent.do?clothes_id="+clothes_id;
 		query += "&clothes_kind="+clothes_kind;
 		window.location.href=query;
	});
       
});