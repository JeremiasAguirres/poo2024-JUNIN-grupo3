package ar.edu.unnoba.poo2024.allmusic.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="playlists")
public class Playlist {


    //agregar metodo para obtener la cuenta de canciones
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String playlistName;
    @ManyToOne
    private User owner;
    @ManyToMany
    private List<Song> songs;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlaylistName() {
        return this.playlistName;
    }

    public void setPlaylistName(String name) {
        this.playlistName = name;
    }

    public int getSongsCount() {
        if(this.getSongs() == null){
            return 0;
        }
        return this.getSongs().size();
    }

}
