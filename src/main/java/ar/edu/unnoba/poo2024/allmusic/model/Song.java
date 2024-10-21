package ar.edu.unnoba.poo2024.allmusic.model;

public class Song {
    
    private long id;
    private String name;
    private Genre genre;
    private MusicArtistUser author;

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MusicArtistUser getAuthor() {
        return author;
    }

    public void setAuthor(MusicArtistUser author) {
        this.author = author;
    }
}
