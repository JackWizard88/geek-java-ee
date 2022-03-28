package ru.geekbrains.krylov.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.geekbrains.krylov.dto.UserDTO;
import ru.geekbrains.krylov.services.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UserController implements Serializable {

    private final static Logger logger = LogManager.getLogger(UserController.class);

    @EJB
    private UserService userService;

    private UserDTO user;
    private List<UserDTO> userList;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        userList = userService.getAllUsers();
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String createUser() {
        this.user = new UserDTO();
        return "/product_form.xhtml?faces-redirect=true";
    }

    public List<UserDTO> getAllUsers() {
        return userList;
    }

    public String editUser(UserDTO user) {
        this.user = user;
        return "/user_form.xhtml?faces-redirect=true";
    }

    public void deleteUser(UserDTO user) {
        userService.delete(user.getId());
    }

    public String saveUser() {
        userService.saveOrUpdate(user);
        return "/user.xhtml?faces-redirect=true";
    }
}
