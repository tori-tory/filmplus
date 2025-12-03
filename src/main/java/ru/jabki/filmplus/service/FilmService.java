package ru.jabki.filmplus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.jabki.filmplus.exception.FilmException;
import ru.jabki.filmplus.exception.UserException;
import ru.jabki.filmplus.model.Film;
import ru.jabki.filmplus.repository.FilmRepository;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;

    @Transactional(rollbackFor = Exception.class)
    public Film create(final Film film) {
        validate(film);
        filmRepository.insert(film);
        return film;
    }

    @Transactional(readOnly = true)
    public Film getById(final Long id) {
        final Film film = filmRepository.getById(id);
        if (film == null) {
            throw new UserException("Фильм не найден");
        }
        return film;
    }

    @Transactional(rollbackFor = Exception.class)
    public Film update(final Film film) {
        validate(film);
        final Film existFilm = getById(film.getId());
        existFilm.setName(film.getName());
        existFilm.setDescription(film.getDescription());
        existFilm.setReleaseDate(film.getReleaseDate());
        existFilm.setDuration(film.getDuration());
        existFilm.setGenres(film.getGenres());
        filmRepository.update(existFilm);
        return existFilm;
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(final Long id) {
        filmRepository.delete(id);
    }

    private void validate(final Film film) {
        if (film == null) {
            throw new FilmException("Фильм не задан");
        }
        if (!StringUtils.hasText(film.getName())) {
            throw new FilmException("Название фильма не может быть пустым");
        }
        if (!StringUtils.hasText(film.getDescription())) {
            throw new FilmException("Описание фильма не может быть пустым");
        }
        if (!film.getReleaseDate().isBefore(LocalDate.now())) {
            throw new FilmException("Дата релиза не может быть из будущего");
        }
        if ((film.getDuration() == null) || !(film.getDuration() > 0)) {
            throw new FilmException("Продолжительность фильма должна быть положительной");
        }
        if (film.getGenres().isEmpty()){
            throw new FilmException("Должен быть выбран хотя бы один жанр");
        }
    }
}