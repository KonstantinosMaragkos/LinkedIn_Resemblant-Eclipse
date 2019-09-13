<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Network</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="./css/network_page.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
</head>
<body onload="loadFriends()">

	<%
		if(session.getAttribute("username") == null) {
			response.sendRedirect("welcome_page.jsp");
		}
	%>


	<ul class="topnav">
		<li><i id="demo" class="fab fa-linkedin-in"></i></li>
		<li id="second"><a href="HomepageServlet" title="Homepage"><i class="fas fa-home icon"></i>Home</a></li>
		<li><a href="NetworkServlet" class="active" title="Network"><i class="fas fa-users icon"></i>Network</a></li>
		<li><a href="JobsPageServlet" title="Jobs"><i class="fas fa-user-tie icon"></i>Jobs</a></li>
		<li><a href="ConvServlet" title="Conversations"><i class="fas fa-comment icon"></i>Conversations</a></li>
		<li><a href="NotificationServlet" title="Notifications"><i class="fas fa-globe icon"></i>Notifications</a></li>
		<li><a href="ProfileServlet" title="Profile"><i class="fas fa-user-edit icon"></i>Profile</a></li>
		<li class="right"><a href="logout.jsp" title="Logout"><i class="fas fa-sign-out-alt icon"></i>Logout</a></li>
		<li class="right"><a href="settings_page.jsp" title="Settings"><i class="fas fa-cog icon"></i>Settings</a></li>
	</ul>
	
	<div class="top_container">
		<div class="searchbar">
			<form>
				<input type="text" name="input_txt" onkeyup="showHint(this.value)" placeholder="Search friends..">
				<button type='button' class="search_icon" onclick="clickSearch()"><i class="fas fa-search"></i></button>
			</form>
		</div>
	</div>
	
	<div id="results">
		<div id="hint"></div>
	</div>
	
	<div id="friend_container">
	</div>
	

	<script>
		function showHint(str){
			//ajax
			var xhttp = new XMLHttpRequest();
			
			//clear whitespace at the beginning
			while(str.indexOf(' ') == 0){
				str = str.replace(/\s/, '');
			}
			if(str == ""){
				var x = document.getElementById("hint");
				x.innerHTML = "";
				x.style.display = "none";
				
				return;
			}
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {
					//document.getElementById("hint").innerHTML = this.responseText;
					var array = JSON.parse(this.responseText);
					extract(array);
			    }
			};
			xhttp.open("POST", "SearchServlet?fname=" + str, true);
			xhttp.send();
		}
		
		function extract(array) {
			//show results - json
			var out = "";
			if(array.length > 0){
				for(var i=0; i<array.length; i++){
					out += "<a href='FriendServlet?f_id=" + array[i].user_id + "' id='result" + i + "'>" + array[i].first_Name + " " + array[i].last_Name + "</a>";
				}
				var x = document.getElementById("hint");
				x.style.display = "block";
				x.innerHTML = out;
			}
			else {
				var x = document.getElementById("hint");
				x.innerHTML = "";
				x.style.display = "none";
			}
		}
		
		function clickSearch(){
			var x = document.getElementById("result0");
			if(x != null){
				x.click();
			}
		}
		
		function loadFriends(){
			var out = "<div class='g_head'><h1>Friends List</h1></div>";
			var str = ${flist} ;
			var ph = ${photos};
			
			for(var i=0; i<str.length; i++){
				out += "<div class='card'><img class='pr_img' src='" + ph[i] + "' alt='"+ str[i].first_Name +
					"'> <div id='user" + i + "' class='info_friend'><b class='pr_name'>" + str[i].first_Name + " " + str[i].last_Name + 
					"</b><br><i class='fas fa-envelope'>Email: </i><span> " + str[i].e_Mail + "</span></div>" + 
					"<div class='footer'><a id='lnk1' href='FriendServlet?f_id=" + str[i].user_id + "'>Visit profile</a>" + 
					"<a id='lnk2' href='sendMessage?user=" + str[i].user_id +"'>Send message</a></div></div>";
			}
			document.getElementById("friend_container").innerHTML = out;
		}
	</script>
</body>
</html>