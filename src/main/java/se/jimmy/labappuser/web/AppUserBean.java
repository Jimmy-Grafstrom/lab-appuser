package se.jimmy.labappuser.web;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import se.jimmy.labappuser.entity.AppUser;
import se.jimmy.labappuser.service.AppUserService;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AppUserBean implements Serializable {
    private String username;
    private String password;

    @Inject
    private AppUserService appUserService;

    public void save() {
        AppUser appUser = new AppUser(username, password);
        appUserService.saveUser(appUser);
        this.username = "";
        this.password = "";
    }

    public List<AppUser> getAllUsers() {
        return appUserService.getAllUsers();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
