package ar.edu.unnoba.poo2024.allmusic.model;

import jakarta.persistence.Entity;

@Entity
public class MusicArtistUser extends User{

    private String artisticName;
    
    public boolean canCreateSongs(){
            return true;
        }
    

    public String getArtisticName() {
        return this.artisticName;
    }

    public void setArtisticName(String artisticName) {
        this.artisticName = artisticName;
    }
}
