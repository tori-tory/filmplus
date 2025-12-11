package ru.jabki.filmplus.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.jabki.filmplus.model.Friend;

@Repository
@AllArgsConstructor
public class FriendRepository {

    private static final String INSERT = """
            INSERT INTO filmplus.friend (user_id, friend_id)
            VALUES (:user_id, :friend_id)
            RETURNING *
            """;

    private final FriendMapper friendMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Friend insert(Friend friend) {
        return jdbcTemplate.queryForObject(INSERT, friendToSql(friend), friendMapper);
    }

    private MapSqlParameterSource friendToSql(Friend friend) {
        final MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("user_id", friend.getUserId());
        params.addValue("friend_id", friend.getFriendId());
        return params;
    }
}