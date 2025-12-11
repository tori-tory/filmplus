package ru.jabki.filmplus.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.jabki.filmplus.model.Feedback;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Component
public class FeedbackMapper implements RowMapper<Feedback> {

    @Override
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
        Timestamp ts = rs.getTimestamp("created_at");
        OffsetDateTime createdAt = (ts != null) ? ts.toInstant().atOffset(ZoneOffset.UTC) : null;
        return Feedback.builder()
                .id(rs.getLong("id"))
                .userId(rs.getLong("user_id"))
                .filmId(rs.getLong("film_id"))
                .reviewText(rs.getString("review_text"))
                .createdAt(createdAt)
                .build();
    }
}