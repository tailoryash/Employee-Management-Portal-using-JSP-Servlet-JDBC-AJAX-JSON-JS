<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login Page
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
    <h2 class="title">Login to your account</h2>
    <form action="" id="formvalidate">
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

      <button type="submit" id="login">Login</button>
      <div class="clearfix supporter">
        <div class="pull-left remember-me">
          <input id="rememberMe" type="checkbox">
          <label for="rememberMe">Remember Me</label>
        </div>
        <!-- <a class="forgot pull-right" href="#">Forgot Password?</a> -->
      </div>
    </form>
  </div>
  <div class="signup-wrapper text-center">
    <a href="register.jsp">Don't have an account? <span class="text-primary">Create One</span></a>
  </div>
</div>

<!-- partial -->
  <script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.0/jquery.validate.min.js'></script><script  src="js/script.js"></script>

</body>
</html>