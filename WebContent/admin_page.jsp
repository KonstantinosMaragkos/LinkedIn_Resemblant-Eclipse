<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Admin page</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<meta http-equiv='cache-control' content='no-cache'>
	<meta http-equiv='expires' content='0'>
	<meta http-equiv='pragma' content='no-cache'>
	
	<link rel="stylesheet" href="./css/admin_page.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
</head>
<body onload="loadPage()">
	
	<a id="logout_lnk" href="logout.jsp" title="Logout"><i class="fas fa-sign-out-alt icon"></i>Logout</a>
	
	<div class="container">
		<div class="results">
			
		</div>
	</div>

	<script>
		function loadPage(){
			var str = ${users};
			var out = "<ul id='user_list'>";
			
			for(var i=0; i<str.length; i++){
				out += "<li>" + str[i].first_Name + " " + str[i].last_Name + 
						"<a href='FriendServlet?f_id=" + str[i].user_id + "' class='prof_link'>Visit Profile</a><span class='dl_cont' id='dl_cont" + i + "'><a href='#' onclick='downloadXml(" +i+ ", " + str[i].user_id + ")' title='download xml' class='dl_link'>Download</a></span></li>";
			}
			out += "</ul>";
			document.getElementsByClassName("results")[0].innerHTML = out;
		}
		
		function downloadXml(i, id){
			var sp = document.getElementById("dl_cont" + i);
			var prev = sp.innerHTML;
			var flag=0;
			sp.innerHTML = "<p>Processing...</p>";
			
			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if(this.readyState==4 && this.status==200){
					sp.innerHTML = "<i class='fas fa-check'></i>";
					flag=0;
				}
			};
			xhttp.open("POST", "DownloadXMLSerlvet?id=" + id, true);
			xhttp.send();
		}
	</script>
</body>
</html>