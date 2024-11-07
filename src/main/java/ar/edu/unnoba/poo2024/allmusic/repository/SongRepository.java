package ar.edu.unnoba.poo2024.allmusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unnoba.poo2024.allmusic.model.Song;

@Repository
public interface SongRepository extends JpaRepository<Song,Long>{

}
