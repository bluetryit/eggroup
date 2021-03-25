<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>

<%
	MemVO memVO = (MemVO) session.getAttribute("memberVO");
	pageContext.setAttribute("memVO",memVO);
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Chat Room</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <style type="text/css">* {
	margin: auto;
	padding: 0px;
}

html, body {
	font: 15px verdana, Times New Roman, arial, helvetica, sans-serif,
		Microsoft JhengHei;
	width: 90%;
	height: 90%;
/* 	background: #eeeeda; */
}

.panel {
	border: 2px solid #0078ae;
	border-radius: 5px;
	width: 100%;
}

.message-area {
	height: 70%;
	resize: none;
	box-sizing: border-box;
	overflow: auto;
}

.input-area {
	background: #0078ae;
	box-shadow: inset 0 0 10px #00568c;
}

.input-area input {
	margin: 0.5em 0em 0.5em 0.5em;
}

.text-field {
	border: 1px solid grey;
	padding: 0.2em;
	box-shadow: 0 0 5px #000000;
}

.button {
	border: none;
	padding: 5px 5px;
	border-radius: 5px;
	width: 60px;
	background: orange;
	box-shadow: inset 0 0 10px #000000;
	font-weight: bold;
}

.button:hover {
	background: yellow;
}

h1 {
	font-size: 1.5em;
	padding: 5px;
	margin: 5px;
}

#userName {
	width: 20%;
}

#message {
	min-width: 50%;
	max-width: 60%;
}

.statusOutput {
	background: #0078ae;
	text-align: center;
	color: #ffffff;
	border: 1px solid grey;
	padding: 0.2em;
	box-shadow: 0 0 5px #000000;
	width: 20%;
}

#messagesArea {
	border: 8px dashed;
	cursor: text;
	border: 1px solid #ccc;
	font: medium -moz-fixed;
	font: -webkit-small-control;
	width: 97%;
	height: 500px;
	overflow: auto;
	padding: 2px;
	resize: both;
	-moz-box-shadow: inset 0px 1px 2px #ccc;
	-webkit-box-shadow: inset 0px 1px 2px #ccc;
	box-shadow: inset 0px 1px 2px #000000;
	margin: 10px auto;
}

#fileHolder.hover {
	color: #f00;
	border-color: #f00;
	border-style: solid;
	box-shadow: inset 0 3px 4px #888;
}

#fileHolder {
	font-weight: bold;
	text-align: center;
	padding: 0 0;
/* 	margin: 1em auto; */
/* 	margin: 1em; */
	color: #555;
	border: 2px dashed #555;
	border-radius: 7px;
	cursor: default;
	width: 1024px;
	min-height: 100px;
}

#fileHolder.hover {
	color: #f00;
	border-color: #f00;
	border-style: solid;
	box-shadow: inset 0 3px 4px #888;
}

#fileHolder img {
	display: inline-block;
	margin: 10px auto;
}

#fileHolder p {
	margin: 10px;
	font-size: 14px;
}</style>
    </head>
    	
    <body onload="connect();" onunload="disconnect();">
    	<%@ include file="/header.jsp"%>
        <h1 align="center"> 聊天室 </h1>
	    <h3 id="statusOutput" class="statusOutput"></h3>
	    <div id="fileHolder"></div>
	    <div id="messagesArea"></div>
        <div class="panel input-area">
            <input id="userName" class="text-field" type="text" placeholder="使用者名稱"  value="${memVO.mem_name}" readonly="readonly"/>
            <input id="message"  class="text-field" type="text" placeholder="訊息" onkeydown="if (event.keyCode == 13) sendMessage();"/>
            <input type="submit" id="sendMessage" class="button" value="送出" onclick="sendMessage();"/>
		    <input type="button" id="connect"     class="button" value="連線" onclick="connect();"/>
		    <input type="button" id="disconnect"  class="button" value="離線" onclick="disconnect();"/>
	    </div>
	    
    </body>
    
<script>
    
    var MyPoint ="/MyEchoServer";
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    
	var statusOutput = document.getElementById("statusOutput");
	var webSocket;
	
	function connect() {
		// 建立 websocket 物件
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) {
			var messagesArea = document.getElementById("messagesArea");
	        var jsonObj = JSON.parse(event.data);
	        var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
	        messagesArea.innerHTML = messagesArea.innerHTML +message+ "<br>";
	        messagesArea.scrollTop = messagesArea.scrollHeight;
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
	}
	
	
	var inputUserName = document.getElementById("userName");
	inputUserName.focus();
	
	function sendMessage() {
	    var userName = inputUserName.value.trim();
	    if (userName === ""){
	        alert ("使用者名稱請勿空白!");
	        inputUserName.focus();	
			return;
	    }
	    
	    var inputMessage = document.getElementById("message");
	    var message = inputMessage.value.trim();
	    
	    if (message === ""){
	        alert ("訊息請勿空白!");
	        inputMessage.focus();	
	    }else{
	        var jsonObj = {"userName" : userName, "message" : message};
	        webSocket.send(JSON.stringify(jsonObj));
	        inputMessage.value = "";
	        inputMessage.focus();
	    }
	}
	
	
	function disconnect () {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}

	
	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
    
</script>

<script>

var filereader_support = typeof FileReader != 'undefined';
if(!filereader_support) {
	 alert("No FileReader support");
}
var dnd_support = 'draggable' in document.createElement('span');
if(!dnd_support) {
	 alert("No draggable support");
}
var fileHolder = document.getElementById('fileHolder');
 
acceptedTypes = {
  'image/png': true,
  'image/jpeg': true,
  'image/gif': true
};

document.addEventListener("dragover", function(event) {
	event.preventDefault();
    event.target.className = 'hover';
},false);

document.addEventListener("drop", function(event) {
	event.preventDefault();
    event.target.className = '';
    readfiles(event.dataTransfer.files);
});


function readfiles(files) {
  for (var i = 0; i < files.length; i++) {
     previewfile(files[i]);
  }
 
}

function previewfile(file) {
	  if (filereader_support === true && acceptedTypes[file.type] === true) {
	       var reader = new FileReader();
	       reader.onload = function (event) {
	          var image = new Image();
	          image.src = event.target.result;
	          fileHolder.appendChild(image);
	          
	          var userName = inputUserName.value.trim();
			  if (userName === ""){
			      alert ("使用者名稱請勿空白!");
			      inputUserName.focus();	
			      return;
			  }
			    	    
			  var message = '<img src='+event.target.result+'>';
			  var jsonObj = {"userName" : userName, "message" : message};
			  webSocket.send(JSON.stringify(jsonObj));
	          
	       };
	       reader.readAsDataURL(file);

	      
	       
	       
	  } else {
	       alert(file.type);
	  }
}
	
</script>

</html>
