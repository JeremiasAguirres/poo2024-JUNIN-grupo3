package ar.edu.unnoba.poo2024.allmusic.model;

import jakarta.persistence.*;

@Entity
@Table(name="playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String namePlaylist;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.namePlaylist;
    }

    public void setName(String name) {
        this.namePlaylist = name;
    }

}
