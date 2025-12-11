package ru.jabki.filmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jabki.filmplus.model.Friend;
import ru.jabki.filmplus.service.FriendService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/friend")
@Tag(name = "Друзья")
public class FriendController {

    public final FriendService friendService;

    @PostMapping
    @Operation(summary = "Добавить друга")
    public Friend create(@RequestBody final Friend friend) {
        return friendService.create(friend);
    }
}