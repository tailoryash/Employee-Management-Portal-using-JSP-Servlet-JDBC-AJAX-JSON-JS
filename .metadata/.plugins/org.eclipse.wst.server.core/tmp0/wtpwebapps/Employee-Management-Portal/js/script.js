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


function checkPassword() {
	let password = document.getElementById("userPassword").value;
	let cnfrmPassword = document.getElementById("confirmUserPassword").value;
	let message = document.getElementById("message");
	// console.log(" Password:", password,'\n',"Confirm Password:",cnfrmPassword);
	if (password.length != 0) {
		if (password == cnfrmPassword) {
			message.innerText = "password matched";
			message.style.backgroundColor = "#1dcd59";
			fetchDataFromFormToJson();
		}
		else {
			message.innerText = "Sorry, Password match doesn't matching";
			message.style.backgroundColor = "#ff4d4d";
		}
	}
	else {
		message.innerText = "Sorry password can't be empty";
	}
}


async function fetchDataFromFormToJson() {

	/*var form = document.getElementById('formvalidate');
	
	var formData = new FormData(form);*/

	var formData = new FormData($("#formvalidate"));
	formData.append("profile-photo", profile - photo.files[0]);

	await fetch('validateUser', {
		method: "POST",
		body: formData,
		success: document.forms["formvalidate"].reset(),
	}).status

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


function editEmployeeDetails(id) {
	/*$.ajax({
		type: 'GET',
		url: "/Employee-Management-Portal/fetchUser",
		cache: false,
		success: function(response) {
			var data = JSON.parse(response);
			$("#fullName").val(data.fullName);
			$("#phone").val(data.phone);
			$("#tech").val(data.tech);
			$("#profile-photo").val(data.profilePhotoUrl);
			$("#userName").val(data.userName);
			$("#userPassword").val(data.password);
			$("#confirmUserPassword").val(data.confirmPassword);
		},
		error: function() {
		}
	});*/

	/*fetch('http://localhost:8080/Employee-Management-Portal/fetchUser', {
		method: 'GET',
	})
		.then(response => response.json())
		.then(response => {
			var data = JSON.stringify(response);
			console.log(data);
			$("#fullName").val(data.fullName);
			$("#phone").val(data.phone);
			$("#tech").val(data.tech);
			$("#profile-photo").val(data.profilePhotoUrl);
			$("#userName").val(data.userName);
			$("#userPassword").val(data.password);
			$("#confirmUserPassword").val(data.confirmPassword);
		});*/


	var xhr = new XMLHttpRequest();
	// xhr.responseType = "json";
	xhr.open("GET", "http://localhost:8080/Employee-Management-Portal/register.jsp?id="+);
	var employee = JSON.parse(xhr.response);
	console.log(employee);
	xhr.onreadystatechange = function(){
		if (xhr.readyState == 4 && xhr.status == 200) {
			// Set the employee data to the input tag
			document.getElementById("fullName").value = employee.fullName;
			document.getElementById("phone").value = employee.phone;
			document.getElementById("tech").value = employee.tech;
		}
	};

	xhr.send();
}