package se.jimmy.labappuser.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import se.jimmy.labappuser.entity.ChatMessages;

import java.util.List;

@ApplicationScoped
public class JpaChatMessagesRepository implements ChatMessagesRepository{
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    @Override
    public ChatMessages save(ChatMessages chatMessages) {
        entityManager.persist(chatMessages);
        return chatMessages;
    }

    @Override
    public List<ChatMessages> findAll() {
        return entityManager.createQuery("SELECT c FROM ChatMessages c", ChatMessages.class).getResultList();
    }
}
