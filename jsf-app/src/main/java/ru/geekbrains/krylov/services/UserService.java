package ru.geekbrains.krylov.services;

import ru.geekbrains.krylov.dto.UserDTO;
import ru.geekbrains.krylov.entities.User;
import ru.geekbrains.krylov.repositories.UserRepositoryImpl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Andrey Krylov on 24.03.2021
 * @project geek-java-ee
 */

@Stateless
public class UserService {

    @EJB
    private UserRepositoryImpl userRepository;

    public void saveOrUpdate(UserDTO user) {
        User saved = userRepository.saveOrUpdate(new User(user));
        user.setId(saved.getId());
    }

    @TransactionAttribute
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @TransactionAttribute
    public UserDTO findById(Long id) {
        return new UserDTO(userRepository.findById(id));
    }

    @TransactionAttribute
    public boolean existsById(Long id) {
        return userRepository.findById(id) != null;
    }

    @TransactionAttribute
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());
    }
}
