package ru.jabki.filmplus.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Feedback {

    private Long id;
    private Long userId;
    private Long filmId;
    private String content;

    @Min(value = 0, message = "Поле 'like' должно быть не меньше 0")
    @Max(value = 1, message = "Поле 'like' должно быть не больше 1")
    private int like;
}
