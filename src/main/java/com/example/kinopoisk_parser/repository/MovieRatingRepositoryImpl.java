package com.example.kinopoisk_parser.repository;

import com.example.kinopoisk_parser.model.MovieRating;
import com.example.kinopoisk_parser.repository.interfaces.MovieRatingRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public class MovieRatingRepositoryImpl implements MovieRatingRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public <S extends MovieRating> S save(S entity) {
        if (entity.getId() == null) {
            this.em.persist(entity);
        } else {
            entity = em.merge(entity);
        }
        return entity;
    }

    @Override
    public <S extends MovieRating> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<MovieRating> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<MovieRating> findAll() {
        return null;
    }

    @Override
    public Iterable<MovieRating> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(MovieRating entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends MovieRating> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
