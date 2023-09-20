package com.nervCode.starHall.Songs.Entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "song_genre",
    joinColumns = @JoinColumn(name = "song_id"),
    inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();
    @Column(name = "src")
    private String src;
    @Column(name = "title")
    private String title;
    @Column(name = "author")

    private String author;
    @Column(name = "lyrics")

    private String lyrics;
    @Column(name = "interpretation")

    private String interpretation;

    public Song(){}

    public Song(int id,  Set<Genre> genres, String src, String title, String author, String lyrics, String interpretation) {
        this.id = id;
        this.genres = genres;
        this.src = src;
        this.title = title;
        this.author = author;
        this.lyrics = lyrics;
        this.interpretation = interpretation;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", genres=" + genres +
                ", src='" + src + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", lyrics='" + lyrics + '\'' +
                ", interpretation='" + interpretation + '\'' +
                '}';
    }

    public void addGenre(Genre genre){
        if(genres.isEmpty()){
            genres = new HashSet<>();
        }
        genres.add(genre);
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
