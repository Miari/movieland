CREATE TABLE users (
 	id serial PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL CONSTRAINT email_unique UNIQUE,
    password VARCHAR(50) NOT NULL
);

CREATE TABLE genres (
    id serial PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE countries (
    id serial PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE movies (
    id serial PRIMARY KEY,
    nameRu VARCHAR(200) NOT NULL,
    nameEn VARCHAR(200) NOT NULL,
    yearOfProduction integer NOT NULL CONSTRAINT valid_year CHECK (yearOfProduction > 1894),
    description text NOT NULL,
    rating numeric NOT NULL,
    price numeric NOT NULL
);

CREATE TABLE posters (
  	id serial PRIMARY KEY,
    id_movie integer NOT NULL REFERENCES movies ON DELETE RESTRICT,
    link VARCHAR(250) NOT NULL
);

CREATE TABLE country_movie (
    id serial PRIMARY KEY,
    id_country integer NOT NULL REFERENCES countries ON DELETE RESTRICT,
    id_movie integer NOT NULL REFERENCES movies ON DELETE RESTRICT
);

CREATE TABLE genre_movie (
      id serial PRIMARY KEY,
      id_genre integer NOT NULL REFERENCES genres ON DELETE RESTRICT,
      id_movie integer NOT NULL REFERENCES movies ON DELETE RESTRICT
);

CREATE TABLE reviews (
    id serial PRIMARY KEY,
    id_user integer NOT NULL REFERENCES users ON DELETE RESTRICT,
    id_movie integer NOT NULL REFERENCES movies ON DELETE RESTRICT,
    description VARCHAR(1500) NOT NULL
);


