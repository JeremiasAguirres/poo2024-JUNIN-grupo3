package ar.edu.unnoba.poo2024.allmusic.service;

import java.util.List;

import ar.edu.unnoba.poo2024.allmusic.model.Playlist;
import ar.edu.unnoba.poo2024.allmusic.model.User;

public interface PlaylistService {

    public List<Playlist> getAllPlaylists();
    public Playlist getPlaylistDetailsById(Long id) throws Exception;
    public void createPlaylist(Playlist playlist) throws Exception;
    public void editPlaylist(Long playListID, Playlist playlistName) throws Exception;
    public void deletePlaylist(Long playListID) throws Exception;
    public void addSongToPlaylist(Long playListID, Long songId) throws Exception;
    public void removeSongFromPlaylist(Long playListID, Long songId) throws Exception;
    public boolean checkOwnership(User user, Long playListID) throws Exception;
    public List<Playlist> getCurrentUserPlaylists(String username);

}