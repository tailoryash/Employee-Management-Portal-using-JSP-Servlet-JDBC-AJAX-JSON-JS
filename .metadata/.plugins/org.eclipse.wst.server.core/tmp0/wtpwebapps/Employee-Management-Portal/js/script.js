+ function($) {
  $('.palceholder').click(function() {
    $(this).siblings('input').focus();
  });

  $('.form-control').focus(function() {
    $(this).parent().addClass("focused");
  });

  $('.form-control').blur(function() {
    var $this = $(this);
    if ($this.val().length == 0)
      $(this).parent().removeClass("focused");
  });
  $('.form-control').blur();

  // validetion
  $.validator.setDefaults({
    errorElement: 'span',
    errorClass: 'validate-tooltip'
  });

  $("#formvalidate").validate({
    rules: {
      userName: {
        required: true,
        minlength: 6
      },
      userPassword: {
        required: true,
        minlength: 6
      }
    },
    messages: {
      userName: {
        required: "Please enter your username.",
        minlength: "Please provide valid username."
      },
      userPassword: {
        required: "Enter your password to Login.",
        minlength: "Incorrect login or password."
      }
    }
  });

}(jQuery);

function checkPassword(){
    let password = document.getElementById("userPassword").value;
    let cnfrmPassword = document.getElementById("confirmUserPassword").value;
    let message = document.getElementById("message");
   // console.log(" Password:", password,'\n',"Confirm Password:",cnfrmPassword);
    if(password.length != 0){
        if(password == cnfrmPassword){
            message.innerText = "password matched";
            message.style.backgroundColor = "#1dcd59";
            fetchDataFromFormToJson();
        }
        else{
            message.innerText = "Sorry, Password match doesn't matching";
            message.style.backgroundColor = "#ff4d4d";
            window.location.replace("register.jsp");
        }
    }
    else{
        message.innerText = "Sorry password can't be empty";
        window.location.replace("register.jsp");
    }
    
    
}


async function fetchDataFromFormToJson(){
	 
	/*var form = document.getElementById('formvalidate');
	
	var formData = new FormData(form);*/
	
	var formData = new FormData($("#formvalidate"));
	formData.append("profile-photo", profile-photo.files[0]);
	
	await fetch('validateUser', {
		method: "POST",
		body:formData
	});
	
	/*$.ajax({
		url: "/validateUser",
		type: "POST",
		data: formData
	})*/
	
	
	 /*var object = {};
  	 
  	 formData.forEach((value, key) => object[key] = value);
  	 
  	 var json = JSON.stringify(object);
  	 
  	 fetch('/validateUser', {
	  method: 'POST',
	  body: json,
	  headers: {
	    'Content-Type': 'application/json'
	  }
	}).then((response) => {
  	 	console.log(json);
	  // handle the response from the servlet
	});*/
  	 
}
