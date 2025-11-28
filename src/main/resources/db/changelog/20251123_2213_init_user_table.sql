CREATE SCHEMA filmplus;

CREATE TABLE filmplus.user (
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    login VARCHAR NOT NULL,
    birthday DATE
);