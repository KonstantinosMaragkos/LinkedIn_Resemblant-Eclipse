<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ page import="dao.PDataDAO, dao.PDataDAOImpl, model.PersonalData" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Profile</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="./css/profile_page.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
</head>
<body onload="loadSlides()">

	<%
		if(session.getAttribute("username") == null) {
			response.sendRedirect("welcome_page.jsp");
		}
	%>

	<h3 id="res"></h3>

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
	
	<div id="editModal" class="modal">
  		<div class="modal-content">
   		 	<span class="close">&times;</span>
    	 	<form name="form1" action="ProfileServlet2" method="post" onsubmit="return submitFunc()">
			<div id="div5">
				<h2 style="color:#008B8B"><i class="fas fa-user" style="margin-right:10px;font-size:90%"></i>Update you profile</h2>
				<i id="info">- You can select the fields you want to update -</i>
				<p>University <input type="checkbox" name="valid_university" value="yes" onclick="deleteFields('university')" checked></p>
				<input type="text" name="university" placeholder="University" maxlength="60">
				<p>Department <input type="checkbox" name="valid_department" value="yes" onclick="deleteFields('department')" checked></p>
				<input type="text" name="department" placeholder="Department" maxlength="60">
				<p>From <input type="checkbox" name="valid_from" value="yes" onclick="deleteFields('from')" checked><span style="margin-left:10%;">To <input type="checkbox" name="valid_to" value="yes" onclick="deleteFields('to')" checked></span></p>
				<select name="from">
					<option value="...1985">1985</option>
					<option value="1986">1986</option><option value="1987">1987</option><option value="1988">1988</option><option value="1989">1989</option>
					<option value="1990">1990</option><option value="1991">1991</option><option value="1992">1992</option><option value="1993">1993</option>
					<option value="1994">1994</option><option value="1995">1995</option><option value="1996">1996</option><option value="1997">1997</option>
					<option value="1998">1998</option><option value="1999">1999</option><option value="2000">2000</option><option value="2001">2001</option>
					<option value="2002">2002</option><option value="2003">2003</option><option value="2004">2004</option><option value="2005" selected>2005</option>
					<option value="2006">2006</option><option value="2007">2007</option><option value="2008">2008</option><option value="2009">2009</option>
					<option value="2010">2010</option><option value="2011">2011</option><option value="2012">2012</option><option value="2013">2013</option>
					<option value="2014">2014</option><option value="2015">2015</option><option value="2016">2016</option><option value="2017">2017</option>
					<option value="2018">2018</option>
				</select>
				<select name="to">
					<option value="1989">1989</option>
					<option value="1990">1990</option><option value="1991">1991</option><option value="1992">1992</option><option value="1993">1993</option>
					<option value="1994">1994</option><option value="1995">1995</option><option value="1996">1996</option><option value="1997">1997</option>
					<option value="1998">1998</option><option value="1999">1999</option><option value="2000">2000</option><option value="2001">2001</option>
					<option value="2002">2002</option><option value="2003">2003</option><option value="2004">2004</option><option value="2005" selected>2005</option>
					<option value="2006">2006</option><option value="2007">2007</option><option value="2008">2008</option><option value="2009">2009</option>
					<option value="2010">2010</option><option value="2011">2011</option><option value="2012">2012</option><option value="2013">2013</option>
					<option value="2014">2014</option><option value="2015">2015</option><option value="2016">2016</option><option value="2017">2017</option>
					<option value="2018">2018</option>
				</select>
				<p>Choose education level <input type="checkbox" name="valid_education" value="yes" onclick="deleteFields('education')" checked></p>
				<select name="education" style="width:40%">
					<option value="Bachelor_Uni">Bachelor-Uni</option>
					<option value="Bachelor_TEI">Bachelor-TEI</option>
					<option value="College">College</option>
					<option value="Master">Master</option>
					<option value="Other">Other</option>
				</select>
			</div><br>
			
			<div id="div6">
				<p>Title <input type="checkbox" name="valid_title" value="yes" onclick="deleteFields('title')" checked></p>
				<input type="text" name="title" placeholder="Title" maxlength="60">
				<p>Company <input type="checkbox" name="valid_company" value="yes" onclick="deleteFields('company')" checked></p>
				<input type="text" name="company" placeholder="Company" maxlength="60">
				<p>Location <input type="checkbox" name="valid_location" value="yes" onclick="deleteFields('location')" checked></p>
				<input type="text" name="location" placeholder="Location" maxlength="60">
				
				<input type="submit" value="Submit">
			</div>
			</form>	
   		</div>
	</div>
	
	<div class="container">
		<div class="column side">
			<div class="img_container">
				<img src="${photo}" alt="profile_pic" class="pr_img">
			</div>
			
			<div id="div1">
				<div class="name"><span>${f_name} ${l_name}</span></div>
				<div class="data_left"><i class="fas fa-mobile-alt"> Phone: </i><span> ${phone}</span></div>
				<div class="data_left last"><i class="fas fa-envelope"> Email: </i><span> ${email}</span></div>
			</div>
			
			
		</div>
		
		<div class="column content">
			<h1>Profile details <i class="fas fa-edit edit_icon" onmouseover="showEdit()" onmouseout="hideEdit()">
					<span class="popuptext" id="editPopup">Edit Profile</span>
					</i></h1>
			<div>
			<h2>Education</h2>
			<hr>
			<div id="div2">
			<form>
				<div class="data_right"><b>University</b> <p>${uni}
					<label class="switch" onmouseover="showSlide(1)" onmouseout="hideSlide(1)">
						<span class="popuptext para" id="slidePopup1"><i class="fas fa-arrow-left"></i>Private Public<i class="fas fa-arrow-right"></i></span>
  						<input type="checkbox" name="slide_University" value="yes" onclick="manageSlides('slide_University')" checked>
  						<span class="slider"></span>
					</label></p>
				</div>
				<div class="data_right"><b>Department</b> <p>${dep}
					<label class="switch" onmouseover="showSlide(2)" onmouseout="hideSlide(2)">
						<span class="popuptext para" id="slidePopup2"><i class="fas fa-arrow-left"></i>Private Public<i class="fas fa-arrow-right"></i></span>
  						<input type="checkbox" name="slide_Department" value="yes" onclick="manageSlides('slide_Department')" checked>
  						<span class="slider"></span>
					</label></p>
				</div>
				<div class="data_right year" style="float:left;"><b>From</b> <p>${frm}</p></div>
				<div class="data_right year"><b>To</b> <p>${to}</p></div><br>
				<div class="data_right" style="margin-bottom:25px;"><b>Education Level</b> <p>${edu}
					<label class="switch" onmouseover="showSlide(3)" onmouseout="hideSlide(3)">
						<span class="popuptext para" id="slidePopup3"><i class="fas fa-arrow-left"></i>Private Public<i class="fas fa-arrow-right"></i></span>
  						<input type="checkbox" name="slide_Education" value="yes" onclick="manageSlides('slide_Education')" checked>
  						<span class="slider"></span>
					</label></p>
				</div>
			</form>
			</div>
			
			<h2>Profession</h2>
			<hr>
			<div id="div3">
				<div class="data_right"><b>Title</b> <p>${title}
					<label class="switch" onmouseover="showSlide(4)" onmouseout="hideSlide(4)">
						<span class="popuptext para" id="slidePopup4"><i class="fas fa-arrow-left"></i>Private Public<i class="fas fa-arrow-right"></i></span>
  						<input type="checkbox" name="slide_Title" value="yes" onclick="manageSlides('slide_Title')" checked>
  						<span class="slider"></span>
					</label></p>
				</div>
				<div class="data_right"><b>Company</b> <p>${comp}
					<label class="switch" onmouseover="showSlide(5)" onmouseout="hideSlide(5)">
						<span class="popuptext para" id="slidePopup5"><i class="fas fa-arrow-left"></i>Private Public<i class="fas fa-arrow-right"></i></span>
  						<input type="checkbox" name="slide_Company" value="yes" onclick="manageSlides('slide_Company')" checked>
  						<span class="slider"></span>
					</label></p>
				</div>
				<div class="data_right"><b>Location</b> <p>${loc}
					<label class="switch" onmouseover="showSlide(6)" onmouseout="hideSlide(6)">
						<span class="popuptext para" id="slidePopup6"><i class="fas fa-arrow-left"></i>Private Public<i class="fas fa-arrow-right"></i></span>
  						<input type="checkbox" name="slide_Location" value="yes" onclick="manageSlides('slide_Location')" checked>
  						<span class="slider"></span>
					</label></p>
				</div>
			</div>
			</div>
		</div>
	</div>
	
	<script>
	//modal
	var modal = document.getElementById('editModal');
	var bttn = document.getElementsByClassName("fas fa-edit edit_icon")[0];
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
	
	//flags
	var uni_f = 0, dep_f = 0, frm_f = 0, to_f = 0, edu_f = 0;
	var title_f = 0, comp_f = 0, loc_f = 0;
	
	function showEdit(){
		show("editPopup");
	}
	function hideEdit(){
		hide("editPopup");
	}
	function showSlide(x){
		show("slidePopup" + x);
	}
	function hideSlide(x){
		hide("slidePopup" + x);
	}
	
	function show(str){
		var x = document.getElementById(str);
		x.style.visibility = "visible";
	}
	function hide(str){
		var x = document.getElementById(str);
		x.style.visibility = "hidden";
	}
	
	//form
	function deleteFields(name){
		var x = document.getElementsByName(name)[0];
		if(x.disabled == false){
			x.disabled = true;
			var str = "valid_" + name;
			var y = document.getElementsByName(str)[0];
			y.value = "no";
		}
		else{
			x.disabled = false;
			var str = "valid_" + name;
			var y = document.getElementsByName(str)[0];
			y.value = "yes";
		}
	}
	
	function manageSlides(str){
		//fixes value
		var status;
		var x = document.getElementsByName(str)[0];
		if(x.value == "no"){
			x.value = "yes";
			status = 0;
		}
		else{
			x.value = "no";
			status = 1;
		}
		//console.log("hi, " + x.value);
		
		//submits value - ajax
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if(this.readyState==4 && this.status==200){
				document.getElementById("res").innerHTML = this.responseText;
			}
		};
		xhttp.open("POST", "ChangeStatusServlet?status=" + status + "&field=" + x.name, true);
		xhttp.send();
	}
	
	function loadSlides() {
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if(this.readyState==4 && this.status==200){
				var pds = JSON.parse(this.responseText);
				setSlides(pds);
			}
		};
		xhttp.open("GET", "ChangeStatusServlet", true);
		xhttp.send();
	}
	
	function setSlides(pds) {
		var elem;
		
		if(pds.university_Status == 1){
			elem = document.getElementsByName("slide_University")[0];
			elem.value = "no";
			elem.checked = false;
		}
		if(pds.department_Status == 1){
			elem = document.getElementsByName("slide_Department")[0];
			elem.value = "no";
			elem.checked = false;
		}
		if(pds.education_Status == 1){
			elem = document.getElementsByName("slide_Education")[0];
			elem.value = "no";
			elem.checked = false;
		}
		if(pds.title_Status == 1){
			elem = document.getElementsByName("slide_Title")[0];
			elem.value = "no";
			elem.checked = false;
		}
		if(pds.company_Status == 1){
			elem = document.getElementsByName("slide_Company")[0];
			elem.value = "no";
			elem.checked = false;
		}
		if(pds.location_Status == 1){
			elem = document.getElementsByName("slide_Location")[0];
			elem.value = "no";
			elem.checked = false;
		}
	}
	</script>

</body>
</html>