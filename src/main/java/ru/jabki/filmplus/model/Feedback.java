package ru.jabki.filmplus.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class Feedback {

    private Long id;
    private Long userId;
    private Long filmId;
    private String text;
    @Min(value = 0, message = "Поле 'like' должно быть не меньше 0")
    @Max(value = 1, message = "Поле 'like' должно быть не больше 1")
    private int like;

    public Feedback(final Long id, final Long filmId, final Long userId,
                    final String text, final int like) {
        this.id = id;
        this.filmId = filmId;
        this.userId = userId;
        this.text = text;
        this.like = like;
    }

    public Long getId() {
        return this.id;
    }
     public void setId(Long id) {
        this.id = id;
     }
    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFilmId() {
        return this.filmId;
    }

    public void setFilmId(Long filmId) {
        this.filmId = filmId;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLike() {
        return this.like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
