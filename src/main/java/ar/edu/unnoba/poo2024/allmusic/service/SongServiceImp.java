package ar.edu.unnoba.poo2024.allmusic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unnoba.poo2024.allmusic.model.Genre;
import ar.edu.unnoba.poo2024.allmusic.model.Song;
import ar.edu.unnoba.poo2024.allmusic.repository.SongRepository;

@Service
public class SongServiceImp implements SongService{

    @Autowired
    private SongRepository repository;

    @Override
    public List<Song> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Song> getSongById(Long id){
        return repository.findById(id);
    }

    @Override
    public List<Song> filterByArtistOrGenre(String artisticName, Genre genre) {
        return repository.findAll();
    }

}
