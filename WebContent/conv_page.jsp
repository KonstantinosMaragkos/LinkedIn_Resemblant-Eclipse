<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

	if(session.getAttribute("username") == null) {
		response.sendRedirect("welcome_page.jsp");
	}
	int active = (int) session.getAttribute("active");
	int user_id = (int) session.getAttribute("user_id");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Conversations</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="./css/conv_page.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
	<script type="text/javascript" src="./js/chat.js"></script>
</head>
<script>
	setInterval(function() {
		reloadChatList();
		reloadHeader();
		reloadMessages();
	}, 2000);
</script>
<body>
	<!-- NAVIGATION BAR -->
	<ul class="topnav">
		<li><i id="demo" class="fab fa-linkedin-in"></i></li>
		<li id="second"><a href="HomepageServlet" title="Homepage"><i class="fas fa-home icon"></i>Home</a></li>
		<li><a href="NetworkServlet" title="Network"><i class="fas fa-users icon"></i>Network</a></li>
		<li><a href="JobsPageServlet" title="Jobs"><i class="fas fa-user-tie icon"></i>Jobs</a></li>
		<li><a href="ConvServlet" class="active" title="Conversations"><i class="fas fa-comment icon"></i>Conversations</a></li>
		<li><a href="NotificationServlet" title="Notifications"><i class="fas fa-globe icon"></i>Notifications</a></li>
		<li><a href="ProfileServlet" title="Profile"><i class="fas fa-user-edit icon"></i>Profile</a></li>
		<li class="right"><a href="logout.jsp" title="Logout"><i class="fas fa-sign-out-alt icon"></i>Logout</a></li>
		<li class="right"><a href="settings_page.jsp" title="Settings"><i class="fas fa-cog icon"></i>Settings</a></li>
	</ul>
	
	<!-- MAIN AREA -->
	<div class="container">
		<div class="head-area">
			
			<div class="head-right">
				<div class="head-right-sub">
				</div>
			</div>
		</div>

		<div class="body-area">
			<!-- CHAT LIST -->
			<div class="body-left">
				<ul id="chatList">
				</ul>
			</div>
			
			<!-- MESSAGE BOX -->
			<div class="body-right">
				<div class="message" id="message">
					<ul id="Chat">
					</ul>
				</div>
				<!-- MESSAGE FORM -->
				<div class="right-area-bottom">
						<input type="text" style="display:none" id="a" value="${active}">
						<input type="text" style="display:none" id="u" value="${user_id}">
						<%if (active == -1) {
							%><input type="text" id="h" placeholder="type here..." disabled>
						<%} if (active > 0) {
							%><input type="text" id="h" placeholder="type here...">
						<% } %>
						<button class="btn-send" onclick="addText()"><i class="fas fa-paper-plane"></i></i></button>
				</div>
			</div>
		</div>
	</div>	

</body>
</html>