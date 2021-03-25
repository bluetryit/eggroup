package com.message.controller;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

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
import com.message.jedis.JedisHandleMessage;
import com.message.model.ChatMessage;
import com.message.model.State;

@ServerEndpoint("/ChangeNotify/{userName}")
public class ChangeNotify {
	
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

		}
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
