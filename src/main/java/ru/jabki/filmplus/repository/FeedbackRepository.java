package ru.jabki.filmplus.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.jabki.filmplus.exception.BadRequestException;
import ru.jabki.filmplus.model.Feedback;

@Repository
@AllArgsConstructor
public class FeedbackRepository {

    private static final String INSERT = """
        INSERT INTO filmplus.feedback(user_id, film_id, review_text)
        VALUES (:user_id, :film_id, :review_text)
        RETURNING *
    """;

    private static final String GET_BY_ID = """
            SELECT *
            FROM filmplus.feedback
            WHERE id = :id
            """;

    private final FeedbackMapper feedbackMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Feedback insert(Feedback feedback) {
        return jdbcTemplate.queryForObject(INSERT, feedbackToSql(feedback), feedbackMapper);
    }

    public Feedback getById(Long id) {
        try {
            return jdbcTemplate.queryForObject(GET_BY_ID, new MapSqlParameterSource("id", id), feedbackMapper);
        } catch (Exception e) {
            throw new BadRequestException(String.format("Отзыв с id %d не найден", id));
        }
    }

    private MapSqlParameterSource feedbackToSql(Feedback feedback) {
        final  MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id", feedback.getId());
        params.addValue("user_id", feedback.getUserId());
        params.addValue("film_id", feedback.getFilmId());
        params.addValue("review_text", feedback.getReviewText());
        return params;
    }
}