package ar.edu.unnoba.poo2024.allmusic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unnoba.poo2024.allmusic.model.MusicArtistUser;
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
    public Song findById(Long id){
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Song> getByArtistOrGenre(String artist, String genre){
        return repository.getByArtistOrGenre(artist, genre);
    }

    @Override
    public void create(Song song) throws Exception{
        Song songDB = repository.findByName(song.getName());
        if(songDB != null){
            throw new Exception();
        }
        repository.save(song);
    }

    public void edit(Song songEdit, Long songId) throws Exception{
        Song songDB = repository.getReferenceById(songId);
        songDB.setName(songEdit.getName());
        songDB.setGenre(songEdit.getGenre());
        repository.save(songDB);
    }

    public void remove(Long songId){
        repository.deleteById(songId);
    }

    public boolean checkOwnership(MusicArtistUser user, Long id) throws Exception{
        return (repository.getReferenceById(id).getAuthor().equals(user));
    }
}
