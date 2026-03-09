package se.jimmy.labappuser.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import se.jimmy.labappuser.entity.AppUser;
import se.jimmy.labappuser.repository.AppUserRepository;

import java.util.List;

@ApplicationScoped
public class AppUserService {

    @Inject
    private AppUserRepository appUserRepository;

    @Transactional
    public void saveUser(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public AppUser login(String username, String password) {
        return appUserRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .orElse(null);
    }

}
