package ru.jabki.filmplus.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.jabki.filmplus.model.Film;
import ru.jabki.filmplus.model.Genre;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FilmMapper implements RowMapper<Film> {

    @Override
    public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
        Date date = rs.getDate("release_date");
        LocalDate releaseDate = (date != null) ? date.toLocalDate() : null;

        String genreStr = rs.getString("genre");
        Set<Genre> genres = (genreStr != null && !genreStr.isEmpty())
                ? Arrays.stream(genreStr.split(",")).map(Genre::valueOf).collect(Collectors.toSet())
                : new HashSet<>();

        return Film.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .releaseDate(releaseDate)
                .duration(rs.getInt("duration"))
                .genres(genres)
                .build();
    }
}