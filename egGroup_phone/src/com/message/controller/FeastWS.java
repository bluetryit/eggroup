package com.message.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mem.model.MemService;
import com.message.model.*;
import com.myfeast.model.MyFeastService;
import com.myfeast.model.MyFeastVO;
import com.message.jedis.*;


@ServerEndpoint("/FeastWS/{userName}")//這應該放房間號吧
public class FeastWS {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson =new GsonBuilder().setDateFormat("HH:mm:ss").create();
	

	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession)  {
		// 設定成500KB為了配合Android bundle傳送大小
		int maxBufferSize = 500 * 1024;
		userSession.setMaxTextMessageBufferSize(maxBufferSize);
		userSession.setMaxBinaryMessageBufferSize(maxBufferSize);
		/* save the new user in the map */
		sessionsMap.put(userName, userSession);
		/* Sends all the connected users to the new user */
		Set<String> userNames = sessionsMap.keySet();//memNo
		System.out.println(userNames +"連線近來了喔");
		
		//上線了
//		State stateMessage = new State("open", userName, userNames);
//		String stateMessageJson = gson.toJson(stateMessage);
//		Collection<Session> sessions = sessionsMap.values();
//		for (Session session : sessions) {
//			if (session.isOpen()) {
//				session.getAsyncRemote().sendText(stateMessageJson);
//			}
//		}
//		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
//				userName, userNames);
//		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		System.out.println("message received: " + message);
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		String sender = chatMessage.getSender();//原來發送訊息的人
		String receiver = chatMessage.getReceiver();//飯局編號
		String messageType = chatMessage.getMessageType();
		MemService memSrv=new MemService();
		System.out.println("messageType = " + messageType);
		
		if ("history".equals(chatMessage.getType())) {
			System.out.println("收到取得歷史訊息的請求，sender:"+sender+" receiver:"+receiver);
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);//取回list拆開處裡資料
			if (userSession != null && userSession.isOpen()) {
				for(String historyMsg:historyData) {
					ChatMessage cmHistory_old=gson.fromJson(historyMsg,ChatMessage.class);
					ChatMessage cmHistory = new ChatMessage(cmHistory_old.getType(),cmHistory_old.getReceiver(),memSrv.memFindByPrimaryKey(cmHistory_old.getSender()).getMem_name(),cmHistory_old.getContent(),cmHistory_old.getMessageType(),cmHistory_old.getTime());	
					if(!cmHistory.getMessageType().equals("image")) {
						userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
					}else {
						userSession.getAsyncRemote().sendBinary(ByteBuffer.wrap(gson.toJson(cmHistory).getBytes()));
					}			
				}			
			}
			return;
//			String historyMsg = gson.toJson(historyData);
//			ChatMessage cmHistory = new ChatMessage("history",receiver , sender, historyMsg,"text");//由於app收訊是由發送者是不是飯局為判斷所以將，兩者反過來放
//			if (userSession != null && userSession.isOpen()) {
//				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
//				return;}
		}

		
		MyFeastService myFeastSrv=new MyFeastService();
		List<MyFeastVO> myFeastList=myFeastSrv.getByFea(receiver);
		System.out.println("甚麼人在飯局裡呢"+gson.toJson(myFeastList));
		System.out.println("我是收的"+receiver);
		
		for(MyFeastVO myFeastVO:myFeastList) {
			Session session = sessionsMap.get(myFeastVO.getMye_memNo());
			if(session != null && session.isOpen()&&!myFeastVO.getMye_memNo().equals(sender)) {
				if (messageType.equals("image")) {
					System.out.println("messageType.equals(\"image\")");
					ChatMessage resChatMessage = new ChatMessage(chatMessage.getType(),chatMessage.getReceiver(),memSrv.memFindByPrimaryKey(sender).getMem_name(),chatMessage.getContent(),chatMessage.getMessageType(),chatMessage.getTime());
					int imageLength = chatMessage.getContent().getBytes().length;
					System.out.println("image length =這邊是 String message " + imageLength);
					String resJsonln=gson.toJson(resChatMessage); 
					session.getAsyncRemote().sendBinary(ByteBuffer.wrap(resJsonln.getBytes()));
				}else {
				ChatMessage resChatMessage = new ChatMessage(chatMessage.getType(),chatMessage.getReceiver(),memSrv.memFindByPrimaryKey(sender).getMem_name(),chatMessage.getContent(),chatMessage.getMessageType(),chatMessage.getTime());
				String resJsonln=gson.toJson(resChatMessage);
				System.out.println("要發給大家的資料:"+resJsonln);
				session.getAsyncRemote().sendText(resJsonln);
				System.out.println("傳給飯局裡的"+myFeastVO.getMye_memNo());	
				}
			}
		}
//		if (!messageType.equals("image")) {
			JedisHandleMessage.saveChatMessage(sender, receiver, message);//這裡的message是原始的，所以送的人是發送訊息的人，收的是飯局編號
//		}
//		if (receiverSessionList != null) {// && receiverSession.isOpen()
//			// 如果訊息內容包含圖片，必須將資料改成byte型式傳送，否則Android端會因為資料量過大而無法接收
//			if (messageType.equals("image")) {
//				int imageLength = chatMessage.getContent().getBytes().length;
//				System.out.println("image length = " + imageLength);
//				for(Session receiverSession:receiverSessionList) {
//				receiverSession.getAsyncRemote().sendBinary(ByteBuffer.wrap(message.getBytes()));
//				}
//				//這邊沒有存圖片，看看要不要改 JedisHandleMessage 多設個TYPE存圖 但下面又有個接圖片訊息的
//			} else {
//				for(Session receiverSession:receiverSessionList) {
//				receiverSession.getAsyncRemote().sendText(message);
//				}
//				JedisHandleMessage.saveChatMessage(sender, receiver, message);
//				
//				//存進資料庫//這個不知道有沒有存時間
//			}
//		}
		System.out.println("Message received: " + message);
	}

	// 此方法接收byte型式資料
	@OnMessage
	public void OnMessage(Session userSession, ByteBuffer bytes) {
		String message = new String(bytes.array());
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		String sender = chatMessage.getSender();
		String receiver = chatMessage.getReceiver();//飯局編號
		String messageType = chatMessage.getMessageType();
		//
		MemService memSrv=new MemService();
		MyFeastService myFeastSrv=new MyFeastService();
		List<MyFeastVO> myFeastList=myFeastSrv.getByFea(receiver);
		System.out.println("甚麼人在飯局裡呢"+gson.toJson(myFeastList));
		System.out.println("我是收的"+receiver);
		for(MyFeastVO myFeastVO:myFeastList) {
			Session session = sessionsMap.get(myFeastVO.getMye_memNo());
			if(session != null && session.isOpen()&&!myFeastVO.getMye_memNo().equals(sender)) {
				if (messageType.equals("image")) {
					ChatMessage resChatMessage = new ChatMessage(chatMessage.getType(),chatMessage.getReceiver(),memSrv.memFindByPrimaryKey(sender).getMem_name(),chatMessage.getContent(),chatMessage.getMessageType(),chatMessage.getTime());
					int imageLength = chatMessage.getContent().getBytes().length;
					
					System.out.println("image length = 這裡是ByteBuffer bytes" + imageLength);
					
					session.getAsyncRemote().sendBinary(ByteBuffer.wrap(message.getBytes()));
				}
			}
		}
		System.out.println("ByteBuffer Message received: " + message);
	}
	
	@OnError
	public void onError(Session userSession, Throwable e) {
		e.printStackTrace();
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				break;
			}
		}

		if (userNameClose != null) {
			State stateMessage = new State("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
			for (Session session : sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}
}
