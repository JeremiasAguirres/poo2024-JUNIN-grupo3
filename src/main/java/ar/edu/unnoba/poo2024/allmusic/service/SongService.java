package ar.edu.unnoba.poo2024.allmusic.service;

import java.util.List;

import ar.edu.unnoba.poo2024.allmusic.model.MusicArtistUser;
import ar.edu.unnoba.poo2024.allmusic.model.Song;

public interface SongService {
    public List<Song> getAll();
    public Song findById(Long id);
    public void create(Song song) throws Exception;
    public void edit(Song song, Long id) throws Exception;
    public boolean checkOwnership(MusicArtistUser user, Long id) throws Exception;
    public void remove(Long songId);
}
