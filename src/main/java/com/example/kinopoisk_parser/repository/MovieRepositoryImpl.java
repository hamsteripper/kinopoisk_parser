package com.example.kinopoisk_parser.repository;

import com.example.kinopoisk_parser.model.Movie;
import com.example.kinopoisk_parser.repository.interfaces.MovieRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public class MovieRepositoryImpl implements MovieRepository {

    @PersistenceContext
    private EntityManager em;

    public MovieRepositoryImpl() {
    }

    @Override
    public <S extends Movie> S save(S entity) {
        if (entity.getId() == null) {
            this.em.persist(entity);
        } else {
            entity = em.merge(entity);
        }

        return entity;
    }

    @Override
    public <S extends Movie> Iterable<S> saveAll(Iterable<S> entities) {
        for (Movie entity: entities) {
            this.save(entity);
        }

        return null;
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return Optional.ofNullable(em.find(Movie.class, id));
    }

    public Movie findByOriginalId(Long originalId) {

        String jpql = "SELECT entity FROM Movie entity WHERE entity.originalId = :originalId";
        TypedQuery<Movie> query = em.createQuery(jpql, Movie.class).setParameter("originalId", originalId);

        return query.getSingleResultOrNull();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Movie> findAll() {
        return null;
    }

    @Override
    public Iterable<Movie> findAllById(Iterable<Long> longs) {
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
    public void delete(Movie entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Movie> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
