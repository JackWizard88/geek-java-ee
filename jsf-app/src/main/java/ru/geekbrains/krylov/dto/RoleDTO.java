package ru.geekbrains.krylov.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.krylov.entities.Role;

/**
 * @author Andrey Krylov on 24.03.2021
 * @project geek-java-ee
 */

@Data
@NoArgsConstructor
public class RoleDTO {

    private long id;

    private String name;

    public RoleDTO(Role r) {
        this.id = r.getId();
        this.name = r.getName();
    }
}
