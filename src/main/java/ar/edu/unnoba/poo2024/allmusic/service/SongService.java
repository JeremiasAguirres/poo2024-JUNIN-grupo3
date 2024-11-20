package ar.edu.unnoba.poo2024.allmusic.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unnoba.poo2024.allmusic.model.Genre;
import ar.edu.unnoba.poo2024.allmusic.model.Song;

public interface SongService {
    public List<Song> getAll();
    public Optional<Song> getSongById(Long id);
    public List<Song> filterByArtistOrGenre(String artisticName, Genre genre);
}
