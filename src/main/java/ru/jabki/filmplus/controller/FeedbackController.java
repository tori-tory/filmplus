package ru.jabki.filmplus.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.jabki.filmplus.model.Feedback;
import ru.jabki.filmplus.service.FeedbackService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/feedback")
@Tag(name = "Отзывы")

public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping()
    @Operation(summary = "Оставить отзыв")
    public Feedback create(@RequestBody final Feedback feedback) {
        return feedbackService.create(feedback);
    }

    @GetMapping("{filmId}")
    @Operation(summary = "Посмотреть отзывы по фильму")
    public List<Feedback> getFeebackByFilmId(final Long filmId) {
        return feedbackService.getFeedbackByFilmId(filmId);
    }

    @PatchMapping()
    @Operation(summary = "Изменить отзыв")
    public Feedback update(@RequestBody final Feedback feedback) {
        return feedbackService.update(feedback);
    }
}
