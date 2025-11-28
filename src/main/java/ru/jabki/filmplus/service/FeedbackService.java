package ru.jabki.filmplus.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.jabki.filmplus.exception.UserException;
import ru.jabki.filmplus.model.Feedback;
import ru.jabki.filmplus.repository.FeedbackRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserService userService;
    private final FilmService filmService;

    @Transactional(rollbackFor = Exception.class)
    public Feedback create(final Feedback feedback) {
        validate(feedback);
        feedbackRepository.insert(feedback);
        return feedback;
    }

    @Transactional(rollbackFor = Exception.class)
    public Feedback update(final Feedback feedback) {
        validate(feedback);
        feedbackRepository.update(feedback);
        return feedback;
    }

    @Transactional(readOnly = true)
    public Feedback getById(final Long id) {
        final Feedback feedback = feedbackRepository.getById(id);
        if (feedback == null) {
            throw new UserException("Пользователь не найден");
        }
        return feedback;
    }

    private void validate (final Feedback feedback) {
        if (feedback == null) {
            throw new UserException("Данные не определены");
        }
        if ((feedback.getLike() == 0) && !StringUtils.hasText(feedback.getContent())) {
            throw new UserException("Если хотите оценить фильм, нужно или поставить лайк или написать отзыв");
        }

        userService.getById(feedback.getUserId());
        filmService.getById(feedback.getFilmId());
    }
}
