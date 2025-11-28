package ru.jabki.filmplus.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.jabki.filmplus.model.Feedback;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FeedbackMapper implements RowMapper<Feedback> {

    @Override
    public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Feedback.builder()
                .id(rs.getLong("id"))
                .userId(rs.getLong("user_id"))
                .filmId(rs.getLong("film_id"))
                .content(rs.getString("content"))
                .build();
    }
}
