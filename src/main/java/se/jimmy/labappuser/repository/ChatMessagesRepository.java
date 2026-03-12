package se.jimmy.labappuser.repository;

import se.jimmy.labappuser.entity.ChatMessages;

import java.util.List;

public interface ChatMessagesRepository {
    ChatMessages save(ChatMessages chatMessages);
    List<ChatMessages> findAll();
}
