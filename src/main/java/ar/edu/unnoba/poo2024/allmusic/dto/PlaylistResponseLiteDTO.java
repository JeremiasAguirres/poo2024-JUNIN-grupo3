package ar.edu.unnoba.poo2024.allmusic.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ar.edu.unnoba.poo2024.allmusic.model.Song;

public class PlaylistResponseLiteDTO {

    private long id;
    private String playlistName;
    @JsonIgnore
    private List<Song> songs;
    private int count;

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount() {
        this.count = this.getSongs().size();
    }

    public List<Song> getSongs() {
        return this.songs;
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
}
