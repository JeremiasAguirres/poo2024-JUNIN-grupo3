package ar.edu.unnoba.poo2024.allmusic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unnoba.poo2024.allmusic.model.User;
import ar.edu.unnoba.poo2024.allmusic.repository.UserRepository;
import ar.edu.unnoba.poo2024.allmusic.util.PasswordEncoder;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void create(User user) throws Exception{
        User userDB = repository.findByUsername(user.getUsername());
        if (userDB != null){
            throw new Exception();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
