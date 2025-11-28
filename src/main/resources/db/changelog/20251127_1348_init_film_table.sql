CREATE TABLE filmplus.film (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    releaseDate DATE,
    duration BIGINT NOT NULL,
    genres VARCHAR NOT NULL
);