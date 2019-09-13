<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Home page</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="./css/home_page.css">
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
		<li id="second"><a href="HomepageServlet" class="active"  title="Homepage"><i class="fas fa-home icon"></i>Home</a></li>
		<li><a href="NetworkServlet" title="Network"><i class="fas fa-users icon"></i>Network</a></li>
		<li><a href="JobsPageServlet" title="Jobs"><i class="fas fa-user-tie icon"></i>Jobs</a></li>
		<li><a href="ConvServlet" title="Conversations"><i class="fas fa-comment icon"></i>Conversations</a></li>
		<li><a href="NotificationServlet" title="Notifications"><i class="fas fa-globe icon"></i>Notifications</a></li>
		<li><a href="ProfileServlet" title="Profile"><i class="fas fa-user-edit icon"></i>Profile</a></li>
		<li class="right"><a href="logout.jsp" title="Logout"><i class="fas fa-sign-out-alt icon"></i>Logout</a></li>
		<li class="right"><a href="settings_page.jsp" title="Settings"><i class="fas fa-cog icon"></i>Settings</a></li>
	</ul>
	
	<div class="container">
		<div class="column side">
			<h2 id="top">Profile overview</h2>
			
			<div class="img_container">
				<img src="${photo}" alt="profile_pic" class="pr_img">
			</div>
			
			<h3 id="username"> ${user.getFirst_Name()} ${user.getLast_Name()}</h3>
			<a href="ProfileServlet" id="pr_link2" title="Visit your Profile">Visit your Profile..</a><br><hr><br>
			
			
			<div id="net-overview">
				<h2>Friends</h2>
				<div id="friendlist">
				</div>
				<a href="network_page.jsp" id="f_link2" title="Go to Network Page">Go to Network Page...</a>
			</div>
		</div>
		
		<div class="upload_form">
			<p>Make an article</p>
			<i class="fas fa-pen" id="media_icon"></i>
		
			<form action="UploadArticleServlet" method="post" enctype="multipart/form-data" onsubmit="return checkValues()">
				<input type="text" id="in_text" name="text_in" placeholder="Enter text..">
		
				<button type="button" id="bttn1" class="media_bttn" onclick="showDivPhoto()"><i class="fas fa-image"></i> Upload photo</button>
				<button type="button" id="bttn2" class="media_bttn" onclick="showDivVideo()"><i class="fas fa-file-video"></i> Upload video</button>
		
				<div id="div_photo" style="display: none">
					<b>Select a photo</b><br>
					<input type="file" id="in_photo" name="photo" accept="image/*">
				</div>
				<div id="div_video" style="display: none">
					<b>Select a video</b><br>
					<input type="file" id="in_video" name="video" accept="video/*">
				</div>
				<input type="submit" name="Submit" value="Submit"><span id="warning">Can't post empty article!</span>
			</form>
		</div>
	
		<div class="column content" id="content">
		</div>
		
		<a href="#top" class='top_link'>Go to top</a>
	</div>
	
	<script>
		window.onscroll = function() {gotoTop()};
	
		function showMore() {
			var i;
			for(i=0; i<3; i++){
				var para = document.createElement("p");
				var node = document.createTextNode("Friend" + (i+4));
				para.appendChild(node);
			
				var element = document.getElementById("friendlist");
				var child = document.getElementById("f_link");
				element.insertBefore(para, child);
			}
			document.getElementById("f_link").style.display = "none";
			document.getElementById("f_link2").style.display = "inline";
		}
		
		function showDivPhoto() {
			document.getElementById("div_photo").style.display = "block";
			document.getElementById("div_video").style.display = "none";
			document.getElementById("in_video").value = "";
			
			var x = document.getElementById("bttn1");
			var y = document.getElementById("bttn2");
			if(x.className == "media_bttn") {
				x.className = "media_bttn active_bttn";
				y.className = "media_bttn";
			}
			else {
				x.className = "media_bttn";
				document.getElementById("div_photo").style.display = "none";
				document.getElementById("in_photo").value = "";
			}
		}
		function showDivVideo() {
			document.getElementById("div_video").style.display = "block";
			document.getElementById("div_photo").style.display = "none";
			document.getElementById("in_photo").value = "";
			
			var y = document.getElementById("bttn1");
			var x = document.getElementById("bttn2");
			if(x.className == "media_bttn") {
				x.className = "media_bttn active_bttn";
				y.className = "media_bttn";
			}
			else {
				x.className = "media_bttn";
				document.getElementById("div_video").style.display = "none";
				document.getElementById("in_video").value = "";
			}
		}
		
		function loadPage() {
			loadFriends();
			loadArticles();
			loadLikes();
		}
		function loadFriends(){
			var out = "";
			var str = ${flist};
			var str2 = ${photos};
			//var str = JSON.parse(${flist});
			for(var i=0; i<str.length; i++){
				out += "<img src='" + str2[i] + "' alt='profile_pic' class='fr_img'><p>" 
				+ str[i].first_Name + " " + str[i].last_Name + "</p><br>";
			}
			document.getElementById("friendlist").innerHTML = out;
		}
		function loadArticles(){
			var out;
			var str = ${article};
			var fr = ${article_friend};
			var ph = ${article_photos};
			
			for(var i=0; i<str.length; i++){
				out = "";
				var node = document.createElement("DIV");
				node.className = "article";
				out += "<p class='article_user'><img src='" + ph[i] + "' alt='photo' class='ar_photo'> " + 
				fr[i].first_Name + " " + fr[i].last_Name + "</p><br>";
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
				out += "<br><div class='art_footer'><button class='like_bttn' id='like" + i + "' onclick='likeFunction(" + i + ", " + str[i].idArthro + ")'><i class='fas fa-thumbs-up'></i> Like!</button>" + 
						"<button class='comment_bttn' id='comment" + i + "' onclick='commentFunction(" + i + ")'><i class='far fa-comment'></i> Comment</button></div>" + 
						"<div class='art_comment' id='comment_form" + i + "'><form onsubmit='return submitComment(" + i + ", " + str[i].idArthro + ")'>" +
						"<i>Leave a comment</i><input type='text' name='text_comment' placeholder='Comment..' onfocus='clearIcons(" + i + ")'><i id='check" + i +"' class='fas fa-check'></i><i id='ex" + i +"' class='fas fa-times'></i></form></div>";
				
				node.innerHTML = out;
				document.getElementById("content").appendChild(node);
			}
		}
		function loadLikes() {
			var x = document.getElementsByClassName("like_bttn");
			var arts = ${article};
			var lk = ${liked};
			
			var index=0;
			for(var i=0; i<arts.length; i++){
				if(arts[i].idArthro == lk[index].idArthro) {
					likeFunction_color(x[i].id);
					index++;
					if(index == lk.length){
						break;
					}
				}
			}
		}
		function likeFunction(y, id) {
			var x = document.getElementById('like' + y),
		    	style = window.getComputedStyle(x),
		    	color = style.getPropertyValue('color');
			
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if(this.readyState==4 && this.status==200){
					if(this.responseText == "Ok") {
						x.style.color = "rgb(59,89,152)";
						x.style.borderTop = "2px solid rgb(59,89,152)";
					}
					else {
						x.style.color = "#777";
						x.style.borderTop = "2px solid #ddd";
					}
				}
			};
			xhttp.open("POST", "ShowInterestServlet?arthro=" + id, true);
			xhttp.send();
		}
		
		function likeFunction_color(str) {
			//only show color
			var x = document.getElementById(str),
		    	style = window.getComputedStyle(x),
		    	color = style.getPropertyValue('color');
			
			x.style.color = "rgb(59,89,152)";
			x.style.borderTop = "2px solid rgb(59,89,152)";
		}
		
		function commentFunction(y) {
			var x = document.getElementById('comment' + y),
	    	style = window.getComputedStyle(x),
	    	color = style.getPropertyValue('color');
			var elem = document.getElementById('comment_form' + y);
			
			if(color == "rgb(119, 119, 119)") {
				x.style.color = "rgb(59,89,152)";
				x.style.borderTop = "2px solid rgb(59,89,152)";
				elem.style.display = "block";
				
				var all = document.getElementsByClassName('art_comment');
				for(var i=0; i<all.length; i++){
					if(all[i].id != "comment_form"+y){
						all[i].style.display = "none";
						document.getElementById('comment' + i).style.color = "#777";
						document.getElementById('comment' + i).style.borderTop = "#777";
						document.getElementById("check" + i).style.opacity = "0";
						document.getElementById("ex" + i).style.opacity = "0";
					}
				}
			}
			else {
				x.style.color = "#777";
				x.style.borderTop = "#777";
				elem.style.display = "none";
				document.getElementById("check" + y).style.opacity = "0";
				document.getElementById("ex" + y).style.opacity = "0";
			}
		}
		
		function submitComment(i, id) {
			var xhttp = new XMLHttpRequest();
			var check = document.getElementById("check" + i);
			var ex = document.getElementById("ex" + i);
			var text = document.getElementsByName("text_comment")[i];
			
			//check if empty
			if(text.value == "") {
				return false;
			}
			
			xhttp.onreadystatechange = function() {
				if(this.readyState==4 && this.status==200){
					ex.style.opacity = "0";
					check.style.opacity = "1";
					text.value = "";
				}
				else {
					check.style.opacity = "0";
					ex.style.opacity = "1";
					text.value = "";
				}
			};
			
			xhttp.open("POST", "UploadCommentServlet?arthro=" + id +"&text=" + text.value, true);
			var er = "UploadCommentServlet?arthro=" + id +"&text=" + text.value;
			console.log(er);

			xhttp.send();
			return false;
		}
		
		function clearIcons(y) {
			document.getElementById("check" + y).style.opacity = "0";
			document.getElementById("ex" + y).style.opacity = "0";
		}
		
		function checkValues() {
			var x1 = document.getElementById("in_text");
			var x2 = document.getElementById("in_photo");
			var x3 = document.getElementById("in_video");
			if(x1.value=="" && x2.value=="" && x3.value==""){
				console.log("1");
				document.getElementById("warning").style.display = "inline";
				return false;
			}
			else {
				console.log("2");
				document.getElementById("warning").style.display = "none";
			}
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