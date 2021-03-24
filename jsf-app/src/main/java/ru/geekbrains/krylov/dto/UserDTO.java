package ru.geekbrains.krylov.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.krylov.entities.User;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Andrey Krylov on 24.03.2021
 * @project geek-java-ee
 */

@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String login;

    private String password;

    private Set<RoleDTO> roles;

    public UserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.roles = new HashSet<>();
        user.getRoles().forEach(r -> this.roles.add(new RoleDTO(r)));
    }
}
