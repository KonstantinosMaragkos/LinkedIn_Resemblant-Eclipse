<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<html lang="en">
	<head>
		<title>WELCOME</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<meta http-equiv='cache-control' content='no-cache'>
		<meta http-equiv='expires' content='0'>
		<meta http-equiv='pragma' content='no-cache'>
		
		<link rel="stylesheet" href="./css/signup_page.css">
	</head>
	
	<body>
		<%
			if(session.getAttribute("email") == null) {
				response.sendRedirect("welcome_page.jsp");
			}
			else if(session.getAttribute("opened_signup") == "yes") {
				response.sendRedirect("welcome_page.jsp");
			}
		%>
		
		<div class="header">
			<h2>MyLinked<span>in</span></h2>
		</div>
		<div class="container">
			<div class="box">
				<h1>Sign Up</h1>
				<form name="form1" action="SignupServlet2" method="post" onsubmit="return checkValues()" enctype="multipart/form-data">
					<%
						if(session.getAttribute("field") == "no") {
					%>
							<p class="warning" style="color:#FA8072">*fill in all the fields*</p>
					<% 
						} else if(session.getAttribute("pass_flag") == "no") {
					%>
							<p class="warning" style="color:#FA8072">passwords must match!</p>
					<% } %>
					
					<p>E-Mail</p>
					<input type="email" id="user" name="user" placeholder="Enter E-Mail" value="${email}" readonly>
					<p>Password</p>
					<div id="demo1" style="color:#FA8072;font-style:italic;font-size:85%;text-align:left;margin:3vh;display:none">Passwords must match!</div>
					<input type="password" id="pass" name="pass" placeholder="Enter Password">
					<p>Confirm</p>
					<input type="password" id="pass2" name="pass2" placeholder="Confirm Password">
					<p>First Name</p>
					<input type="text" id="name" name="name" placeholder="Enter Name">
					<p>Last Name</p>
					<input type="text" id="lname" name="lname" placeholder="Enter Last Name">
					<p>Phone number</p>
					<input type="text" id="phone" name="phone" placeholder="Enter Phone Number">
					<p>Photo</p>
					<div id="demo2" style="color:#FA8072;font-style:italic;font-size:85%;text-align:left;margin:3vh;display:none">Please select a photo!</div>
					<input type="file" id="photo" name="photo" accept="image/*">
					<input type="submit" value="Register">
				</form>
			</div>
		</div>
		
		<script>
		function checkValues()
		{
			if(!checkPasswords()){
				var element = document.getElementById("demo1");
				//element.innerHTML = "Passwords must match!";
				element.style.display = "block";
		   		element.scrollIntoView(false);
		   		return false;
			}
			else{
				document.getElementById("demo1").style.display = "none";
			}
			if(!checkPhoto()){
				var element = document.getElementById("demo2");
		   		//element.innerHTML = "Please select a photo!";
		   		element.style.display = "block";
		   		element.scrollIntoView();
		   		return false;
			}
			else{
				document.getElementById("demo2").style.display = "none";
			}
		}
		function checkPhoto()
		{
			var fname = document.forms["form1"]["photo"].value;
		    if(fname == ""){
		    	return true;
		    }
		    var array = fname.split(".");
		    var extension = array[array.length - 1];
		   	if (extension == "png" || extension == "jpeg" || extension == "jpg")
				{ return true; }
		   	else
		   		{ return false; }
		}
		function checkPasswords() {
			var strPass = document.getElementById("pass").value;
			var strPass2 = document.getElementById("pass2").value;
			if(strPass == strPass2 || (strPass=="") || (strPass2==""))
				{ return true; }
			else
				{ return false; }
		}
		</script>
	</body>

</html>