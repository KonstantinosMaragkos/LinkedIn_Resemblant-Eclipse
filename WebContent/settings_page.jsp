<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Settings</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<meta http-equiv='cache-control' content='no-cache'>
	<meta http-equiv='expires' content='0'>
	<meta http-equiv='pragma' content='no-cache'>
	
	<link rel="stylesheet" href="./css/settings_page.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
</head>
<body>

	<%
		if(session.getAttribute("username") == null) {
			response.sendRedirect("welcome_page.jsp");
		}
	%>

	<ul class="topnav">
		<li><i id="demo" class="fab fa-linkedin-in"></i></li>
		<li id="second"><a href="HomepageServlet" title="Homepage"><i class="fas fa-home icon"></i>Home</a></li>
		<li><a href="NetworkServlet" title="Network"><i class="fas fa-users icon"></i>Network</a></li>
		<li><a href="JobsPageServlet" title="Jobs"><i class="fas fa-user-tie icon"></i>Jobs</a></li>
		<li><a href="ConvServlet" title="Conversations"><i class="fas fa-comment icon"></i>Conversations</a></li>
		<li><a href="NotificationServlet" title="Notifications"><i class="fas fa-globe icon"></i>Notifications</a></li>
		<li><a href="ProfileServlet" title="Profile"><i class="fas fa-user-edit icon"></i>Profile</a></li>
		<li class="right"><a href="logout.jsp" title="Logout"><i class="fas fa-sign-out-alt icon"></i>Logout</a></li>
		<li class="right"><a href="settings_page.jsp" class="active" title="Settings"><i class="fas fa-cog icon"></i>Settings</a></li>
	</ul>
	
	<div class="container">
		<form class="myForm" action="SettingsServlet" method="post" onsubmit="return checkValues()">
			<div class="head">
			<i class="fas fa-user" id="pr_icon"></i><p>Change Email / Password</p>
			</div>
			
			<p>New E-mail</p>
			<input type="text" id="new_email" name="new_email" placeholder="Enter New E-mail" maxlength="50">
			
			<p>New Password</p>
			<input type="password" id="pass" name="new_pass" placeholder="Enter New Password" maxlength="50">
			<p>Confirm Password</p>
			<input type="password" id="pass2" name="new_pass2" placeholder="Confirm Password" maxlength="50">
			<br><input type="checkbox" id="check_box" name="exp" value="no" onmousedown="showPass()" onmouseup="hidePass()" onclick="fixCheck()"> Show passwords<br><br>
			
			<input type="submit" value="Submit">
			<div id="demo1" class="warning" style="display:none">Passwords must match!</div>
			<div id="demo2" class="warning" style="display:none">Fill in all the fields!</div>
			
			<%
				if(session.getAttribute("input") == "no") {
			%> 
					<div class="warning">Fill in all the fields!</div>
			<%  }
				if(session.getAttribute("valid") == "no") {
			%>
					<div class="warning">Passwords must match!</div>
			<%
				}
				if(session.getAttribute("exist") == "no") {
			%>
					<div class="warning">Please select New Email!</div>
			<%
				}
				if(session.getAttribute("updated") == "yes") {
			%>
					<div class="ok_message">Changes were applied successfully!</div>
			<% } %>
			
		</form>
	</div>
	

	<script>
	function checkValues() {
		//check fields
		if(!checkFields()){
			var element = document.getElementById("demo2");
			element.style.display = "block";
	   		element.scrollIntoView(false);
	   		
	   		document.getElementById("new_email").className = "error";
	   		document.getElementById("pass").className = "error";
	   		document.getElementById("pass2").className = "error";
	   		return false;
		}
		else{
			var element = document.getElementById("demo2");
			element.style.display = "none";
	   		
			document.getElementById("new_email").className = "";
	   		document.getElementById("pass").className = "";
	   		document.getElementById("pass2").className = "";
		}
		//check passwords
		if(!checkPasswords()){
			var element = document.getElementById("demo1");
			//element.innerHTML = "Passwords must match!";
			element.style.display = "block";
	   		element.scrollIntoView(false);
	   		
	   		document.getElementById("pass").className = "error";
	   		document.getElementById("pass2").className = "error";
	   		return false;
		}
		else{
			document.getElementById("demo1").style.display = "none";
			document.getElementById("demo1").innerHTML = "";
			document.getElementById("pass").className = "";
	   		document.getElementById("pass2").className = "";
		}
	}
	
	function checkFields() {
		var strEmail = document.getElementById("new_email").value;
		var strPass = document.getElementById("pass").value;
		var strPass2 = document.getElementById("pass2").value;
		if(strEmail == "" || strPass == "" || strPass2 == "")
			{ return false; }
		else
			{ return true; }
	}
	
	function checkPasswords() {
		var strPass = document.getElementById("pass").value;
		var strPass2 = document.getElementById("pass2").value;
		if(strPass != strPass2 || (strPass=="") || (strPass2==""))
			{ return false; }
		else
			{ return true; }
	}
	
	function showPass() {
		document.getElementById("pass").type = "text";
   		document.getElementById("pass2").type = "text";
	}
	
	function hidePass() {
		document.getElementById("pass").type = "password";
   		document.getElementById("pass2").type = "password";
	}
	
	function fixCheck(){
		document.getElementById("check_box").checked = false;
	}
	</script>

</body>
</html>