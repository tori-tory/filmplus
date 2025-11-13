package ru.jabki.filmplus.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.jabki.filmplus.exception.UserException;
import ru.jabki.filmplus.model.User;

import java.time.LocalDate;
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
        existUser.setLogin(user.getLogin());
        existUser.setBirthday(user.getBirthday());
        return existUser;
    }

    public void delete(final Long id) {
        users.remove(getById(id));
    }

    public User addFriend(final Long userId, final Long friendId) {
        User user = getById(userId);
        User friend = getById(friendId);

        if (userId.equals(friendId)) {
            throw new UserException("Нельзя добавить самого себя в друзья");
        }

        user.getFriends().add(friendId);
        return user;
    }

    private void validate(final User user) {
        if (user == null) {
            throw new UserException("Пользователь не задан");
        }
        if (!StringUtils.hasText(user.getName())) {
            throw new UserException("Имя пользователя не может быть пустым");
        }
        if (!StringUtils.hasText(user.getEmail())) {
            throw new UserException("Email пользователя не может быть пустым");
        }
        if (!StringUtils.hasText(user.getLogin())) {
            throw new UserException("Login пользователя не может быть пустым");
        }
        if (user.getBirthday() == null) {
            throw new UserException("Дата рождения не может быть пустой");
        }
        if (!user.getBirthday().isBefore(LocalDate.now())) {
            throw new UserException("Дата рождения не может быть из будущего");
        }
        if (user.getFriends() == null) {
            user.setFriends(new HashSet<>());
        } else {
            user.getFriends().remove(0L); // очищаем ошибочный id = 0
        }
    }
}