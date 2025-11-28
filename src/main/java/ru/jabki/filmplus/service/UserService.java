package ru.jabki.filmplus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.jabki.filmplus.exception.UserException;
import ru.jabki.filmplus.model.User;
import ru.jabki.filmplus.repository.UserRepository;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public User create(final User user) {
        validate(user);
        userRepository.insert(user);
        return user;
    }

    @Transactional(readOnly = true)
    public User getById(final Long id) {
        final User user = userRepository.getById(id);
        if (user == null) {
            throw new UserException(String.format("Пользователь id = %d не найден", id));
        }
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    public User update(final User user) {
        validate(user);
        final User existUser = getById(user.getId());
        existUser.setName(user.getName());
        existUser.setEmail(user.getEmail());
        existUser.setLogin(user.getLogin());
        existUser.setBirthday(user.getBirthday());
        userRepository.update(existUser);
        return existUser;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(final Long id) {
        userRepository.delete(id);
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
    }
}