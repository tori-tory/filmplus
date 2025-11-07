package ru.jabki.filmplus.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.jabki.filmplus.exception.UserException;
import ru.jabki.filmplus.model.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private static final Set<User> users = new HashSet<>();

    public User create(final User user) {
        validate(user);
        user.setId((long) (users.size() + 1));
        users.add(user);
        return user;
    }

    public User getById(final Long id) {
        final User user = users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
        if (user == null) {
            throw new UserException("Пользователь не найден");
        }
        return user;
    }

    public User update(final User user) {
        validate(user);
        final User existUser = getById(user.getId());
        existUser.setName(user.getName());
        existUser.setEmail(user.getEmail());
        return existUser;
    }

    public void delete(final Long id) {
        users.remove(getById(id));
    }

    private void validate(final User user) {
        if (user == null) {
            throw new UserException("User is null");
        }
        if (!StringUtils.hasText(user.getName())) {
            throw new UserException("User name is empty");
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new UserException("User email is empty");
        }
    }
}