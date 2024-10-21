package ar.edu.unnoba.poo2024.allmusic.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class MusicEnthusiastUser extends User{

    public boolean canCreateSongs(){
        return false;
    }


}
