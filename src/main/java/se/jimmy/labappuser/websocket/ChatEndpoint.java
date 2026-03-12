package se.jimmy.labappuser.websocket;

import jakarta.inject.Inject;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import se.jimmy.labappuser.entity.ChatMessages;
import se.jimmy.labappuser.service.ChatMessagesService;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/ws/chat")
public class ChatEndpoint {
    private  static final Set<Session> sessions = ConcurrentHashMap.newKeySet();

    @Inject
    private ChatMessagesService chatService;

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        chatService.getAllMessages().forEach(msg -> { session.getAsyncRemote().sendText(msg.getSender() + ": " + msg.getContent());
        });
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        String[] parts = message.split(": ",2);
        if(parts.length == 2) {
            ChatMessages chatMessages = new ChatMessages();
            chatMessages.setSender(parts[0]);
            chatMessages.setContent(parts[1]);
            chatMessages.setTimestamp(LocalDateTime.now());

            chatService.save(chatMessages);
        }
        sessions.forEach(s -> {
            if (s.isOpen()) {
                s.getAsyncRemote().sendText(message);
            }
        });
    }
    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }
}
