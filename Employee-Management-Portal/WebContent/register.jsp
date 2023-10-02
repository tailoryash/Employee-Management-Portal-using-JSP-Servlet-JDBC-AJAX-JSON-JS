<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <link rel="icon" type="image/x-icon" href="images/favicon/register.png">
  <meta charset="UTF-8">
  <title>SignUp
  </title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet" href="css/style.css">
	<script>
	
	var id = <%=(Integer)session.getAttribute("empId")%>;
	window.addEventListener("DOMContentLoaded", function() {   
		var xhr = new XMLHttpRequest();
		xhr.open("GET", "fetchUser?id="+id, true);
		xhr.onreadystatechange = function(){
			if (xhr.readyState == 4 && xhr.status == 200) {
		
				 var employee = JSON.parse(xhr.responseText);
				// Set the employee data to the input tag
				console.log("Employee json : " + xhr.responseText);
				
				document.getElementById("fullName").value = employee.fullName;
				document.getElementById("phone").value = employee.phone;
				document.getElementById("tech").value = employee.tech;
				document.getElementById("userName").value = employee.userName;
			}                            
		};
		
		 xhr.send(); 
		
	});
	
	</script>

</head>
<body>
	<script src="js/script.js"></script>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // http 1.1
response.setHeader("Pragma", "no-cache"); // http 1.0
response.setHeader("Expires", "0");

%>
<div class="logo text-center">
  <h1>Employee Management Portal</h1>
</div>
<div class="wrapper">
  <div class="inner-warpper text-center">
    <form id="formvalidate" action = "validateUser" method = "post" enctype="multipart/form-data">
    <div class="input-group">
        <label class="palceholder" for="fullName">Full Name</label>
        <input class="form-control" name="fullName" id="fullName" type="text" placeholder="" />
        <span class="lighting"></span>
      </div>
      <div class="input-group">
        <label class="palceholder" for="phone">Phone no.</label>
        <input class="form-control" name="phone" id="phone" type="number" placeholder="" />
        <span class="lighting"></span>
      </div>
      <div class="input-group">
        <label class="palceholder" for="tech">Tech Stack</label>
        <input class="form-control" name="tech" id="tech" type="text" placeholder="" />
        <span class="lighting"></span>
      </div>
       <div class="input-group">
        <label class="palceholder" for="profile-photo">Profile Photo</label>
        <input class="form-control" name="profile-photo" id="profile-photo" type="file" placeholder="" accept="application/pdf, image/x-png,image/gif,image/jpeg" />
     <span class="lighting"></span>
      </div>
      <div class="input-group">
        <label class="palceholder" for="userName">User Name</label>
        <input class="form-control" name="userName" id="userName" type="text" placeholder="" />
        <span class="lighting"></span>
      </div>
      <div class="input-group">
        <label class="palceholder" for="userPassword">Password</label>
        <input class="form-control" name="userPassword" id="userPassword" type="password" placeholder="" />
        <span class="lighting"></span>
      </div>
		<div class="input-group">
        <label class="palceholder" for="confirmUserPassword">Confirm Password</label>
        <input class="form-control" name="confirmUserPassword" id="confirmUserPassword" type="password" placeholder="" />
        <span class="lighting"></span>
    	</div>
    	<div>
    		<p id="message"></p>
    	</div>
      <button type="submit" onfocus = "checkPassword()" onclick="fetchDataFromFormToJson()" id="login">Submit</button>
    </form>
  </div>
  <div class="signup-wrapper text-center">
			<a href="login.jsp">Already user? <span class="text-primary">Login
					here</span></a>
		</div>
</div>

<!-- partial -->
  <script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.0/jquery.validate.min.js'></script><script  src="js/script.js"></script>
		
	<script>
	var id = <%=(Integer)session.getAttribute("empId")%>;
	if(<%=(Integer)session.getAttribute("empId")%> != null){
		document.getElementById("formvalidate").action = "updateUser?id="+id;
	}
	
	window.history.pushState(null, null, window.location.href);
	window.onpopstate = function () {
	    window.history.pushState(null, null, window.location.href);
	};
	</script>
</body>
</html>