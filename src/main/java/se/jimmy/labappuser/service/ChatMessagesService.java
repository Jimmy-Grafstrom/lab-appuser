package se.jimmy.labappuser.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import se.jimmy.labappuser.entity.ChatMessages;
import se.jimmy.labappuser.repository.ChatMessagesRepository;

import java.util.List;

@ApplicationScoped
public class ChatMessagesService {

    @Inject
    private ChatMessagesRepository chatMessagesRepository;

    @Transactional
    public void save(ChatMessages chatMessages) {
        chatMessagesRepository.save(chatMessages);
    }

    public List<ChatMessages> getAllMessages() {
        return chatMessagesRepository.findAll();
    }

}
