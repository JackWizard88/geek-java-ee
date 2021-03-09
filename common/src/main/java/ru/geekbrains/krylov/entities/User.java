package ru.geekbrains.krylov.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    private Long id;

    private String firstName;

    private String secondName;
}
