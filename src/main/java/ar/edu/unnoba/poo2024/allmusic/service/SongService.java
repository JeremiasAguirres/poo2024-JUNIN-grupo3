package ar.edu.unnoba.poo2024.allmusic.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unnoba.poo2024.allmusic.model.Song;

public interface SongService {
    public List<Song> getAll();
    public Optional<Song> getSongById(Long id);
}
