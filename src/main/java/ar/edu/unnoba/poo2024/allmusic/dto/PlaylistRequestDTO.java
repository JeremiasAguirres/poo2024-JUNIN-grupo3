package ar.edu.unnoba.poo2024.allmusic.dto;

import ar.edu.unnoba.poo2024.allmusic.model.Song;
import ar.edu.unnoba.poo2024.allmusic.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PlaylistRequestDTO {

    @JsonProperty("name_playlist")
    private String namePlaylist;

    @JsonIgnore
    private List<Song> songs;

    private int count;

    public String getNamePlaylist() {
        return namePlaylist ;
    }

    public void setnamePlaylist(String namePlaylist) {
        this.namePlaylist = namePlaylist;
    }


    public int getCount() {
        return songs.size();
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

}