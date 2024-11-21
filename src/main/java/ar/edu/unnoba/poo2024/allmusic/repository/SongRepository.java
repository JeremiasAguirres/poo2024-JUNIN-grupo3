package ar.edu.unnoba.poo2024.allmusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.unnoba.poo2024.allmusic.model.Song;

@Repository
public interface SongRepository extends JpaRepository<Song,Long>{

    @Query(nativeQuery = true, value = "SELECT * FROM songs WHERE name = ?1")
    public Song findByName(@Param("name") String name);

}
