package ru.jabki.filmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.jabki.filmplus.model.Like;
import ru.jabki.filmplus.service.LikeService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/like")
@Tag(name = "Лайки")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    @Operation(summary = "Поставить лайк фильму")
    public Like create(@RequestBody final Like like) {
        return likeService.create(like);
    }

    @DeleteMapping("/delete-like")
    @Operation(summary = "Удалить лайк")
    public void delete(@RequestParam Long userId, @RequestParam Long filmId) {
        likeService.delete(userId, filmId);
    }
}