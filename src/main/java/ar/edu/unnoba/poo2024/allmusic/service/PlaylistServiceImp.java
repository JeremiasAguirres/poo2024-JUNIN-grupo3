package ar.edu.unnoba.poo2024.allmusic.service;

import ar.edu.unnoba.poo2024.allmusic.model.Playlist;
import ar.edu.unnoba.poo2024.allmusic.model.Song;
import ar.edu.unnoba.poo2024.allmusic.model.User;
import ar.edu.unnoba.poo2024.allmusic.repository.PlaylistRepository;
import ar.edu.unnoba.poo2024.allmusic.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Playlist findPlaylistById(Long id) throws Exception {
        return null;
    }

    @Override
    public void createPlaylist(Playlist playlist) throws Exception {
        Playlist playlistDB = playlistRepository.findByName(playlist.getPlaylistName());
        if(playlistDB != null) {
            throw new Exception("Playlist already exists");
        }
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

    }

    @Override
    public void addSongToPlaylist(Long playListID, Long songId) throws Exception {
        Playlist playlistDB = playlistRepository.getReferenceById(playListID);
        Song songDB = songRepository.findById(songId)
                .orElseThrow(() -> new Exception("Song not found"));        //verifica que este la cancion, si no esta devuelve un error
        if(playlistDB.getSongs().contains(songDB)) {
            throw new Exception("Song already exists");                     //verifica que no se repitan
        }
        playlistDB.getSongs().add(songDB);
        playlistRepository.save(playlistDB);
    }

    @Override
    public boolean checkOwnership(User user, Long id) throws Exception{
        return (playlistRepository.getReferenceById(id).getOwner().equals(user));
    }

}