package ar.edu.unnoba.poo2024.allmusic.model;

import java.util.List;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="playlists")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String namePlaylist;
    private int count;
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

    public String getPlaylistName() {
        return this.namePlaylist;
    }

    public void setPlaylistName(String name) {
        this.namePlaylist = name;
    }

    public int getCount(){
        return this.songs.size();
    }

    public void setCount(int i){
        this.count = this.songs.size();
    }

}
