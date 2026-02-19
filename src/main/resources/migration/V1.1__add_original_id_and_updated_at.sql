ALTER TABLE movie
    ADD original_id BIGINT;

ALTER TABLE movie
    ALTER COLUMN original_id SET NOT NULL;

ALTER TABLE movie_rating
    ADD updated_at TIMESTAMP WITHOUT TIME ZONE;

ALTER TABLE movie_votes
    ADD updated_at TIMESTAMP WITHOUT TIME ZONE;