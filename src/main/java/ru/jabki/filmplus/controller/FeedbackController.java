package ru.jabki.filmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.jabki.filmplus.model.Feedback;
import ru.jabki.filmplus.service.FeedbackService;

@RestController
@AllArgsConstructor

@RequestMapping("/api/v1/feedback")
@Tag(name = "Отзывы")

public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping()
    @Operation(summary = "Оставить отзыв")
    public Feedback create(@RequestBody final Feedback feedback) {
        return feedbackService.create(feedback);
    }

    @PatchMapping()
    @Operation(summary = "Изменить отзыв")
    public Feedback update(@RequestBody final Feedback feedback) {
        return feedbackService.update(feedback);
    }

    @DeleteMapping()
    @Operation(summary = "Удалить отзыв по id")
    public void delete(@PathVariable("id") Long id) {feedbackService.delete(id);
    }
}