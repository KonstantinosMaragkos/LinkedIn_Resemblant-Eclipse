function addText() {
	var user_id = document.getElementById('u').value;
	var active = document.getElementById('a').value;
	var msg = document.getElementById('h').value;
	document.getElementById('h').value='';

	if(msg == "" || msg == " ") {
		return;
	}
	var xmlhttp;
	if(window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	}else { // IE5, IE6
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp.onreadystatechange = function() {
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
		}
	}
	
	xmlhttp.open("POST","StoreServlet?activeId=" + active + "&text=" + msg + "&user_id=" + user_id, true);
	xmlhttp.send();
}

function reloadChatList(active) {
	var xmlhttp;
	if(window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	}else { // IE5, IE6
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp.onreadystatechange = function() {
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var cont = document.getElementById("chatList");
			cont.innerHTML = xmlhttp.responseText;
		}
	}
	
	xmlhttp.open("POST", "reloadChatList?active=" + active, true);
	xmlhttp.send();
}

function reloadHeader(active) {
	var xmlhttp;
	if(window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	}else { // IE5, IE6
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp.onreadystatechange = function() {
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var cont = document.getElementsByClassName("head-right-sub");
			cont[0].innerHTML = xmlhttp.responseText;
		}
	}
	
	xmlhttp.open("POST", "reloadHeader?active=" + active, true);
	xmlhttp.send();
}

function reloadMessages(active) {
	var xmlhttp;
	if(window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	}else { // IE5, IE6
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp.onreadystatechange = function() {
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var cont = document.getElementById("Chat");
			cont.innerHTML = xmlhttp.responseText;
			var m = document.getElementById("message");
			m.scrollTop = m.scrollHeight;
		}
	}
	
	xmlhttp.open("POST", "reloadMessages?active=" + active, true);
	xmlhttp.send();
}

function change(user_id, chat_id) {
	var xmlhttp;
	if(window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	}else { // IE5, IE6
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp.onreadystatechange = function() {
		if(xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			local.reload();
		}
	}
	
	xmlhttp.open("POST", "changeActive?ChatId="+chat_id+"&UserId="+user_id, true);
	xmlhttp.send();
}