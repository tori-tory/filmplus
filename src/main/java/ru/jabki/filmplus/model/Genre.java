package ru.jabki.filmplus.model;

public enum Genre {
    COMEDY(1, "Комедия"),
    HORROR(2, "Ужасы"),
    ACTION(3, "Боевик"),
    THRILLER(4, "Триллер"),
    DRAMA(5, "Драма"),
    SCI_FI(6, "Фантастика"),
    ROMANCE(7, "Мелодрама"),
    DOCUMENTARY(8, "Документальный"),
    ANIMATION(9, "Анимация");

    public int getId() {
        return this.id;
    }

    private final int id;
    private final String displayName;

    Genre(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
