package ru.jabki.filmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.jabki.filmplus.model.Film;
import ru.jabki.filmplus.service.FilmService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/film")
@Tag(name = "Фильмы")
public class FilmController {

    private final FilmService filmService;

    @PostMapping
    @Operation(summary = "Создать фильм")
    public Film create(@RequestBody final Film film) {
        return filmService.create(film);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить фильм по id")
    public Film getById(@PathVariable("id") Long id) {
        return filmService.getById(id);
    }

    @PatchMapping
    @Operation(summary = "Обновить фильм")
    public Film update(@RequestBody final Film film) {
        return filmService.update(film);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить фильм по id")
    public void delete(@PathVariable("id") Long id) {
        filmService.delete(id);
    }

    @GetMapping("/searchByName")
    @Operation(summary = "Поиск фильма по названию")
    public List<Film> searchByName(@RequestParam(name = "name", required = false)
                                   @Parameter(example = "Белое солнце пустыни") String name) {
        return filmService.searchByName(name);
    }
}