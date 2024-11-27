package ar.edu.unnoba.poo2024.allmusic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.unnoba.poo2024.allmusic.model.Song;

@Repository
public interface SongRepository extends JpaRepository<Song,Long>{

    @Query(nativeQuery = true, value = "SELECT * FROM songs WHERE name = ?1")
    public Song findByName(@Param("name") String name);

    //@Query(nativeQuery = true, value = "SELECT songs.* FROM songs JOIN users ON users.id = author_id WHERE (username = ?1 AND genre = ?2) UNION SELECT songs.* FROM songs JOIN users ON users.id = author_id WHERE username = ?1 UNION SELECT songs.* FROM songs WHERE genre = ?2" )
    @Query(value = "SELECT s FROM Song s WHERE s.genre = ?2 AND s.author.username = ?1 UNION SELECT s FROM Song s WHERE s.author.username = ?1 UNION SELECT s FROM Song s WHERE s.genre = ?2")
    public List<Song> getByArtistOrGenre(@Param("username") String artist, @Param("genre") String genre);

}
