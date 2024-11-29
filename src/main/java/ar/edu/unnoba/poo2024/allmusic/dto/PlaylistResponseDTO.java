package ar.edu.unnoba.poo2024.allmusic.dto;

import java.util.List;

public class PlaylistResponseDTO {

    private String playlistName;
    private Long id;
    private List<SongResponseDTO> songs; // Lista de canciones
    private Owner owner;
    private int songsCount;

    public static class Owner{
        private long id;
        private String username;

        public String getUsername(){
            return this.username;
        }

        public void setUsername(String username){
            this.username = username;
        }

        public long getId(){
            return this.id;
        }

        public void setId(Long id){
            this.id = id;
        }
        
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<SongResponseDTO> getSongs() {
        return songs;
    }

    public void setSongs(List<SongResponseDTO> songNames) {
        this.songs = songNames;
    }

    public int getSongsCount() {
        return this.songsCount;
    }

    public void setSongsCount(int songCount) {
        this.songsCount = songCount;
    }

}
