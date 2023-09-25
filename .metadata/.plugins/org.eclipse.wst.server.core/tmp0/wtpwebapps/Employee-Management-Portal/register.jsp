<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>SignUp page
  </title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
<link rel="stylesheet" href="css/style.css">

</head>
<body>
<div class="logo text-center">
  <h1>Employee Management Portal</h1>
</div>
<div class="wrapper">
  <div class="inner-warpper text-center">
    <form action="validateUser" id="formvalidate" method="post" enctype="multipart/form-data">
    <div class="input-group">
        <label class="palceholder" for="fullName">Full Name</label>
        <input class="form-control" name="fullName" id="fullName" type="text" placeholder="" />
        <span class="lighting"></span>
      </div>
      <div class="input-group">
        <label class="palceholder" for="phoneNo">Phone no.</label>
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
        <input class="form-control" name="profile-photo" id="profile-photo" type="file" placeholder="" accept="image/x-png,image/gif,image/jpeg" />
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
      <button type="submit" onclick = "checkPassword()" id="login">Submit</button>
    </form>
  </div>
  <div class="signup-wrapper text-center">
    <a href="login.jsp">Already user? <span class="text-primary">Login here</span></a>
  </div>
</div>

<!-- partial -->
  <script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.0/jquery.validate.min.js'></script><script  src="js/script.js"></script>

</body>
</html>