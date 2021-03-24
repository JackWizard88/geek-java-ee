package ru.geekbrains.krylov.entities;

import lombok.Data;
import ru.geekbrains.krylov.dto.RoleDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Table(name = "roles")
public class Role implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role() {

    }

    public Role(@NotNull String name) {
        this.name = name;
    }

    public Role(RoleDTO r) {
        this.id = r.getId();
        this.name = r.getName();
    }

}
