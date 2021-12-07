$(document).ready(function(){
    $("#replyPro").click(function(){//[답변하기]버튼 클릭
  		  var query = {qna_content:$("#rContent").val(),
  				       qna_writer:$("#qna_writer").val(),
  				       clothes_title:$("#clothes_title").val(),
  				       clothes_id:$("#clothes_id").val(),
  				       qna_id:$("#qna_id").val(),
  				       qora:$("#qora").val()};
  		  
  		  $.ajax({
  		     type: "POST",
  		     url: "/clothes/manager/qnaReplyPro.do",
  		     data: query,
  		     success: function(data){
  		    	window.location.href="/clothes/manager/qnaList.do";
  		     }
  		  });
	});
    
    $("#cancle").click(function(){//[취소]버튼 클릭
    	window.location.href="/clothes/manager/managerMain.do";
	});
       
});
