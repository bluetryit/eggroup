<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.message.model.*, java.util.*, java.text.*"%>

<!DOCTYPE html>
<html>



    <head>
        <title>Chat Room</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/styles.css" type="text/css"/>
   <style>
   .left{
	align:left;
	background:#edbd38;
	text-align: left;
	padding: 5px;
	word-break: break-all;
	display:inline-block;
	margin-bottom: 10px;
	margin-right:auto;
		margin-left:0;
	}

	.right{
		text-align:right;
		background:#69778c;
		color:#eeeeee;
		display:inline-block;
		padding: 5px;
		word-break: break-all;
		margin-bottom: 10px;
		margin-right:0;
		margin-left:auto;
	}
	.col{
	display:flex;
	width:100%;
	}
</style>
   
    </head>
    
    <body onload="connect();" onunload="disconnect();">
  
     
        <div id="messagesArea" class="panel message-area" >
        
        </div>
        <div class="panel input-area">
            <input id="message"  class="text-field" type="text" placeholder="訊息" onkeydown="if (event.keyCode == 13) sendMessage();"/>
            <input type="submit" id="sendMessage" class="button" value="送出" onclick="sendMessage();"/>
		    <input type="button" id="connect"     class="button" value="連線" onclick="connect();"/>
		    <input type="button" id="disconnect"  class="button" value="離線" onclick="disconnect();"/>
		    <form action="FeastWS"method=post enctype="multipart/form-data">
		   <input type="file" name="upfile2">
        	<input type="submit" value="上傳">
		    </form>
	    </div>
    </body>
    
<script>
	var feast="${feastInfoVO.fea_no}"//取飯局的編號
	var userName = "${sessionScope.memberVO.mem_no}";
	var user="${sessionScope.memberVO.mem_name}";
    var MyPoint = "/FeastWS/"+userName;
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
// 			updateStatus("WebSocket 成功連線");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
			
			 var jsonObj = {"content":"","messageType":"text","receiver":feast,"sender":userName,"time":"11:50:05","type":"history"};
		        webSocket.send(JSON.stringify(jsonObj));
		};

		webSocket.onmessage = function(event) {
			var messagesArea = document.getElementById("messagesArea");
	        var jsonObj = JSON.parse(event.data);
	        var message;
	        if(jsonObj.messageType=="text"){
	        	if(jsonObj.receiver==user){
	        		message =  "<div class='col'><div  class='right'>"+jsonObj.content + ": </div></div><br>";
	        	}else{
	        		message =  "<div class='col'><div  class='left'>"+jsonObj.receiver+ ": "+jsonObj.content+"</div></div><br>";
	        	}	        	
	   	      //  messagesArea.value = messagesArea.value + message;
	   	     document.getElementById("messagesArea").innerHTML +=message;
	   	        messagesArea.scrollTop = messagesArea.scrollHeight;
	        }else{
	        	var encodedData = window.btoa(stringToEncode);//將base64解碼
	        }
		};

		webSocket.onclose = function(event) {
// 			updateStatus("WebSocket 已離線");
		};
	}
	
	
	function sendMessage() {
		
	    var inputMessage = document.getElementById("message");
	    var message = inputMessage.value.trim();
	    var allmessage;
	    
	    if (message === ""){
	        alert ("訊息請勿空白!");
	        inputMessage.focus();	
	    }else{
	    	var timestamp = (new Date()).valueOf();
	        var jsonObj = {"content":message,"messageType":"text","receiver":feast,"sender":userName,"time":null,"type":"chat"};
	        webSocket.send(JSON.stringify(jsonObj));
	        inputMessage.value = "";
	        inputMessage.focus();
	        
	        var message ="<div class='col'><div class='right'>"+ message +":</div></div><br>";
	        //allmessage+=message;
	        document.getElementById("messagesArea").innerHTML +=message;
 	        messagesArea.scrollTop = messagesArea.scrollHeight;
	    }
	}

	
	function disconnect () {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}

	/* 
	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	} */
    
</script>
</html>
