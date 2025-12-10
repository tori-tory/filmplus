package ru.jabki.filmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.jabki.filmplus.model.Feedback;
import ru.jabki.filmplus.service.FeedbackService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/feedback")
@Tag(name = "Отзывы")
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    @Operation(summary = "Оставить отзыв")
    public Feedback create(@RequestBody final Feedback feedback) {
        return feedbackService.create(feedback);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить отзыв по id")
    public Feedback getById(@PathVariable("id") Long id) {
        return feedbackService.getById(id);
    }
}