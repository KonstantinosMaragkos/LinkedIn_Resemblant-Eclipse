<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Review Applications</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="./css/review_apps.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
</head>
<body onload="loadPage()">

	<%
		int flag = (int) request.getAttribute("conf_flag");
		if(flag != 1){
			if(session.getAttribute("username") == null) {
				response.sendRedirect("welcome_page.jsp");
			}
			else {
				response.sendRedirect("JobsPageServlet");
			}
		}
	%>
	
	<ul class="topnav">
		<li><i id="demo" class="fab fa-linkedin-in"></i></li>
		<li id="second"><a href="HomepageServlet" title="Homepage"><i class="fas fa-home icon"></i>Home</a></li>
		<li><a href="NetworkServlet" title="Network"><i class="fas fa-users icon"></i>Network</a></li>
		<li><a href="JobsPageServlet" title="Jobs" class="active"><i class="fas fa-user-tie icon"></i>Jobs</a></li>
		<li><a href="ConvServlet" title="Conversations"><i class="fas fa-comment icon"></i>Conversations</a></li>
		<li><a href="NotificationServlet" title="Notifications"><i class="fas fa-globe icon"></i>Notifications</a></li>
		<li><a href="ProfileServlet" title="Profile"><i class="fas fa-user-edit icon"></i>Profile</a></li>
		<li class="right"><a href="logout.jsp" title="Logout"><i class="fas fa-sign-out-alt icon"></i>Logout</a></li>
		<li class="right"><a href="settings_page.jsp" title="Settings"><i class="fas fa-cog icon"></i>Settings</a></li>
	</ul>
	
	<div id="results_cont">
		<h2 class="gen_header">Your job offers</h2>
		
	</div>

	<script>
		function loadPage(){
			var out;
			var agg = ${aggelies};
			var ait = ${aitiseis};
			var count = ${app_count};
			var names = ${names};
			
			var index=0;
			for(var i=0; i<agg.length; i++){
				out = "";
				var node = document.createElement("DIV");
				node.className = "aggelia";
				node.id = "aggelia" + i;
				out += "<h3 class='field_head'>" + (i+1) + ") Title: </h3> <span class='ag_title'>" + agg[i].title
					+ "</span>";
					out += "<h3 class='field_head'> Job Description:</h3><p class='ag_desc'>" + agg[i].description
					+ "</p>";
				out += "<br><h3 class='field_head type2'>Applications</h3><ul id='ul" +i+ "' class='app_list'>";
				
				for(var k=0; k<count[i]; k++){
					out += "<li id='li" + index +"'>" + names[index] + "<a href='FriendServlet?f_id=" + ait[index].user_id 
						+ "'class='view_friend'>View Profile</a><span class='decline_bttn' onclick='manageApps(1, " +ait[index].idAitisi+ ", " +index+ ", " +i+ ")'>Decline <i class='fas fa-times'></i></span><span class='accept_bttn' onclick='manageApps(0, " +ait[index].idAitisi+ ", " +index+ ", " +i+ ")'>Accept <i class='fas fa-check'></i></span></li>";
					index++;
				}
				node.innerHTML = out;
				//console.log(node.innerHTML);
				document.getElementById("results_cont").appendChild(node);
			}
		}
		function manageApps(status, id_aitisi , index, i){
			var xhttp = new XMLHttpRequest();
			
			xhttp.onreadystatechange = function() {
				if(this.readyState==4 && this.status==200){
					var x = document.getElementById("li" + index);
					var par = document.getElementById("ul" + i);
					par.removeChild(x);
					if(status == 0){
						window.alert("Accepted applicant");
					}
					else{
						window.alert("Declined applicant");
					}
				}
			}
			
			xhttp.open("POST", "ManageAppServlet?id=" + id_aitisi + "&status=" + status, true);
			xhttp.send();
		}
	</script>
</body>
</html>