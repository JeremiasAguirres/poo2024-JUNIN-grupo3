package ar.edu.unnoba.poo2024.allmusic.model;

public abstract class User {
    
    private long id;
    private String username;
    private String password;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    abstract boolean canCreateSongs();
}
