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
            INSERT INTO filmplus."like" (user_id, film_id)
            VALUES (:user_id, :film_id)
            RETURNING *
            """;

    private final LikeMapper likeMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Like insert(Like like) {
        return jdbcTemplate.queryForObject(INSERT, likeToSql(like), likeMapper);
    }

    private MapSqlParameterSource likeToSql(Like like) {
        final  MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("user_id", like.getUserId());
        params.addValue("film_id", like.getFilmId());
        return params;
    }
}