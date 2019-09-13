<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Jobs</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="./css/jobs_page.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
</head>
<body onload="loadPage()">

	<%
		if(session.getAttribute("username") == null) {
			response.sendRedirect("welcome_page.jsp");
		}
	%>

	<ul class="topnav">
		<li><i id="demo" class="fab fa-linkedin-in"></i></li>
		<li id="second"><a href="HomepageServlet" title="Homepage"><i class="fas fa-home icon"></i>Home</a></li>
		<li><a href="NetworkServlet" title="Network"><i class="fas fa-users icon"></i>Network</a></li>
		<li><a href="JobsPageServlet" class="active" title="Jobs"><i class="fas fa-user-tie icon"></i>Jobs</a></li>
		<li><a href="ConvServlet" title="Conversations"><i class="fas fa-comment icon"></i>Conversations</a></li>
		<li><a href="NotificationServlet" title="Notifications"><i class="fas fa-globe icon"></i>Notifications</a></li>
		<li><a href="ProfileServlet" title="Profile"><i class="fas fa-user-edit icon"></i>Profile</a></li>
		<li class="right"><a href="logout.jsp" title="Logout"><i class="fas fa-sign-out-alt icon"></i>Logout</a></li>
		<li class="right"><a href="settings_page.jsp" title="Settings"><i class="fas fa-cog icon"></i>Settings</a></li>
	</ul>
	
	<div id="editModal" class="modal">
  		<div class="modal-content">
   		 	<span class="close">&times;</span>
   		 	<form name="form1" id="form1" action="UploadJobServlet" method="post" onsubmit="return submitFunc()">
   		 		<h2><i class="fas fa-pen"></i> Write job details</h2>
   		 		<p>- Job Title</p>
   		 		<input class="form_field" type="text" name="title" maxlength="100">
   		 		<p><i class="fas fa-info-circle"></i>Description</p>
   		 		<textarea class="form_field" form="form1" name="description" rows="4" maxlength="1900"></textarea>
   		 		<p><i class="far fa-check-circle"></i> Requirements</p>
   		 		<textarea class="form_field" form="form1" name="requirements" rows="4" maxlength="1900"></textarea>
   		 		<p><i class="fas fa-medal"></i>Desired skills</p>
   		 		<textarea class="form_field" form="form1" name="desired" rows="4" maxlength="1900"></textarea>
   		 		<p><i class="fas fa-map-marker-alt"></i>Location</p>
   		 		<input class="form_field" type="text" name="location" maxlength="100">
   		 		<p><i class="fas fa-book-open"></i>Workload</p>
   		 		<input class="form_field" type="text" name="workload" maxlength="100">
   		 		
   		 		<input type="submit" value="Submit"><br>
   		 	</form>
   		 </div>
   	</div>
	
	<div class="container">
		<h1 class="cont_header">Job Opportunities</h1><br>
		<div class="bttn_cont">
			<a href="#" id="bttn1" class="general make_bttn"">Make a new Job entry!</a>
			<a href="RevJobAppsServlet" id="bttn2" class="general manage_bttn">Review applications</a>
		</div>
		<br><hr><br>
		
		<div id="results_cont">
		</div>
		
	</div>

	<script>
		var modal = document.getElementById('editModal');
		var bttn = document.getElementById('bttn1');
		var span = document.getElementsByClassName("close")[0];
		bttn.onclick = function(){
		 	modal.style.display = "block";
		}
		span.onclick = function() {
	    	modal.style.display = "none";
		}
		window.ondblclick = function(event) {
	    	if (event.target == modal) {
	        	modal.style.display = "none";
	    	}
		}
		
		function submitFunc() {
			var x = document.getElementsByClassName("form_field");
			for(var i=0; i<x.length; i++){
				if(x[i].value == ""){
					alert("Please fill in all the blanks!");
					return false;
				}
			}
		}
		function loadPage(){
			var out;
			var fr = ${flist};
			var ph = ${photos};
			var str = ${aggelies};
			
			for(var i=0; i<str.length; i++){
				out = "";
				var node = document.createElement("DIV");
				node.className = "aggelia";
				node.id = "aggelia" + i;
				out += "<p class='ag_user'><img src='" + ph[i] + "' alt='photo' class='ag_photo'> " + fr[i].first_Name + " " + fr[i].last_Name + "</p><br>";
				out += "<h4 class='field_head first_head' style='margin-bottom:8px;'>Title: <span class='ag_title'>" +str[i].title + "</span></h4>";
				out += "<h4 class='field_head'><i class='fas fa-info-circle'></i> Job Description:</h4><p class='ag_text'>" + str[i].description
					+ "</p>";
				out += "<p class='showBtnn' id='showBttn" + i + "' onclick='showMore("+ i +")'><i class='fas fa-caret-down'></i>Show More</p>";
				out += "<div class='more_cont' id='more_cont" + i + "'></div>";
				
				node.innerHTML = out;
				document.getElementById("results_cont").appendChild(node);
			}
		}
		
		function showMore(i){
			//clear others
			var other = document.getElementsByClassName("more_cont");
			for(var j=0; j<other.length; j++){
				if(other[j].innerHTML != ""){
					other[j].innerHTML = "";
					document.getElementById("showBttn" + j).style.display = "block";
				}
			}
			
			//show current
			var b = document.getElementById("showBttn" + i);
			b.style.display = "none";
			var node = document.getElementById("more_cont" + i);
			
			var x = document.getElementById("aggelia" + i);
			x.scrollIntoView();
			var str = ${aggelies};
			
			var out = "<h4 class='field_head'><i class='far fa-check-circle'></i> Requirements:</h4><p class='ag_text'>" + str[i].requirements
			+ "</p>";
			var out1 = "<h4 class='field_head'><i class='fas fa-medal'></i> Desired Skills:</h4><p class='ag_text'>" + str[i].desired
			+ "</p>";
			var out2 = "<h4 class='field_head'><i class='fas fa-map-marker-alt'></i> Location:</h4><p class='ag_text'>" + str[i].location
			+ "</p>";
			var out3 = "<h4 class='field_head'><i class='fas fa-book-open'></i> Workload:</h4><p class='ag_text'>" + str[i].workload
			+ "</p><br>";
			var out4 = "<button class='applyButton' id='ap_bttn" + i + "' type='button' onclick='applyFunc("+ str[i].idAggelia +")'>Apply For this Job!</button>";
			
			node.innerHTML = out + out1 + out2 + out3 + out4;
		}
		
		function applyFunc(id){
			var xhttp = new XMLHttpRequest();
			var flag=0;
			
			xhttp.onreadystatechange = function() {
				if(flag==1){
					return false;
				}
				if(this.readyState==4 && this.status==200){
					flag = 0;
					alert("Your application to your job has been successfully sent!");
					location.reload();
				}
				else if(this.status==500){
					flag = 1;
					alert("You have already applied for this job!");
				}
			}
			
			xhttp.open("POST", "ApplyJobServlet?id=" + id, true);
			xhttp.send();
		}
	</script>
</body>
</html>