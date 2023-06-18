package com.study.websocket.config;

import org.springframework.stereotype.Service;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/chat")
@Service
public class WebSocketSession {

    private static Set<Session> CLIENTS = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) throws IOException {
        if(CLIENTS.contains(session)){
            System.out.println("이미 연결된 세션입니다. >" + session);
        }
        else{
            CLIENTS.add(session);
            System.out.println("새로운 세션입니다." + session);
        }
    }

    @OnClose
    public void onClose(Session session) throws Exception {
        CLIENTS.remove(session);
        System.out.println("세션을 닫습니다." + session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        for(Session client : CLIENTS){
            client.getBasicRemote().sendText(message);
        }
    }

}
