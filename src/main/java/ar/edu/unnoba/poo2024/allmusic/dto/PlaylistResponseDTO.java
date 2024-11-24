package ar.edu.unnoba.poo2024.allmusic.dto;

import ar.edu.unnoba.poo2024.allmusic.model.Song;
import ar.edu.unnoba.poo2024.allmusic.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PlaylistResponseDTO {

    @JsonProperty("name_playlist")
    private String namePlaylist;
    private Long id;
    private User owenr;
    private List<Song> songs;
    private int count;

    public String getPlaylistName() {
        return this.namePlaylist;
    }

    public void setPlaylistName(String name) {
        this.namePlaylist = name;
    }

    public Long getId() {
        return id;
    }

    public int getCount() {
        return songs.size();
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getOwenr() {
        return owenr;
    }

    public void setOwenr(User owenr) {
        this.owenr = owenr;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }
}
