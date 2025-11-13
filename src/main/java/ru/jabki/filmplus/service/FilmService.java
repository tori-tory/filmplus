package ru.jabki.filmplus.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.jabki.filmplus.exception.FilmException;
import ru.jabki.filmplus.model.Film;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FilmService {

    private static final Set<Film> films = new HashSet<>();

    public Film create(final Film film) {
        validate(film);
        film.setId((long) (films.size() + 1));
        films.add(film);
        return film;
    }
    public Film getById(final Long id) {
        final Film film = films.stream()
                .filter(f -> f.getId() == id)
                .findFirst()
                .orElse(null);
        if (film == null) {
            throw new FilmException("Фильм не найден");
        }
        return film;
    }

    public List<Film> getByName(final String query) {
        if (query == null || query.isBlank()) {
            throw new FilmException("Не заданы параметры поиска фильма");
        }
        String lowerCaseQuery = query.toLowerCase();
        final List<Film> foundFilms = films.stream()
                .filter(f -> f.getName().toLowerCase().contains(lowerCaseQuery))
                .toList();
        if (foundFilms.isEmpty()) {
            throw new FilmException("Фильм не найден");
        }
        return foundFilms;
    }

    public Film update(final Film film) {
        validate(film);
        final Film existFilm = getById(film.getId());
        existFilm.setName(film.getName());
        existFilm.setDescription(film.getDescription());
        existFilm.setReleaseDate(film.getReleaseDate());
        existFilm.setDuration(film.getDuration());
        existFilm.setGenres(film.getGenres());
        return existFilm;
    }

    public void delete(final Long id) {
        films.remove(getById(id));
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
