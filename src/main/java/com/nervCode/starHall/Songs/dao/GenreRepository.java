package com.nervCode.starHall.Songs.dao;

import com.nervCode.starHall.Songs.Entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
