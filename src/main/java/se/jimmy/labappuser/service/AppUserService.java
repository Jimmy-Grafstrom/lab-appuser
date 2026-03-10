package se.jimmy.labappuser.service;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import se.jimmy.labappuser.entity.AppUser;
import se.jimmy.labappuser.repository.AppUserRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class AppUserService {

    private static final Logger logger = Logger.getLogger(AppUserService.class.getName());

    @Inject
    private AppUserRepository appUserRepository;

    @Transactional
    public void saveUser(AppUser appUser) {
            appUserRepository.save(appUser);
            logger.log(Level.INFO, "User {0} saved successfully!", appUser.getUsername());
    }

    public List<AppUser> getAllUsers() {
        logger.fine("Fetching all users");
        return appUserRepository.findAll();
    }

    public AppUser login(String username, String password) {
        AppUser appUser = appUserRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .orElse(null);
        if (appUser != null) {
            logger.log(Level.INFO, "User {0} logged in successfully!", appUser.getUsername());
        } else  {
            logger.log(Level.WARNING, "Wrong username or password!");
        }
        return appUser;
    }

}
