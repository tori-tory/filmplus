package ru.jabki.filmplus;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jabki.filmplus.exception.FilmException;
import ru.jabki.filmplus.model.Film;
import ru.jabki.filmplus.model.Genre;
import ru.jabki.filmplus.repository.FilmRepository;
import ru.jabki.filmplus.service.FilmService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FilmServiceTest {

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private FilmService filmService;

    @Test
    void createFilm_valid() {
        final Film film = getFilm();

        Mockito.when(filmRepository.insert(film)).thenReturn(film);

        Film result = filmService.create(film);

        assertThat(result).isEqualTo(film);
        verify(filmRepository).insert(film);

        assertEquals(1, film.getId());
        assertEquals("Фильм", film.getName());
        assertEquals(LocalDate.of(2020, 01, 01), film.getReleaseDate());
        assertEquals(100, film.getDuration());
    }

    @Test
    void createFilm_invalidData_nullName_throwFilmException() {
        final Film film = getFilm();
        film.setName(null);

        final FilmException exception = assertThrows(
                FilmException.class,
                () -> filmService.create(film)
        );

        assertEquals(exception.getMessage(), "Название фильма не может быть пустым");

        verify(filmRepository, never()).insert(any());
    }

    private Film getFilm() {
        return Film.builder()
                .id(1L)
                .name("Фильм")
                .description("прампампам")
                .releaseDate(LocalDate.parse("2020-01-01"))
                .duration(100)
                .genres(new HashSet<Genre>(Arrays.asList(Genre.DRAMA)))
                .build();
    }
}
