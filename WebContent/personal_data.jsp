<!DOCTYPE>
<html lang="en">
<head>
	<title>WELCOME</title>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<meta http-equiv='cache-control' content='no-cache'>
	<meta http-equiv='expires' content='0'>
	<meta http-equiv='pragma' content='no-cache'>
	
	<link rel="stylesheet" href="./css/personal_data.css">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.1/css/all.css" integrity="sha384-O8whS3fhG2OnA5Kas0Y9l3cfpmYjapjI0E4theH4iuMD+pLhbf6JI0jIMfYcK3yZ" crossorigin="anonymous">
</head>
	
<body>

	<%
		if(session.getAttribute("username") == null) {
			response.sendRedirect("welcome_page.jsp");
		}
	%>

	<div id="div1" class="header">
		<h2>MyLinked<span>in</span></h2>
	</div>
	
	<br><h2 style="color:#222;text-align:center;">Tell us a little about yourself!</h2>
	
	<div id="div2" class="progress">
		<div id="div3" class="demo active">Step 1</div>
		<div id="div3-1" class="demo" style="left:50%;right:0%;border-left:1px solid white">Step 2</div>
	</div>
	
	<div id="div4" class="container">
		<form name="form1" action="PdataServlet" method="post" onsubmit="return submitFunc()">
			<div id="div5">
				<h2 style="color:#777">Add Education - ${photo}</h2><br>
				<p>University</p>
				<input type="text" name="university" placeholder="University" maxlength="60">
				<p>Department</p>
				<input type="text" name="department" placeholder="Department" maxlength="60">
				<p>From<span style="margin-left:10%;">To</span></p>
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
				<p>Choose education level</p>
				<select name="education" style="width:40%">
					<option value="Bachelor_Uni">Bachelor-Uni</option>
					<option value="Bachelor_TEI">Bachelor-TEI</option>
					<option value="College">College</option>
					<option value="Master">Master</option>
					<option value="Other">Other</option>
				</select>
			</div>
			
			<div id="div6" style="display:none;">
				<h2 style="color:#777">Add Professional Experience</h2><br>
				<input type="checkbox" name="exp" value="no" onclick="deleteFields()" checked>  I have working experience<br><br>
				<p>Title</p>
				<input type="text" name="title" placeholder="Title" maxlength="60">
				<p>Company</p>
				<input type="text" name="company" placeholder="Company" maxlength="60">
				<p>Location</p>
				<input type="text" name="location" placeholder="Location" maxlength="60">
				
				<input type="submit" value="Ready!">
			</div>
			
			<input type="hidden" id="refreshed" value="no">
		</form>	
		
		
		<div id="div7" style="margin-top:40px">
		<button id="next_button" type="button" onclick="nextPanel()">Next
			<i class="fas fa-arrow-right"></i>
		</button>
		</div>
	</div>
	
	<script>
	var del_flag = 0;  //for deleted fields
	
	/*onload=function(){
        var e=document.getElementById("refreshed");
        if(e.value=="no")e.value="yes";
        else{e.value="no";location.reload();}
    }*/
	
	function nextPanel() {
		checkField("university");
		checkField("department");
		if(checkField("university") && checkField("department")) { //before cont., check the input fields
			document.getElementById("div3").className = "demo";
			document.getElementById("div3-1").className = "demo active";
		
			document.getElementById("div5").style.display = "none";
			document.getElementById("div6").style.display = "block";
			document.getElementById("next_button").style.display = "none";
		}	
	}
	
	function checkField(fld) {
		var x = document.getElementsByName(fld);
		if(x[0].value == ""){
			x[0].style.border = "1px solid red";
			return false;
		}
		else {
			x[0].style.border = "1px solid gray";
			return true;
		}
	}
	
	function deleteFields() {
		if( typeof deleteFields.flag == 'undefined' ) {
			deleteFields.flag = 0;
		}
		if(deleteFields.flag == 0){
			var x = document.getElementsByName("title");
			x[0].disabled = true;
			x = document.getElementsByName("company");
			x[0].disabled = true;
			x = document.getElementsByName("location");
			x[0].disabled = true;
			deleteFields.flag = 1;
			
			del_flag = 1;
		}
		else {
			var x = document.getElementsByName("title");
			x[0].disabled = false;
			x = document.getElementsByName("company");
			x[0].disabled = false;
			x = document.getElementsByName("location");
			x[0].disabled = false;
			deleteFields.flag = 0;
			
			del_flag = 0;
		}
	}
	
	function submitFunc() {
		if(del_flag == 1){
			return true;
		}
		
		checkField("title");
		checkField("company");
		checkField("location");
		
		if(!checkField("title") || !checkField("company") || !checkField("location")) {
			console.log("submmit() is returning false");
			return false;
		}
	}

	</script>
</body>
</html>