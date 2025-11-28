CREATE TABLE filmplus.feedback (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES filmplus.user (id),
    film_id INTEGER REFERENCES filmplus.film (id),
    content VARCHAR NOT NULL
);