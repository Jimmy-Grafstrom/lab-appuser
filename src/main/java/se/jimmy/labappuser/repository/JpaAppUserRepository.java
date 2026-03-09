package se.jimmy.labappuser.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import se.jimmy.labappuser.entity.AppUser;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class JpaAppUserRepository implements AppUserRepository {

    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    @Override
    public AppUser save(AppUser user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public List<AppUser> findAll() {
        return entityManager.createQuery("SELECT u FROM AppUser u", AppUser.class).getResultList();
    }

    @Override
    public Optional<AppUser> findByUsername(String username) {
        try {
            AppUser user = entityManager.createQuery
                            ("SELECT u FROM AppUser u WHERE u.username = :uname", AppUser.class)
                    .setParameter("uname", username)
                    .getSingleResult();
            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
