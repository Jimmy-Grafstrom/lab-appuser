package se.jimmy.labappuser.repository;

import jakarta.data.repository.Repository;
import se.jimmy.labappuser.entity.AppUser;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository {

    AppUser save (AppUser user);
    List<AppUser> findAll();
    Optional<AppUser> findByUsername(String username);
}
