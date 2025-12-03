CREATE TABLE filmplus.like (
    id SERIAL PRIMARY KEY,
    film_id INTEGER REFERENCES filmplus.film (id),
    user_id INTEGER REFERENCES filmplus.user (id),
	CONSTRAINT like_pk UNIQUE (user_id, film_id)
);