package ru.jabki.filmplus.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.jabki.filmplus.exception.FeedbackException;
import ru.jabki.filmplus.exception.UserException;
import ru.jabki.filmplus.model.Feedback;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    private static final Set<Feedback> feedbacks = new HashSet<>();

    private final UserService userService;
    private final FilmService filmService;

    public FeedbackService(UserService userService, FilmService filmService) {
        this.userService = userService;
        this.filmService = filmService;
    }

    public Feedback create(final Feedback feedback) {
        validate(feedback);
        final Feedback existFeedback = getFeedback(feedback.getUserId(), feedback.getFilmId());

        if (!(existFeedback == null)) {
            throw new FeedbackException("Пользователь может оставить один отзыв о фильме");
        }
        feedback.setId((long) (feedbacks.size() + 1));
        feedbacks.add(feedback);
        return feedback;
    }

    public Feedback update(final Feedback feedback) {
        validate(feedback);
        final Feedback existFeedback = getFeedback(feedback.getUserId(), feedback.getFilmId());

        if (existFeedback == null) {
            throw new FeedbackException("Отзыв не найден");
        }
        return existFeedback;
    }

    public Feedback getFeedback(final Long userId, final Long filmId) {
        return feedbacks.stream()
                .filter(f -> f.getUserId().equals(userId)
                        && f.getFilmId().equals(filmId))
                .findFirst()
                .orElse(null);
    }

    public List<Feedback> getFeedbackByFilmId(final Long filmId) {
      final List<Feedback> feedbackByFilm = feedbacks.stream()
              .filter(f ->  f.getFilmId().equals(filmId))
              .toList();

        return feedbackByFilm;
    }

    private void validate (final Feedback feedback) {
        if (feedback == null) {
            throw new UserException("Данные не определены");
        }
        if ((feedback.getLike() == 0) && !StringUtils.hasText(feedback.getText())) {
            throw new UserException("Если хотите оценить фильм, нужно или поставить лайк или написать отзыв");
        }

        userService.getById(feedback.getUserId());
        filmService.getById(feedback.getFilmId());
    }
}
