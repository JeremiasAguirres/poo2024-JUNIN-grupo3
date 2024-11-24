package ar.edu.unnoba.poo2024.allmusic.repository;

import ar.edu.unnoba.poo2024.allmusic.model.Playlist;
import ar.edu.unnoba.poo2024.allmusic.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM playlists WHERE name_playlist = ?1")
    public Playlist findByName(@Param("name_playlist") String name_playlist);

}