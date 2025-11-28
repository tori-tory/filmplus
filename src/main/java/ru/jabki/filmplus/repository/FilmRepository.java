package ru.jabki.filmplus.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.jabki.filmplus.exception.BadRequestException;
import ru.jabki.filmplus.model.Film;

@Repository
@AllArgsConstructor
public class FilmRepository {

    private static final String INSERT = """
        INSERT INTO filmplus.film(name, description, releasedate, duration)
        VALUES (:name, :description, :releasedate, :duration)
        RETURNING *
    """;

    private static final String UPDATE = """
            UPDATE filmplus.film
            SET name = :name, description = :description, releasedate = :releasedate, duration = :duration
            WHERE id = :id
            RETURNING *
            """;

    private static final String DELETE = """
            DELETE FROM filmplus.film
            WHERE id = :id
            """;

    private static final String GET_BY_ID = """
            SELECT *
            FROM filmplus.film
            WHERE id = :id
            """;

    private final FilmMapper userMapper;
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Film insert(Film film){
        return jdbcTemplate.queryForObject(INSERT, filmToSql(film), userMapper);
    }

    public Film update(Film film){
        return jdbcTemplate.queryForObject(UPDATE, filmToSql(film), userMapper);
    }

    public void delete(Long id){
        try {
            jdbcTemplate.update(DELETE, new MapSqlParameterSource("id", id));
        } catch (Exception e) {
            throw new BadRequestException(String.format("Фильм с id %d не найден", id));
        }
    }

    public Film getById(Long id) {
        try {
            return jdbcTemplate.queryForObject(GET_BY_ID, new MapSqlParameterSource("id", id), userMapper);
        } catch (Exception e) {
            throw new BadRequestException(String.format("Фильм с id %d не найден", id));
        }
    }

    private MapSqlParameterSource filmToSql(Film film) {
        final  MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id", film.getId());
        params.addValue("name", film.getName());
        params.addValue("description", film.getDescription());
        params.addValue("releasedate", film.getReleaseDate());
        params.addValue("duration", film.getDuration());
        return params;
    }
}
