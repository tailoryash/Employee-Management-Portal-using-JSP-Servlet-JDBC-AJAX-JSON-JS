<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/profilecss.css">
<script src="js/script.js"></script>
</head>
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // http 1.1
	response.setHeader("Pragma", "no-cache"); // http 1.0
	response.setHeader("Expires", "0"); // proxies

	if (session.getAttribute("loginId") == null) {
		response.sendRedirect("login.jsp");
	}
	
	int id = Integer.parseInt(session.getAttribute("empId"));
	%>
	DashBoard

	<div class="card">
		<img src="#" alt="User Profile Photo" style="width: 100%">
		<h1 id="user-name"></h1>
		<p class="title"></p>
		<p>Promount Technologies LLP</p>
		<p>
			<a href="fetchUser" onclick="editEmployeeDetails()">
				<button type = "submit">Update Profile</button>
			</a>
		</p>
		<p>
			<a href="attachments.jsp"><button>Attachments</button></a>
		</p>
		<p>
			<a href="logout"><button>Log out</button></a>
		</p>
	</div>


	<script>

		var fullName = '<%=(String) session.getAttribute("loginId")%>';
		document.getElementById("user-name").innerText = fullName;
	</script>

</body>
</html>