package ru.geekbrains.krylov.services;

import ru.geekbrains.krylov.dto.RoleDTO;
import ru.geekbrains.krylov.repositories.RoleRepositoryImpl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrey Krylov on 24.03.2021
 * @project geek-java-ee
 */

@Stateless
public class RoleService {

    @Inject
    private RoleRepositoryImpl roleRepository;

    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(RoleDTO::new)
                .collect(Collectors.toList());
    }
}
