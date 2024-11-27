package ar.edu.unnoba.poo2024.allmusic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.edu.unnoba.poo2024.allmusic.model.Playlist;


@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM playlists WHERE name_playlist = ?1")
    public Playlist findByName(@Param("name_playlist") String name_playlist);

}