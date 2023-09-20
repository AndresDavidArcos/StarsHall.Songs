package com.nervCode.starHall.Songs.config;

import com.nervCode.starHall.Songs.Entities.Genre;
import com.nervCode.starHall.Songs.Entities.Song;
import com.nervCode.starHall.Songs.dao.GenreDao;
import com.nervCode.starHall.Songs.dao.GenreRepository;
import com.nervCode.starHall.Songs.dao.SongDao;
import com.nervCode.starHall.Songs.dao.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

@Configuration
public class AwsLambdaConfig {

    SongRepository songRepository;
    GenreRepository genreRepository;
    GenreDao genreDao;
    SongDao songDao;


    @Autowired
    public void setSongRepository(SongRepository repo){
        songRepository = repo;
    }

    @Autowired
    public void setGenreRepository(GenreRepository repo){
        genreRepository = repo;
    }
    @Autowired
    public void setGenreDao(GenreDao repo){
        genreDao = repo;
    }
    @Autowired
    public void setSongDao(SongDao repo){
        songDao = repo;
    }

    @Bean
    public Consumer<Song> createSongWithGenres(){
        return (receivedSong) -> {
            Set<Genre> genres = receivedSong.getGenres();
            receivedSong.setGenres(new HashSet<>());
            Song song = songDao.createAndReturnSong(receivedSong);

            genres.forEach(genre -> {
                Optional<Genre> foundGenre = genreDao.findGenreByName(genre.getGenre());
                if (foundGenre.isPresent()) {
                    song.addGenre(foundGenre.get());
                }
                else {
                    Genre newGenre = genreDao.createAndReturnGenre(new Genre(genre.getGenre()));
                    song.addGenre(newGenre);
                }
            });
            songRepository.save(song);
        };
    }
}
