package ru.jabki.filmplus.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.jabki.filmplus.model.Like;

@Repository
@AllArgsConstructor
public class LikeRepository {

    private static final String INSERT = """
            INSERT INTO filmplus."like" (film_id, user_id)
            VALUES (:film_id, :user_id)
            RETURNING *
            """;

    private static final String DELETE = """
            DELETE FROM filmplus."like"
            WHERE user_id = :user_id
            AND film_id = :film_id
            """;

    private final LikeMapper likeMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Like insert(Like like) {
        return jdbcTemplate.queryForObject(
                INSERT,
                likeToSql(like),
                likeMapper);
    }

    public void delete(Long userId, Long filmId) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("film_id", filmId);

        jdbcTemplate.update(DELETE, params);
    }

    private MapSqlParameterSource likeToSql(Like like) {
        final  MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id", like.getId());
        params.addValue("user_id", like.getUserId());
        params.addValue("film_id", like.getFilmId());
        return params;
    }
}