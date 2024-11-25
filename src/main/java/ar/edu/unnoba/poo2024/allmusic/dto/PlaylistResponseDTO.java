package ar.edu.unnoba.poo2024.allmusic.dto;

import ar.edu.unnoba.poo2024.allmusic.model.Song;
import ar.edu.unnoba.poo2024.allmusic.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PlaylistResponseDTO {

    @JsonProperty("name_playlist")
    private String namePlaylist;
    private Long id;
    private String owner; // Nombre del dueño de la playlist
    private List<String> songNames; // Lista de nombres de canciones

    public String getNamePlaylist() {
        return namePlaylist;
    }

    public void setNamePlaylist(String namePlaylist) {
        this.namePlaylist = namePlaylist;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getSongNames() {
        return songNames;
    }

    public void setSongNames(List<String> songNames) {
        this.songNames = songNames;
    }
}