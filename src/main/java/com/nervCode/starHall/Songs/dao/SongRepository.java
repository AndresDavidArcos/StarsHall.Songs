package com.nervCode.starHall.Songs.dao;

import com.nervCode.starHall.Songs.Entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Integer> {

}
