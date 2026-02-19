ALTER TABLE movie
    ADD CONSTRAINT uc_movie_original UNIQUE (original_id);