package com.nervCode.starHall.Songs.dao;

import com.nervCode.starHall.Songs.Entities.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GenreDao {
    private EntityManager entityManager;
    @Autowired
    public GenreDao(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Optional<Genre> findGenreByName(String name){
        TypedQuery<Genre> query = entityManager.createQuery("from Genre where genre = :data", Genre.class);
        query.setParameter("data", name);

        try {
            Genre result = query.getSingleResult();
            return Optional.ofNullable(result);
        } catch (NoResultException | NonUniqueResultException e) {
            return Optional.empty();
        }
    }
    @Transactional
    public Genre createAndReturnGenre(Genre genre){
        return entityManager.merge(genre);
    }


}
