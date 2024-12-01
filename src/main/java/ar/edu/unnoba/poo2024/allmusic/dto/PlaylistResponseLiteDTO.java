package ar.edu.unnoba.poo2024.allmusic.dto;

public class PlaylistResponseLiteDTO {

    private long id;
    private String playlistName;
    private int songsCount;

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public int getSongsCount() {
        return this.songsCount;
    }

    public void setSongsCount(int songCount) {
        this.songsCount = songCount;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
