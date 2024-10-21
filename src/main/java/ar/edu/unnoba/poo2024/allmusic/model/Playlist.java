package ar.edu.unnoba.poo2024.allmusic.model;

import java.util.List;

public class Playlist {

    private long id;
    private String namePlaylist;
    private User owner;
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
