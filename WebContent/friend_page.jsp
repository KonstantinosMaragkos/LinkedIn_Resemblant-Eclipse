<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="model.PdStatus" %>
<!DOCTYPE html>
<html>
<head>
<title>Profile</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="./css/friend_page.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
</head>
<body>
	
	<%
		if(session.getAttribute("username") == null) {
			response.sendRedirect("welcome_page.jsp");
		}
		
		PdStatus pds = new PdStatus();
		pds = (PdStatus) request.getAttribute("pds");
		String isAdmin = (String) request.getAttribute("isAdmin");
	%>
	
	<ul class="topnav">
		<li><i id="demo" class="fab fa-linkedin-in"></i></li>
		<li id="second"><a href="HomepageServlet" title="Homepage"><i class="fas fa-home icon"></i>Home</a></li>
		<li><a href="NetworkServlet" title="Network"><i class="fas fa-users icon"></i>Network</a></li>
		<li><a href="JobsPageServlet" title="Jobs"><i class="fas fa-user-tie icon"></i>Jobs</a></li>
		<li><a href="ConvServlet" title="Conversations"><i class="fas fa-comment icon"></i>Conversations</a></li>
		<li><a href="NotificationServlet" title="Notifications"><i class="fas fa-globe icon"></i>Notifications</a></li>
		<li><a href="ProfileServlet" class="active" title="Profile"><i class="fas fa-user-edit icon"></i>Profile</a></li>
		<li class="right"><a href="logout.jsp" title="Logout"><i class="fas fa-sign-out-alt icon"></i>Logout</a></li>
		<li class="right"><a href="settings_page.jsp" title="Settings"><i class="fas fa-cog icon"></i>Settings</a></li>
	</ul>
	
	<div class="container">
		<div class="user_container">
			<div class="column side">
				<div class="img_container">
					<img src="./css/Marco_the_phoenix_HB.jpg" alt="marco" class="pr_img">
				</div>
			</div>
		
			<div class="column content">
				<div id="user_info">
					<div class="name"><span>${f_name} ${l_name}</span></div>
					<div class="user_data"><i class="fas fa-mobile-alt"> Phone: </i><span> ${phone}</span></div>
					<div class="user_data last"><i class="fas fa-envelope"> Email: </i><span> ${email}</span></div>
				</div>
			</div>
			
			<div class="column add">
				<% int value = (int) request.getAttribute("status");
				if(value == -1) { %>
					<button type="button" class="addButton" onclick="addFriend()"><i class="fas fa-user-plus"></i> Add friend</button>
				<% } else if(value == 0) { %>
					<button type="button" class="addButton not"><i class="fas fa-check"></i> Friends</button>
					<button type="button" class="addButton" onclick="deleteFriend()"><i class="fas fa-times"></i> Delete friend</button>
				<% } else if(value == 1) { %>
					<button type="button" class="addButton not"><i class="fas fa-user-plus"></i></i> Pending</button>
				<% } else if(value == 2) { %>
					<button type="button" class="addButton" onclick="redirectNotif()"><i class="fas fa-user-plus"></i></i> This user has sent you a request!</button>
				<% } %>
			</div>
		</div>
		
		<div class="info_container">
			<h1>Public Information</h1>
			<%
				if(pds.getGEducation() == 0) {
			%>
				<h2>Education</h2>
			<% } %>
		
			<%
				if(pds.getUniversity_Status() == 0 || isAdmin.equals("1")){
			%>
				<div class="data"><b>University</b><pre>		${uni}</pre></div>
			<%
				} if(pds.getDepartment_Status() == 0 || isAdmin.equals("1")) {
			%>
				<div class="data"><b>Department</b><pre>	${dep}</pre></div>
				<div class="data" style="float:left"><b>From</b><pre>	${frm}</pre></div>
				<div class="data" style="margin-left: 130px;"><b>To</b>  <pre>	${to}</pre></div>
			<%
				} if(pds.getEducation_Status() == 0 || isAdmin.equals("1")) {
			%>
				<div class="data" style="margin-bottom:25px;"><b>Education Level</b><pre>	${edu}</pre></div>
			<% } %>
				
			<%
				if(pds.getGProfession() == 0 || isAdmin.equals("1")) {
			%>
				<hr><h2 style="padding-top: 10px;">Profession</h2>
			<% } %>
			
			<%
				if(pds.getTitle_Status() == 0 || isAdmin.equals("1")){
			%>
				<div class="data"><b>Title</b><pre>		${title}</pre></div>
			<%
				} if(pds.getCompany_Status() == 0 || isAdmin.equals("1")){
			%>
				<div class="data"><b>Company</b><pre>	${comp}</pre></div>
			<%
				} if (pds.getLocation_Status() == 0 || isAdmin.equals("1")) {
			%>
				<div class="data"><b>Location</b><pre>	${loc}</pre></div>
			<% } %>
		</div>
	</div>


	<script>
		function addFriend() {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if(this.readyState==4 && this.status==200){
					window.location = "FriendServlet?f_id=" + ${pds.getIdPersonal_Data()};
				}
			};
			xhttp.open("POST", "AddFriendServlet?f_id=" + ${pds.getIdPersonal_Data()}, true);
			xhttp.send();
		}
		function deleteFriend() {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if(this.readyState==4 && this.status==200){
					window.location = "FriendServlet?f_id=" + ${pds.getIdPersonal_Data()};
				}
			};
			xhttp.open("GET", "AddFriendServlet?f_id=" + ${pds.getIdPersonal_Data()}, true);
			xhttp.send();
		}
		
		function redirectNotif() {
			window.location = "NotificationServlet";
		}
	</script>
</body>
</html>