CREATE SEQUENCE IF NOT EXISTS movie_rating_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS movie_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS movie_votes_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE movie
(
    id            BIGINT       NOT NULL,
    position      INTEGER      NOT NULL,
    original_name VARCHAR(255) NOT NULL,
    year          date,
    CONSTRAINT pk_movie PRIMARY KEY (id)
);

CREATE TABLE movie_rating
(
    id                   BIGINT NOT NULL,
    kp                   FLOAT  NOT NULL,
    imdb                 FLOAT  NOT NULL,
    film_critics         FLOAT  NOT NULL,
    russian_film_critics FLOAT  NOT NULL,
    movie_id             BIGINT NOT NULL,
    CONSTRAINT pk_movie_rating PRIMARY KEY (id)
);

CREATE TABLE movie_votes
(
    id                   BIGINT  NOT NULL,
    kp                   INTEGER NOT NULL,
    imdb                 INTEGER NOT NULL,
    film_critics         INTEGER NOT NULL,
    russian_film_critics INTEGER NOT NULL,
    movie_id             BIGINT  NOT NULL,
    CONSTRAINT pk_movie_votes PRIMARY KEY (id)
);

ALTER TABLE movie_rating
    ADD CONSTRAINT FK_MOVIE_RATING_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movie (id);

ALTER TABLE movie_votes
    ADD CONSTRAINT FK_MOVIE_VOTES_ON_MOVIE FOREIGN KEY (movie_id) REFERENCES movie (id);