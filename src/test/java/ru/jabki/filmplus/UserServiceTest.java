package ru.jabki.filmplus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jabki.filmplus.exception.UserException;
import ru.jabki.filmplus.model.User;
import ru.jabki.filmplus.repository.UserRepository;
import ru.jabki.filmplus.service.UserService;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_valid() {
        final User user = getUser();

        Mockito.when(userRepository.insert(user)).thenReturn(user);

        User result = userService.create(user);

        assertThat(result).isEqualTo(user);
        verify(userRepository).insert(user);
    }

    @Test
    void createUser_invalidData_nullName_throwUserException() {
        final User user = getUser();
        user.setName(null);

        final UserException exception = assertThrows(
                UserException.class,
                () -> userService.create(user)
        );

        assertEquals(exception.getMessage(), "Имя пользователя не может быть пустым");

        verify(userRepository, never()).insert(any());
    }

    private User getUser() {
        return User.builder()
                .id(1L)
                .name("ФИО")
                .email("почта")
                .login("пароль")
                .birthday(LocalDate.parse("2020-01-01"))
                .build();
    }
}
