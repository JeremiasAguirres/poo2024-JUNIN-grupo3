package ar.edu.unnoba.poo2024.allmusic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unnoba.poo2024.allmusic.model.Playlist;
import ar.edu.unnoba.poo2024.allmusic.model.Song;
import ar.edu.unnoba.poo2024.allmusic.model.User;
import ar.edu.unnoba.poo2024.allmusic.repository.PlaylistRepository;
import ar.edu.unnoba.poo2024.allmusic.repository.SongRepository;

@Service
public class PlaylistServiceImp implements PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private SongRepository songRepository;

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    @Override
    public Playlist getPlaylistDetailsById(Long id) throws Exception {
        return playlistRepository.findById(id).orElseThrow(() -> new Exception());
    }

    @Override
    public void createPlaylist(Playlist playlist) throws Exception {
        /*Playlist playlistDB = playlistRepository.findByName(playlist.getPlaylistName());
        if(playlistDB != null) {                                                //si no se queremos que se repita el nombre de las playlist
            throw new Exception("Playlist already exists");                     //tenemos que descomentar esta parte
        }*/
        playlistRepository.save(playlist);
    }

    @Override
    public void editPlaylist(Long playListID, Playlist playlistEdit) throws Exception {
        Playlist playlistDB = playlistRepository.getReferenceById(playListID);
        playlistDB.setPlaylistName(playlistEdit.getPlaylistName());
        playlistRepository.save(playlistDB);
    }

    @Override
    public void deletePlaylist(Long playlistEdit) throws Exception {
        playlistRepository.deleteById(playlistEdit);
    }

    @Override
    public void removeSongFromPlaylist(Long playListID, Long songId) throws Exception {
        Playlist playlistDB = playlistRepository.getReferenceById(playListID);
        Song song = songRepository.getReferenceById(songId);
        if(!playlistDB.getSongs().contains(song)) {
            throw new Exception();
        }
        playlistDB.getSongs().remove(song);
        playlistRepository.save(playlistDB);
    }

    @Override
    public void addSongToPlaylist(Long playListID, Long songId) throws Exception {
        Playlist playlistDB = playlistRepository.getReferenceById(playListID);
        Song song = songRepository.getReferenceById(songId);
        if(playlistDB.getSongs().contains(song)) {
            throw new Exception();
        }
        playlistDB.getSongs().add(song);
        playlistRepository.save(playlistDB);
    }

    @Override
    public boolean checkOwnership(User user, Long id) throws Exception{
        return (playlistRepository.getReferenceById(id).getOwner().equals(user));
    }

    @Override
    public List<Playlist> getCurrentUserPlaylists(String currentUser){
        return playlistRepository.findByOwner(currentUser);
    }

}