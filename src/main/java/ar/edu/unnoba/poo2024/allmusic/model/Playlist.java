package ar.edu.unnoba.poo2024.allmusic.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String namePlaylist;

    @ManyToOne
    private User owner;

    @ManyToMany
    private List<Song> songs;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

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
