package ru.jabki.filmplus.model;

import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
public class Feedback {

    private Long id;
    private Long userId;
    private Long filmId;
    private String reviewText;
    private OffsetDateTime createdAt;
}