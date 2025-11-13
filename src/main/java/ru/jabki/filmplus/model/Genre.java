package ru.jabki.filmplus.model;

public enum Genre {
    COMEDY("Комедия"),
    HORROR("Ужасы"),
    ACTION("Боевик"),
    THRILLER("Триллер"),
    DRAMA("Драма"),
    SCI_FI("Фантастика"),
    ROMANCE("Мелодрама"),
    DOCUMENTARY("Документальный"),
    ANIMATION("Анимация");

    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
