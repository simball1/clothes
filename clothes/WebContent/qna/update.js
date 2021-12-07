$(document).ready(function(){
    $("#update").click(function(){//[수정]버튼 클릭
    	  var clothes_id = $("#clothes_id").val();
    	  var clothes_kind = $("#clothes_kind").val();

  		  var query = {qna_content:$("#updateCont").val(),
  				       qna_id:$("#qna_id").val()};
  		  
  		  $.ajax({
  		     type: "POST",
  		     url: "/clothes/qnaUpdatePro.do",
  		     data: query,
  		     success: function(data){
  		    	var str1 = '<p id="ck">';
	    		var loc = data.indexOf(str1);
	    		var len = str1.length;
	    		var check = data.substr(loc+len,1);
	    		if(check == "1"){
	    			alert("QnA가 수정되었습니다.");
	    			var query = "/clothes/clothesContent.do?clothes_id="+clothes_id;
 		    		query += "&clothes_kind="+clothes_kind;
 		    		window.location.href=query;
	    	     }else
	    	  	    alert("QnA수정 실패");
  		     }
  		  });
	});
    
    $("#cancle").click(function(){//[취소]버튼 클릭
    	var clothes_id = $("#clothes_id").val();
  	    var clothes_kind = $("#clothes_kind").val();
    	var query = "/clothes/clothesContent.do?clothes_id="+clothes_id;
 		query += "&clothes_kind="+clothes_kind;
 		window.location.href=query;
	});
     
});
