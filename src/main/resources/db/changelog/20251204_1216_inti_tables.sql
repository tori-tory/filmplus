CREATE SCHEMA IF NOT EXISTS filmplus;

CREATE TABLE IF NOT EXISTS filmplus.user (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    login VARCHAR NOT NULL,
    birthday DATE NOT NULL,
	UNIQUE (login),
    UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS filmplus.film (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    release_date DATE NOT NULL,
    duration INTEGER NOT NULL,
    genre VARCHAR NOT NULL
);

CREATE TABLE IF NOT EXISTS filmplus.feedback (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES filmplus.user (id) NOT NULL,
    film_id INTEGER REFERENCES filmplus.film (id) NOT NULL,
    review_text TEXT NOT NULL,
	created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS filmplus.friend (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES filmplus.user (id) NOT NULL,
    friend_id INTEGER REFERENCES filmplus.user (id) NOT NULL,
	UNIQUE (user_id, friend_id)
);

CREATE TABLE IF NOT EXISTS filmplus.like (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES filmplus.user (id) NOT NULL,
    film_id INTEGER REFERENCES filmplus.film (id) NOT NULL,
	CONSTRAINT like_pk UNIQUE (user_id, film_id)
);

