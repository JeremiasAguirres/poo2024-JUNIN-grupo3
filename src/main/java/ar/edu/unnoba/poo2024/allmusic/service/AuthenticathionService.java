package ar.edu.unnoba.poo2024.allmusic.service;

import ar.edu.unnoba.poo2024.allmusic.model.User;

public interface AuthenticathionService {
    public String authenticate(User user) throws Exception;
}
