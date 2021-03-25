<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<%@ page import="com.message.model.*, java.util.*, java.text.*,java.sql.*"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Chat Room</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" href="css/styles.css" type="text/css"/>
    </head>
    
    <body onload="connect();" onunload="disconnect();">
    <%@ include file="/header.jsp"%>
	    <h3 id="statusOutput" class="statusOutput"></h3>
        <textarea id="messagesArea" class="panel message-area" readonly ></textarea>
        <div class="panel input-area">
            <input id="message"  class="text-field" type="text" placeholder="訊息" onkeydown="if (event.keyCode == 13) sendMessage();"/>
            <input type="submit" id="sendMessage" class="button" value="送出" onclick="sendMessage();"/>
		    <input type="button" id="connect"     class="button" value="連線" onclick="connect();"/>
		    <input type="button" id="disconnect"  class="button" value="離線" onclick="disconnect();"/>
		     <form action="FeastWS" method=post enctype="multipart/form-data">
		     <input type="file" name="upfile1">
		     <input type="submit" value="上傳">
		    </form> 
	    </div>
    </body>
    
<script>
	var userName = ${sessionScope.memberVO.mem_no};
    var MyPoint = "/FeastWS/ME000002";
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
			updateStatus("WebSocket 成功連線");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
			
			//取得歷史訊息
			var jsonObj ={"content":"","messageType":"text","receiver":"FE000001","sender":userName,"time":"16:56:29","type":"history"}
	        webSocket.send(JSON.stringify(jsonObj));
		};

		webSocket.onmessage = function(event) {
			var messagesArea = document.getElementById("messagesArea");
	        var jsonObj = JSON.parse(event.data);
	        if(jsonObj.messageType.equals("text")){
	        	var message = jsonObj.receiver + ": " + jsonObj.content + "\r\n";
	   	        messagesArea.value = messagesArea.value + message;
	   	        messagesArea.scrollTop = messagesArea.scrollHeight;
	        }else{
	        	
	        	var encodedData = window.btoa(stringToEncode);//將base64解碼
	        	
	        }
	     
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
	}
	
	
	function sendMessage() {
		
	    var inputMessage = document.getElementById("message");
	    var message = inputMessage.value.trim();
	    //取得顯示區域
	    var messagesArea = document.getElementById("messagesArea");
	    
	    if (message === ""){
	        alert ("訊息請勿空白!");
	        inputMessage.focus();	
	    }else{
	    	const timestamp = new Date('2012-06-08').getTime()
	    	var jsonObj ={"content":message,"messageType":"text","receiver":"FE000001","sender":userName,"time":"16:56:29","type":"chat"}
	        webSocket.send(JSON.stringify(jsonObj));
	    	
	    	 messagesArea.value = messagesArea.value + ":"+message;
		        messagesArea.scrollTop = messagesArea.scrollHeight;
		        
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
</html>