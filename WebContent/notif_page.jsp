<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Notifications</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="./css/notif_page.css">
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
		<li><a href="JobsPageServlet" title="Jobs"><i class="fas fa-user-tie icon"></i>Jobs</a></li>
		<li><a href="ConvServlet" title="Conversations"><i class="fas fa-comment icon"></i>Conversations</a></li>
		<li><a href="NotificationServlet" class="active" title="Notifications"><i class="fas fa-globe icon"></i>Notifications</a></li>
		<li><a href="ProfileServlet" title="Profile"><i class="fas fa-user-edit icon"></i>Profile</a></li>
		<li class="right"><a href="logout.jsp" title="Logout"><i class="fas fa-sign-out-alt icon"></i>Logout</a></li>
		<li class="right"><a href="settings_page.jsp" title="Settings"><i class="fas fa-cog icon"></i>Settings</a></li>
	</ul>
	
	<div class="request_container">
		<h2 id="top" style="color: #555">Friend Requests</h2><hr>
		<div id="request-list">
			<% 
				int count = (int) request.getAttribute("flist_count");
				if(count == 0) {
			%>
				<i class="request_warning">You have no friend requests</i>
			<% } %>
		</div>
	</div>
	
	<h2 class="ar_header">Your articles</h2>
	<div class="article_container" id="content">
		<a href="#top" class='top_link'>Go to top</a>
	</div>
	
	<div id="editModal" class="modal">
  		<div class="modal-content" id="editModal-content">
   		 	<span class="close">&times;</span>
   		</div>
	</div>

	<script>
		window.onscroll = function() {gotoTop()};
	
		function loadPage() {
			loadRequests();
			loadArticles();
		}
		function loadRequests() {
			var out = "";
			var str = ${flist} ;
			//var str = JSON.parse(${flist});
			for(var i=0; i<str.length; i++){
				out += "<div class='request_item' id='request" + i + "'><p><i class='fas fa-user-plus'> </i> <b><a href='FriendServlet?f_id=" + str[i].user_id + "'>" + str[i].first_Name + " " +
				str[i].last_Name + "</a></b> wants to be your friend <button class='r_button' type='button' onclick='decline(" + str[i].user_id + ")'><i class='fas fa-times'></i>" +
				"Decline</button> <button class='r_button' type='button' onclick='accept(" + str[i].user_id + ")'><i class='fas fa-check'></i>Accept</button></p></div>";
			}
			if(out != ""){
				document.getElementById("request-list").style.display = "block";
				var x = "request" + --i;
				document.getElementById("request-list").innerHTML = out;
				document.getElementById(x).style.borderBottom = "none";
			}
			/*else {
				document.getElementById("request-list").style.display = "none";
			}*/
		}
		
		function accept(id) {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if(this.readyState==4 && this.status==200){
					//document.getElementById("res").innerHTML = this.responseText;
					window.location = "NotificationServlet";
				}
			};
			
			//HERE
			xhttp.open("POST", "ManageRequestServlet?f_id=" +id+ "&flag=0", true);
			xhttp.send();
		}
		function decline(id) {
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if(this.readyState==4 && this.status==200){
					//document.getElementById("res").innerHTML = this.responseText;
					window.location = "NotificationServlet";
				}
			};
			
			//HERE
			xhttp.open("POST", "ManageRequestServlet?f_id=" +id+ "&flag=1", true);
			xhttp.send();
		}
		
		function loadArticles() {
			var out;
			var str = ${arthra};
			var photo = "${photo}";
			var user = ${user};
			var likes = ${like_counts};
			
			var id=0;
			for(var i=0; i<str.length; i++){
				out = "";
				var node = document.createElement("DIV");
				node.className = "article";
				out += "<p class='article_user'><img src='" + photo + "' alt='photo' class='ar_photo'> " + 
				user.first_Name + " " + user.last_Name + "</p><br>";
				if(str[i].text != undefined) {
					out += "<p class='article_text'>" + str[i].text + "</p>";
					console.log("<p>" + str[i].text + "</p>");
				}
				if(str[i].photo != undefined) {
					out += "<img src='C:/media/user" + str[i].User_id + "/" + str[i].photo +
						"' alt='photo' class='artice_photo'>";
					console.log("<img src='C:/media/user" + str[i].User_id + "/" + str[i].photo +
							"' alt='photo' class='artice_photo'>");
				}
				if(str[i].video != undefined) {
					out += "<video controls class='article_video'><source src='C:/media/user" + str[i].User_id + "/" + str[i].video +
						"' type='video/mp4'>Error</video>";
					console.log("<video controls class='article_video'><source src='C:/media/user" + str[i].User_id + "/" + str[i].video +
							"' type='video/mp4'>Error</video>");
				}
				out += "<br><div class='art_footer'><button class='like_bttn' id='like" + i + "' onclick='likeModal(" + i + ", " + str[i].idArthro + ")'><i class='fas fa-thumbs-up'></i> Likes (" + likes[i] + ")" +
						"</button><button class='comment_bttn' id='comment" + i + "' onclick='commentModal(" + i +  ", " + str[i].idArthro + ")'><i class='far fa-comment'></i> Comments</button></div>" /*+ 
						"<div class='art_comment' id='comment_form" + i + "'><form onsubmit='return submitComment(" + i + ", " + str[i].idArthro + ")'>" +
						"<i>Leave a comment</i><input type='text' name='text_comment' placeholder='Comment..' onfocus='clearIcons(" + i + ")'><i id='check" + i +"' class='fas fa-check'></i><i id='ex" + i +"' class='fas fa-times'></i></form></div>";*/
				
				node.innerHTML = out;
				node.id = "article" + id;	id++;
				document.getElementById("content").appendChild(node);
			}
		}
		
		function likeModal(x, id) {
			//empty modal
			document.getElementById("editModal-content").innerHTML = "<span class='close'>&times;</span>";
			
			var modal = document.getElementById("editModal");
			var span = document.getElementsByClassName("close")[0];
			
			modal.style.display = "block";
			span.onclick = function() {
			    modal.style.display = "none";
			}
			window.onclick = function(event) {
			    if (event.target == modal) {
			        modal.style.display = "none";
			    }
			}
			
			//Fill modal with info
			var str = ${friends_like};
			var likes = ${like_counts};
			var photos = ${fr_photos};
			var out = "";
			
			var start = 0;
			for(var i=0; i<x; i++){
				start += likes[i];
			}
			
			console.log("Start: " + start);
			for(var y=0; y<likes[x]; y++){
				//out += "<p>" + str[start].first_Name + "</p><br>";
				//out += "<p>" + str[start].first_Name + "- " + photos[start] + "</p><br>";
				out += "<p class='article_user'><img src='" + photos[start] + "' alt='photo' class='ar_photo'> " + 
				str[start].first_Name + " " + str[start].last_Name + "</p><br>";
				start++;
			}
			
			var node = document.createElement("DIV");
			node.className = "like_info";
			if(out == ""){
				out = "<span style='color:#444'><i> 0 likes </i><i class='fas fa-angle-down'></i></span>"
			}
			node.innerHTML = out;
			document.getElementById("editModal-content").appendChild(node);
		}
		
		function commentModal(x, id) {
			//load comments
			
			//empty modal
			document.getElementById("editModal-content").innerHTML = "<span class='close'>&times;</span>";
			
			var modal = document.getElementById("editModal");
			var span = document.getElementsByClassName("close")[0];
			
			modal.style.display = "block";
			span.onclick = function() {
			    modal.style.display = "none";
			}
			window.onclick = function(event) {
			    if (event.target == modal) {
			        modal.style.display = "none";
			    }
			}
			//fill modal with content
			var xhttp = new XMLHttpRequest();
			
			xhttp.onreadystatechange = function() {
				if(this.readyState==4 && this.status==200){
					var out = this.responseText.split("}] ");
					for(var i=0; i<2; i++){
						if(out[i] != undefined && out[i]!="[] [] []"){
							out[i] = out[i].concat("}]");
						}
					}
					extractComments(out);					
				}
			};
			xhttp.open("POST", "GetCommentsServlet?arthro_id=" + id, true);
			xhttp.send();
		}
		function extractComments(x){
			var sxolia;
			var users;
			var photos;
			var out = "";
			var node = document.createElement("DIV");
			if(x[0] == undefined || x[0] == "[] [] []"){
				out = "<div class='comment_count'><i> 0 comments </i><i class='fas fa-angle-down'></i></div>";
				node.innerHTML = out;
				document.getElementById("editModal-content").appendChild(node);
				return;
			}
			sxolia = JSON.parse(x[0]);
			users = JSON.parse(x[1]);
			photos = JSON.parse(x[2]);
			
			if(sxolia.length == 1){
				out = "<div class='comment_count'><i>" + sxolia.length + " comment </i><i class='fas fa-angle-down'></i></div>";
			}
			else {
				out = "<div class='comment_count'><i>" + sxolia.length + " comments </i><i class='fas fa-angle-down'></i></div>";
			}
			
			for(var i=0; i<sxolia.length; i++){
				out += "<div class='comment'><p class='comment_user'><img src='" + 
					photos[i] + "' alt='photo' class='ar_photo'> " + 
				users[i].first_Name + " " + users[i].last_Name + "</p><br>";
				out += "<p class='comment_body'>" + sxolia[i].comment + "</p></div>";
			}
			
			node.innerHTML = out;
			document.getElementById("editModal-content").appendChild(node);
		}
		
		function gotoTop(){
			x = document.getElementsByClassName("top_link")[0];
			if (document.body.scrollTop > 400 || document.documentElement.scrollTop > 400) {
				x.style.display = "inline-block";
			}
			else {
				x.style.display = "none";
			}
		}
	</script>
</body>
</html>