<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html lang="en">
	<head>
		<title>WELCOME</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="./css/welcome_page.css">
	</head>
	
	<body>
		<div class="header">
			<h2>MyLinked<span>in</span></h2>
		</div>
		<div class="container">
			<div class="box">
				<h1>Sign In</h1>
				<form action="LoginServlet" method="post">
					<%
						if(session.getAttribute("input") == "no") {
					%> 
							<p class="warning" style="color:#FA8072">no username or password given!</p>
					<%  }
						if(session.getAttribute("valid") == "no") {
					%>
							<p class="warning" style="color:#FA8072">Wrong username or password!</p>
					<%
						}
					%>
					<p>Username</p>
					<input type="text" name="user" placeholder="Enter E-Mail">
					<p>Password</p>
					<input type="password" name="pass" placeholder="Enter Password">
					<input type="submit" value="Log In">
				</form>
			</div>
			<div class="box">
				<h1>Sign Up</h1>
				<form action="SignupServlet" method="post">
				<%
					if(session.getAttribute("exist") == "no") {
				%>
						<p class="warning" style="color:#FA8072">E-Mail is already in use!</p>
				<% 
					} else if(session.getAttribute("input_f") == "no") {
				%>
					<p class="warning" style="color:#FA8072">Please give an E-Mail!</p>
				<% } %>
					<p>E-Mail</p>
					<input type="email" name="user" placeholder="Enter New E-Mail">
					<input type="submit" value="Register">
				</form>
			</div>
		</div>
	</body>

</html>