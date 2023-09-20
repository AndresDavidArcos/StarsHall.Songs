package com.nervCode.starHall.Songs.dao;

import com.nervCode.starHall.Songs.Entities.Song;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SongDao {

    EntityManager entityManager;
    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Transactional
    public Song createAndReturnSong(Song song){
        return entityManager.merge(song);
    }
}
