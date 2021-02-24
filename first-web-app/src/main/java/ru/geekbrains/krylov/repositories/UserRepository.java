package ru.geekbrains.krylov.repositories;

import ru.geekbrains.krylov.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository implements Repository <User> {

    private final Map<Long, User> userMap = new ConcurrentHashMap<>();
    private final AtomicLong identity = new AtomicLong(0);

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public User findById(Long id) {
        return userMap.get(id);
    }

    @Override
    public void saveOrUpdate(User user) {
        if (user.getId() == null) {
            Long id = identity.incrementAndGet();
            user.setId(id);
        }
        userMap.put(user.getId(), user);
    }

    @Override
    public User deleteById(Long id) {
        return userMap.remove(id);
    }
}
