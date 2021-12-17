$(document).ready(function(){
	$("#checkId").click(function(){//[ID중복확인]버튼 클릭
	  if($("#id").val()){
		var query = {id:$("#id").val()};
		
	    $.ajax({
	    	type:"post",
	    	url:"/clothes/confirmId.do",
	    	data:query,
	    	success:function(data){
	    		var str1 = '<p id="ck">';
	    		var loc = data.indexOf(str1);
	    		var len = str1.length;
	    		var check = data.substr(loc+len,1);
	    		if(check == "1"){//사용할 수 없는 아이디
	    			alert("사용할 수 없는 아이디");
	    	    	$("#id").val("");
	    	     }else//사용할 수 있는 아이디
	    	  	    alert("사용할 수 있는 아이디");
	 	    }
	    });
	  }else{//아이디를 입력하지 않고 [ID중복확인]버튼을 클릭한 경우
		  alert("사용할 아이디를 입력");
		  $("#id").focus();
	  }
	});
	
	$("#process").click(function(){//[가입하기]버튼 클릭
	
		//이름 입력 확인
		if($('#name').length==0){
			alert("이름을 입력해주세요!");
			$('#name').focus();
			return false;
		}
		
		//이름 유효성 검사
		var name=document.getElementById("name").value;
		var reg=/^[가-힣]{1,5}$/;
		if(reg.test(name)==false){
			alert("5자이내 한글을 입력해주세요.")
			$('#name').focus();
			return false;
		}
		
		//아이디 입력 확인
		if($('#id').length==0){
			alert("아이디를 입력해 주세요!");
			return false;
		}
		
		//아이디(이메일) 유효성 검사
		var id = document.getElementById("id").value;
		var reg =/^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
		if(reg.test(id)==false){
			alert("이메일 형식이 올바르지 않습니다.")
			$('#id').focus();
			return false;
		}
	
		/* ..?? 어떻게 진행하지 이걸..?
		if(document.frm.reid.value.length==0){
			alert("아이디 중복검사를 진행해 주세요!")
			return false;
		}
		*/
		
		//비밀번호 입력 확인
		if($('#passwd').length==0){
			alert("비밀번호를 입력해주세요!");
			$('#passwd').focus();
			return false;
		}

		//비밀번호 일치 확인
		if($('#passwd').value!=$('#repass').value){
			alert("비밀번호가 일치하지 않습니다.비밀번호를 다시입력해 주세요!")
			$('#repass').focus();
			return false;
		}
		
		//비밀번호 유효성 검사
		var passwd=document.getElementById("passwd").value;
		var reg =  /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;
		if (reg.test(passwd)==false){
			alert("비밀번호 형식이 올바르지 않습니다.")
			$('#passwd').focus();
			return false;
		}
		
		//주소 입력 확인
		if($('#address').length==0){
			alert("주소를 입력해 주세요!");
			$('#address').focus();
			return false;
		}
		
		//전화번호 유효성 검사
		var reg=/^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;
		var tel=document.getElementById("tel").value;
		if(reg.test(tel)==false){
			alert("하이픈을 포함해주세요 ex)010-1111-1111")
			$('#tel').focus();
			return false;
		}
	
		  var query = {id:$("#id").val(), 
				  passwd:$("#passwd").val(),
			      name:$("#name").val(),
			      address:$("#address").val(),
			      tel:$("#tel").val(),
				  year:$("#year").val(),
				  month:$("#month").val(),
			      day:$("#day").val()};
		  
		  $.ajax({
		      type:"post",
		      url:"/clothes/registerPro.do",
		      data:query,
		      success:function(data){
		    	  window.location.href="/clothes/index.do";
		 	  }
		  });
	});
	
	
	$("#cancle").click(function(){//[취소]버튼 클릭
		window.location.href="/clothes/index.do";
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
