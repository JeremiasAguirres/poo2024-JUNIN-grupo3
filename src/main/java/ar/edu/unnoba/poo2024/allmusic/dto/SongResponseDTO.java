package ar.edu.unnoba.poo2024.allmusic.dto;

import ar.edu.unnoba.poo2024.allmusic.model.Genre;

public class SongResponseDTO {
    private long id;
    private String name;
    private String description;
    private Genre genre;
    private Artist artist;

    public class Artist{
        private long id;
        private String name;

        public String getName(){
            return this.name;
        }

        public void setName(String name){
            this.name = name;
        }

        public long getId(){
            return this.id;
        }

        public void setId(long id){
            this.id = id;
        }
        
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return this.genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Artist getArtist() {
        return this.artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

}
